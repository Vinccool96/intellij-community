// PSI_ELEMENT: org.jetbrains.kotlin.psi.KtClass
// OPTIONS: derivedClasses
// PSI_ELEMENT_AS_TITLE: "interface X"
interface <caret>X {

}

open class A : X {

}

open class C : Y {

}

open class Z : A() {

}

// IGNORE_PLATFORM_JS: Java-specific code
// IGNORE_PLATFORM_NATIVE: Java-specific code
