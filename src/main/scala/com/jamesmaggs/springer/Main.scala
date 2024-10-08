package com.jamesmaggs.springer

object Main {
  def main(args: Array[String]): Unit = {
    val initialCanvas = Canvas(10, 20)
    Repl.loop(initialCanvas).run
  }
}