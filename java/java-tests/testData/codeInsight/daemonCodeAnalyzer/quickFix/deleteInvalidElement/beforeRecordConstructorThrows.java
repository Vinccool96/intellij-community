// "Remove throws list" "true-preview"
record R() {
  R() throws<caret> Exception, Error {}
}