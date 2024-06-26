// Check that evaluate expression works inside inline function
package parametersOfInlineFunSeveralOnLine

fun main(args: Array<String>) {
    val a = A()
    a.foo { 1 + 1 }.bar { 1 + 1 }
}

inline fun A.foo(f: (i: Int) -> Unit): A {
    f(1)
    return this
}

inline fun A.bar(f: (s: String) -> Unit): A {
    // STEP_INTO: 1
    //Breakpoint!
    f("str")
    return this
}

class A()

// EXPRESSION: it
// RESULT: "str": Ljava/lang/String;