// "Replace with 'newFun(c)'" "true"

@Deprecated("", ReplaceWith("newFun(c)"))
fun oldFun(c: Char){}

fun newFun(c: Char){}

fun foo() {
    <caret>oldFun(java.io.File.separatorChar)
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix