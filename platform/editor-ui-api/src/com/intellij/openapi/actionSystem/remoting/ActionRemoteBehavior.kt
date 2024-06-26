// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.actionSystem.remoting

import com.intellij.util.PlatformUtils
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
@ApiStatus.Experimental
enum class ActionRemoteBehavior {
  /**
   * The action works only on a thin client
    */
  FrontendOnly,

  /**
   * The action tries to perform on a thin client first and if it's not available it goes to a backend
   */
  FrontendThenBackend,

  /**
   * The action delegates to a backend
   */
  BackendOnly,

  /**
   * The action delegates to a backend and if it isn't available there it tries to be executed on a frontend
   */
  BackendThenFrontend,

  /**
   * The action should have both frontend and backend representations accessible from a frontend.
   * E.g. Debug Log Settings which has the frontend version and also the backend version with (On Host) suffix
   */
  Duplicated,

  /**
   * Action should be disabled in remote dev mode on both sides
   */
  Disabled
}

/**
 * Marker interface which defines how an action should act in remote development mode
 *
 * See [ActionRemoteBehavior] for the modes
 */
@ApiStatus.Internal
@ApiStatus.Experimental
interface ActionRemoteBehaviorSpecification {
  fun getBehavior(useDeclaredBehaviour: Boolean = false): ActionRemoteBehavior = getBehavior()
  fun getBehavior(): ActionRemoteBehavior

  interface Frontend : ActionRemoteBehaviorSpecification {
    override fun getBehavior(useDeclaredBehaviour: Boolean): ActionRemoteBehavior = ActionRemoteBehavior.FrontendOnly
    override fun getBehavior(): ActionRemoteBehavior = getBehavior(useDeclaredBehaviour = false)
  }

  interface FrontendThenBackend : ActionRemoteBehaviorSpecification {
    override fun getBehavior(useDeclaredBehaviour: Boolean): ActionRemoteBehavior = ActionRemoteBehavior.FrontendThenBackend
    override fun getBehavior(): ActionRemoteBehavior = getBehavior(useDeclaredBehaviour = false)
  }

  interface BackendOnly : ActionRemoteBehaviorSpecification {
    override fun getBehavior(useDeclaredBehaviour: Boolean): ActionRemoteBehavior {
      if (!useDeclaredBehaviour && (PlatformUtils.isRider() || PlatformUtils.isCLion())) return ActionRemoteBehavior.FrontendThenBackend
      return ActionRemoteBehavior.BackendOnly
    }
    override fun getBehavior(): ActionRemoteBehavior = getBehavior(useDeclaredBehaviour = false)
  }

  interface Duplicated : ActionRemoteBehaviorSpecification {
    override fun getBehavior(useDeclaredBehaviour: Boolean): ActionRemoteBehavior = ActionRemoteBehavior.Duplicated
    override fun getBehavior(): ActionRemoteBehavior = getBehavior(useDeclaredBehaviour = false)
  }

  interface Disabled : ActionRemoteBehaviorSpecification {
    override fun getBehavior(useDeclaredBehaviour: Boolean): ActionRemoteBehavior {
      if (!useDeclaredBehaviour && (PlatformUtils.isRider() || PlatformUtils.isCLion())) return ActionRemoteBehavior.FrontendThenBackend
      return ActionRemoteBehavior.Disabled
    }
    override fun getBehavior(): ActionRemoteBehavior = getBehavior(useDeclaredBehaviour = false)
  }
}


