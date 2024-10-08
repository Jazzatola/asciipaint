package com.jamesmaggs.asciipaint

import com.jamesmaggs.asciipaint.Canvas._

object Canvas {
  val blank = ' '
  val line = 'x'

  def apply(rows: Int, columns: Int) =
    new Canvas(rows, columns, List.fill(rows * columns)(blank))
}

case class Canvas private(rows: Int, columns: Int, private val pixels: List[Char]) {

  def drawLine(x1: Int, y1: Int, x2: Int, y2: Int): Canvas = {
    if(x1 == x2) drawHorizontalLine(x1, y1, y2)
    else if(y1 == y2) drawVerticalLine(x1, x2, y1)
    else this
  }

  def drawRectangle(x1: Int, y1: Int, x2: Int, y2: Int) =
    drawHorizontalLine(x1, y1, y2)
      .drawVerticalLine(x1, x2, y2)
      .drawHorizontalLine(x2, y2, y1)
      .drawVerticalLine(x2, x1, y1)

  private def drawHorizontalLine(x: Int, y1: Int, y2: Int): Canvas = {
    val ys = range(y1, y2)
    val points = List.fill(ys.length)(x).zip(ys)
    draw(points)
  }

  private def drawVerticalLine(x1: Int, x2: Int, y: Int): Canvas = {
    val xs = range(x1, x2)
    val points = xs.zip(List.fill(xs.length)(y))
    draw(points)
  }

  private def draw(points: List[(Int, Int)]): Canvas = {
    def loop(canvas: Canvas, remaining: List[(Int,Int)]): Canvas = remaining match {
      case Nil => canvas
      case (x, y) :: tail => loop(canvas.paint(x, y, line), tail)
    }

    loop(this, points)
  }

  def fill(x: Int, y: Int, colour: Char) = {

    def validFill(x: Int, y: Int) =
      contains(x, y) && get(x, y) != line && colour != line

    def neighbours(x: Int, y: Int): List[(Int, Int)] =
      List((x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1))

    def fillable(canvas: Canvas)(position: (Int, Int)): Boolean = {
      val (x, y) = position
      canvas.contains(x, y) && canvas.get(x, y) != colour && canvas.get(x, y) != line
    }

    def loop(canvas: Canvas, points: List[(Int, Int)]): Canvas = points match {
      case Nil => canvas
      case (x, y) :: tail =>
        val fillableNeighbours = neighbours(x, y).filter(fillable(canvas))
        loop(canvas.paint(x, y, colour), (tail ++ fillableNeighbours).distinct)
    }

    if (validFill(x, y)) loop(this, List((x,y))) else this
  }

  def paint(x: Int, y: Int, colour: Char): Canvas =
    if(contains(x, y)) set(x, y, colour) else this

  def mkString: String = {
    val border = "-" * (columns + 2)
    def formatRows = (0 until rows).map(formatRow).mkString
    def formatRow(x: Int): String = s"|${row(x).mkString}|\n"

    border + "\n" + formatRows + border
  }

  private def index(x: Int, y: Int) =
    columns * x + y

  private def get(x: Int, y: Int) =
    pixels(index(x, y))

  private def set(x: Int, y: Int, value: Char) =
    new Canvas(rows, columns, pixels.updated(index(x, y), value))

  private def contains(x: Int, y: Int) =
    x >= 0 && x < rows && y >= 0 && y < columns

  private def range(from: Int, to: Int) =
    (math.min(from, to) to math.max(from, to)).toList

  private def row(x: Int) =
    pixels.slice(columns * x, columns * (x + 1))
}
