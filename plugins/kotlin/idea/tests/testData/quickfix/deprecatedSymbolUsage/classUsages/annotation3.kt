// "Replace with 'test.Bar(1)'" "true"

package test

@Deprecated("Replace with bar", ReplaceWith("test.Bar(1)"))
annotation class Foo

annotation class Bar(val p: Int)

@Foo<caret> class C {}
// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix