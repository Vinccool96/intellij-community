// "Add non-null asserted (foo!!) call" "true"
class Foo {
    fun f() = 1
}

fun test(foo: Foo?) {
    val f = foo::f<caret>
}

// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.AddExclExclCallFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.AddExclExclCallFix