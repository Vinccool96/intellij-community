// "Replace with 'newFun(p)'" "true"
// WITH_STDLIB

@Deprecated("", ReplaceWith("newFun(p)"))
fun oldFun(vararg p: Byte){
    newFun(p)
}

fun newFun(p: ByteArray){}

fun foo() {
    <caret>oldFun(1, 2, 3)
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix