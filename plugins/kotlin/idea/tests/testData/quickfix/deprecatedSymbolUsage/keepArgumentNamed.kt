// "Replace with 'newFun(p1, p2, null, option)'" "true"

interface I {
    @Deprecated("", ReplaceWith("newFun(p1, p2, null, option)"))
    fun oldFun(p1: String, p2: Int, option: Int = 1)

    fun newFun(p1: String, p2New: Int, p3: String?, option: Int = 2)
}

fun foo(i: I) {
    i.<caret>oldFun("a", p2 = 0)
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix