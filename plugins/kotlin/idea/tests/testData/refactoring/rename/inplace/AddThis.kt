// NEW_NAME: foo
// RENAME: member
// IGNORE_K2

class A {
    fun foo() {}

    fun goo() {
        fun <caret>innerGoo() {
            foo()
        }
        innerGoo()
    }
}