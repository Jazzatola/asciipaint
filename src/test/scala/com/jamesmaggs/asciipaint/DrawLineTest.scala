package com.jamesmaggs.asciipaint

class DrawLineTest extends UnitSuite {

  private val current = Canvas(5, 5)

  test("draws a line on the canvas, converting arguments") {
    val expected = current.drawLine(1, 2, 1, 4)
    DrawLine("3", "2", "5", "2").applyTo(current) should be(expected)
  }

  test("returns current canvas if arguments are badly formed") {
    val malformed = List(
      DrawLine("bad", "2", "5", "2"),
      DrawLine("", "2", "5", "2"),
      DrawLine(null, "2", "5", "2"),
      DrawLine("3", "bad", "5", "2"),
      DrawLine("3", "", "5", "2"),
      DrawLine("3", null, "5", "2"),
      DrawLine("3", "2", "bad", "2"),
      DrawLine("3", "2", "", "2"),
      DrawLine("3", "2", "null", "2"),
      DrawLine("3", "2", "5", "bad"),
      DrawLine("3", "2", "5", ""),
      DrawLine("3", "2", "5", null)
    )

    malformed.foreach(_.applyTo(current) should be(current))
  }
}
