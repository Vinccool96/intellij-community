// "Replace with '{ this.bar }()'" "true"

private class A {
    val bar = 1

    @Deprecated("t", ReplaceWith("{ this.bar }()"))
    fun foooo() {
        { bar }()
    }
}

private fun test(a: A) {
    val c = a.<caret>foooo()
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.k2.codeinsight.fixes.replaceWith.DeprecatedSymbolUsageFix