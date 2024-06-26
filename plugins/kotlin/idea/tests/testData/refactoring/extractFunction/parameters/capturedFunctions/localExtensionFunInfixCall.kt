// OPTIONS: true, false, false, true, false
// PARAM_DESCRIPTOR: value-parameter n: kotlin.Int defined in foo
// PARAM_DESCRIPTOR: local final infix fun kotlin.Int.bar(m: kotlin.Int): kotlin.Int defined in foo
// PARAM_TYPES: kotlin.Int
// PARAM_TYPES: kotlin.Int.(m: kotlin.Int) -> kotlin.Int
fun foo(n: Int): Int {
    infix fun Int.bar(m: Int) = this * m * n

    return <selection>n bar (n + 1)</selection>
}