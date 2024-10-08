package com.jamesmaggs.asciipaint

class CreateCanvasTest extends UnitSuite {

  private val current = Canvas(2, 4)

  test("replaces current canvas with new one") {
    CreateCanvas("5", "3").applyTo(current) should be(Canvas(3, 5))
  }

  test("returns current canvas if arguments are badly formed") {
    CreateCanvas("bad", "3").applyTo(current) should be(current)
    CreateCanvas("", "3").applyTo(current) should be(current)
    CreateCanvas(null, "3").applyTo(current) should be(current)
    CreateCanvas("5", "bad").applyTo(current) should be(current)
    CreateCanvas("5", "").applyTo(current) should be(current)
    CreateCanvas("5", null).applyTo(current) should be(current)
  }
}
