// "Replace with 'newFun(this)'" "true"

class C {
    @Deprecated("", ReplaceWith("newFun(this)"))
    fun oldFun(){}
}

fun C.newFun(c: C){}

fun foo() {
    val bar = 0
    getBar().<caret>oldFun()
}

fun getBar(): C = C()

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix