// "Add remaining branches" "true"
// WITH_STDLIB

sealed class A
class B : A()

fun test(a: A) {
    val r = <caret>when (a) {

    }
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.AddWhenRemainingBranchesFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.AddWhenRemainingBranchFixFactories$AddRemainingWhenBranchesQuickFix