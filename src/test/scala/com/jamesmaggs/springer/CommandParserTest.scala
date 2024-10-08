package com.jamesmaggs.springer

class CommandParserTest extends UnitSuite {

  val parser = CommandParser

  test("parses create canvas command") {
    parser.parse("C 3 4") should be(CreateCanvas("3", "4"))
  }

  test("parses draw line command") {
    parser.parse("L 1 2 3 4") should be(DrawLine("1", "2", "3", "4"))
  }

  test("parses draw rectangle command") {
    parser.parse("R 1 2 3 4") should be(DrawRectangle("1", "2", "3", "4"))
  }

  test("parses bucket fill command") {
    parser.parse("B 1 2 o") should be(BucketFill("1", "2", "o"))
  }

  test("parses quit command") {
    parser.parse("Q") should be(Quit)
  }

  test("commands are case sensitive") {
    parser.parse("c 1 2") should not(be(parser.parse("C 1 2")))
  }

  test("unknown commands have no effect on the canvas") {
    val canvas = Canvas(3, 3)
    val commands = List(
      parser.parse("c 2 3"),
      parser.parse("q"),
      parser.parse("quit"),
      parser.parse("W 1 2 3"),
      parser.parse("1 2 3 4")
    )

    commands.foreach(_.applyTo(canvas) should be(canvas))
  }

  test("if no user input can be read, bail out") {
    parser.parse(null) should be (Quit)
  }

  test("extra arguments are ignored") {
    parser.parse("C 3 4 5 abc") should be(CreateCanvas("3", "4"))
    parser.parse("L 1 2 3 4 5 abc") should be(DrawLine("1", "2", "3", "4"))
    parser.parse("R 1 2 3 4 5 abc") should be(DrawRectangle("1", "2", "3", "4"))
    parser.parse("B 1 2 o 4 abc") should be(BucketFill("1", "2", "o"))
    parser.parse("Q 1 abc") should be(Quit)
  }
}
