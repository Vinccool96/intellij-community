// "Replace with 'this'" "true"
class C {
    @Deprecated("", ReplaceWith("this"))
    fun oldFun(): C = this
}

fun foo() {
    C().<caret>oldFun()
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix