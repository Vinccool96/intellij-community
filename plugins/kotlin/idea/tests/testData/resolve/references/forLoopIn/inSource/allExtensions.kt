fun test(f: Foo) {
    for(i <caret>in f) {}
}

interface Foo
fun Foo.iterator(): Iterator

interface Iterator

fun Iterator.next(): Any
fun Iterator.hasNext(): Boolean

// MULTIRESOLVE
// REF: Foo.iterator()
// REF: Iterator.next()
// REF: Iterator.hasNext()
