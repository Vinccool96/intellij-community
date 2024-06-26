// "Replace with 'newFun(t, k)'" "true"

open class Base<T> {
    @Deprecated("", ReplaceWith("newFun(t, k)"))
    fun <K> oldFun(t: T, k: K) = k

    fun <K> newFun(t: T, k: K) = k
}

class Derived : Base<String>() {
    fun foo() {
        <caret>oldFun("a", 1)
    }
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix