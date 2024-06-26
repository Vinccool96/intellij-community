// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package git4idea.rebase.interactive

import git4idea.rebase.GitRebaseEntry
import git4idea.rebase.GitRebaseEntryWithDetails
import git4idea.rebase.GitSquashedCommitsMessage

internal fun <T : GitRebaseEntry> convertToModel(entries: List<T>): GitRebaseTodoModel<T> {
  val result = mutableListOf<GitRebaseTodoModel.Element<T>>()
  // consider auto-squash
  entries.forEach { entry ->
    val index = result.size
    when (entry.action) {
      GitRebaseEntry.Action.PICK, GitRebaseEntry.Action.REWORD -> {
        val type = GitRebaseTodoModel.Type.NonUnite.KeepCommit.Pick
        result.add(GitRebaseTodoModel.Element.Simple(index, type, entry))
      }
      GitRebaseEntry.Action.EDIT -> {
        val type = GitRebaseTodoModel.Type.NonUnite.KeepCommit.Edit
        result.add(GitRebaseTodoModel.Element.Simple(index, type, entry))
      }
      GitRebaseEntry.Action.DROP -> {
        // move them to the end
      }
      is GitRebaseEntry.Action.FIXUP, GitRebaseEntry.Action.SQUASH -> {
        val lastElement = result.lastOrNull() ?: throw IllegalArgumentException("Couldn't unite with non-existed commit")
        val root = when (lastElement) {
          is GitRebaseTodoModel.Element.UniteChild<T> -> lastElement.root
          is GitRebaseTodoModel.Element.UniteRoot<T> -> lastElement
          is GitRebaseTodoModel.Element.Simple<T> -> {
            when (val rootType = lastElement.type) {
              is GitRebaseTodoModel.Type.NonUnite.KeepCommit -> {
                val newRoot = GitRebaseTodoModel.Element.UniteRoot(lastElement.index, rootType, lastElement.entry)
                result[newRoot.index] = newRoot
                newRoot
              }
              is GitRebaseTodoModel.Type.NonUnite.Drop, is GitRebaseTodoModel.Type.NonUnite.UpdateRef -> {
                throw IllegalStateException()
              }
            }
          }
        }
        val element = GitRebaseTodoModel.Element.UniteChild(index, entry, root)
        root.addChild(element)
        result.add(element)

        val newMessage = amendCommitMessage(root, entry)
        if (newMessage != null) {
          root.type = GitRebaseTodoModel.Type.NonUnite.KeepCommit.Reword(newMessage)
        }
      }
      GitRebaseEntry.Action.UPDATE_REF -> {
        val type = GitRebaseTodoModel.Type.NonUnite.UpdateRef
        val element = GitRebaseTodoModel.Element.Simple(index, type, entry)
        result.add(element)
      }
      is GitRebaseEntry.Action.Other -> throw IllegalArgumentException("Couldn't convert unknown action to the model")
    }
  }
  entries.filter { it.action is GitRebaseEntry.Action.DROP }.forEach { entry ->
    val index = result.size
    result.add(GitRebaseTodoModel.Element.Simple(index, GitRebaseTodoModel.Type.NonUnite.Drop, entry))
  }
  return GitRebaseTodoModel(result)
}

private fun <T : GitRebaseEntry> amendCommitMessage(root: GitRebaseTodoModel.Element.UniteRoot<T>,
                                                    newEntry: GitRebaseEntry): String? {
  if (newEntry !is GitRebaseEntryWithDetails || root.entry !is GitRebaseEntryWithDetails) return null

  val oldMessage = (root.type as? GitRebaseTodoModel.Type.NonUnite.KeepCommit.Reword)?.newMessage
                   ?: root.entry.commitDetails.fullMessage
  val entryCommitMessage = GitSquashedCommitsMessage.trimAutosquashSubject(newEntry.commitDetails.fullMessage)
  if (entryCommitMessage.isBlank()) return null

  val newMessage = when {
    newEntry.action is GitRebaseEntry.Action.SQUASH -> {
      GitSquashedCommitsMessage.squash(oldMessage, entryCommitMessage)
    }
    newEntry.action is GitRebaseEntry.Action.FIXUP && newEntry.action.overrideMessage -> {
      entryCommitMessage
    }
    else -> {
      return null
    }
  }

  if (oldMessage == newMessage) return null
  return newMessage
}


internal fun <T : GitRebaseEntry> GitRebaseTodoModel<out T>.convertToEntries(): List<GitRebaseEntry> = elements.map { element ->
  val entry = element.entry
  GitRebaseEntry(element.type.command, entry.commit, entry.subject)
}
