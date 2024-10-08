package com.jamesmaggs.asciipaint

class DrawRectangleTest extends UnitSuite {

   private val current = Canvas(5, 5)

   test("draws a rectangle on the canvas, converting arguments") {
     val expected = current.drawRectangle(1, 2, 2, 4)
     DrawRectangle("3", "2", "5", "3").applyTo(current) should be(expected)
   }

   test("returns current canvas if arguments are badly formed") {
     val malformed = List(
       DrawRectangle("bad", "2", "5", "3"),
       DrawRectangle("", "2", "5", "3"),
       DrawRectangle(null, "2", "5", "3"),
       DrawRectangle("3", "bad", "5", "3"),
       DrawRectangle("3", "", "5", "3"),
       DrawRectangle("3", null, "5", "3"),
       DrawRectangle("3", "2", "bad", "3"),
       DrawRectangle("3", "2", "", "3"),
       DrawRectangle("3", "2", "null", "3"),
       DrawRectangle("3", "2", "5", "bad"),
       DrawRectangle("3", "2", "5", ""),
       DrawRectangle("3", "2", "5", null)
     )

     malformed.foreach(_.applyTo(current) should be(current))
   }
 }
