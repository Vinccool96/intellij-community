// "Replace with 'newFun(this, p)'" "true"

class C {
    @Deprecated("", ReplaceWith("newFun(this, p)"))
    fun oldFun(p: Int){}
}

fun C.newFun(c: C, p: Int){}

val bar = 0

fun foo() {
    getBar().<caret>oldFun(bar)
}

fun getBar(): C = C()

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix