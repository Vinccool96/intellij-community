// "Add non-null asserted (!!) call" "false"
// ACTION: Convert function to property
// ACTION: Convert to block body
// ACTION: Create test
// ACTION: Enable a trailing comma by default in the formatter
// ACTION: Enable option 'Function return types' for 'Types' inlay hints
// ACTION: Specify return type explicitly

fun foo<caret>() = arrayOf(java.lang.String.valueOf(1))