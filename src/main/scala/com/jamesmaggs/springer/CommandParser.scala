package com.jamesmaggs.springer

object CommandParser {

  def parse(input: String): Command =
    Option(input).map(_.split("\\s").toList).map(toCommand).getOrElse(Quit)

  private def toCommand(input: List[String]) = input match {
    case "C" :: width :: height :: _      => CreateCanvas(width, height)
    case "L" :: x1 :: y1 :: x2 :: y2 :: _ => DrawLine(x1, y1, x2, y2)
    case "R" :: x1 :: y1 :: x2 :: y2 :: _ => DrawRectangle(x1, y1, x2, y2)
    case "B" :: x :: y :: colour :: _     => BucketFill(x, y, colour)
    case "Q" :: _                         => Quit
    case _                                => UnknownCommand
  }
}
