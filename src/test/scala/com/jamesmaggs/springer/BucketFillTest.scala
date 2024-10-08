package com.jamesmaggs.springer

class BucketFillTest extends UnitSuite {

  private val current = Canvas(3, 3)

  test("fills an area on the canvas, converting arguments") {
    val partitioned = current.drawLine(1, 0, 1, 2)
    val expected = partitioned.fill(0, 2, 'o')
    BucketFill("3", "1", "o").applyTo(partitioned) should be(expected)
  }

  test("returns current canvas if arguments are badly formed") {
    val malformed = List(
      BucketFill("bad", "2", "o"),
      BucketFill("", "2", "o"),
      BucketFill(null, "2", "o"),
      BucketFill("3", "bad", "o"),
      BucketFill("3", "", "o"),
      BucketFill("3", null, "o"),
      BucketFill("3", "2", "bad"),
      BucketFill("3", "2", ""),
      BucketFill("3", "2", null)
    )

    malformed.foreach(_.applyTo(current) should be(current))
  }
}
