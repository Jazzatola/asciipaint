package com.jamesmaggs.springer

import com.jamesmaggs.springer.Command._

import scala.util.Try

sealed trait Command {
  def applyTo(canvas: Canvas): Canvas
}

object Command {

  def parseInt(value: String): Option[Int] =
    Try { value.toInt }.toOption

  def parseChar(value: String): Option[Char] =
    Option(value).filter(_.size == 1).map(_.head)
}

case class CreateCanvas(width: String, height: String) extends Command {

  override def applyTo(canvas: Canvas) = {
    val freshCanvas = for {
      rows <- parseInt(height)
      columns <- parseInt(width)
    } yield Canvas(rows, columns)

    freshCanvas.getOrElse(canvas)
  }
}

case class DrawLine(x1: String, y1: String, x2: String, y2: String) extends Command {
  override def applyTo(canvas: Canvas) = {
    val updated = for {
      i1 <- parseInt(y1)
      j1 <- parseInt(x1)
      i2 <- parseInt(y2)
      j2 <- parseInt(x2)
    } yield canvas.drawLine(i1 - 1, j1 - 1, i2 - 1, j2 - 1)

    updated.getOrElse(canvas)
  }
}

case class DrawRectangle(x1: String, y1: String, x2: String, y2: String) extends Command {
  override def applyTo(canvas: Canvas): Canvas = {
    val updated = for {
      i1 <- parseInt(y1)
      j1 <- parseInt(x1)
      i2 <- parseInt(y2)
      j2 <- parseInt(x2)
    } yield canvas.drawRectangle(i1 - 1, j1 - 1, i2 - 1, j2 - 1)

    updated.getOrElse(canvas)
  }
}

case class BucketFill(x: String, y: String, colour: String) extends Command {
  override def applyTo(canvas: Canvas): Canvas = {
    val updated = for {
      i <- parseInt(y)
      j <- parseInt(x)
      colour <- parseChar(colour)
    } yield canvas.fill(i - 1, j - 1, colour)

    updated.getOrElse(canvas)
  }
}

object UnknownCommand extends Command {
  override def applyTo(canvas: Canvas): Canvas = canvas
}

object Quit extends Command {
  override def applyTo(canvas: Canvas): Canvas = canvas
}