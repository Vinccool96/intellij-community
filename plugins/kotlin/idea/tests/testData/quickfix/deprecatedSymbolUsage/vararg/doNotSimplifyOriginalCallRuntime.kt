// "Replace with 'newFun(p)'" "true"
// WITH_STDLIB

fun foo(vararg s: String) = s.joinToString()

@Deprecated("", ReplaceWith("newFun(p)"))
fun oldFun(p: String){
    newFun(p)
}

fun newFun(p: String){}

fun foo() {
    <caret>oldFun(foo(*arrayOf("a", "b")))
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix