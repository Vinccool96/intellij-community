// "Replace with 'newFun(p1, *p2)'" "true"
// WITH_STDLIB

@Deprecated("", ReplaceWith("newFun(p1, *p2)"))
fun oldFun(p1: String, p2: IntArray) {
    newFun(p1, *p2)
}

fun newFun(p1: String, vararg p2: Int){}

fun foo() {
    <caret>oldFun("a", intArrayOf(1, 2, 3))
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix