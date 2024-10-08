package com.jamesmaggs.springer

import Console._
import CommandParser._

object Repl {

  val prompt = "enter command: "

  val quit = IO {()}

  def readCommand = readLine.map(parse)

  def updateCanvas(canvas: Canvas, command: Command) = IO { command.applyTo(canvas) }

  def printCanvas(canvas: Canvas) = printLine(canvas.mkString)

  def loop(canvas: Canvas): IO[Unit] = {
    for {
      _       <- print(prompt)
      command <- readCommand
      updated <- updateCanvas(canvas, command)
      _       <- if (command == Quit) quit else printCanvas(updated).andThen(loop(updated))
    } yield ()
  }
}