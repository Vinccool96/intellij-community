// "Change to 'return@foo'" "true"
inline fun foo(f: (Int) -> Int?) {}

fun baz(): Int = 0

fun test() {
    foo { i ->
        if (i == 1) return null<caret>
        0
    }
}
// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.ChangeToLabeledReturnFix
// FUS_K2_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.ChangeToLabeledReturnFix