package com.jamesmaggs.springer

trait IO[A] { self =>
  def run: A

  def map[B](f: A => B): IO[B] =
    new IO[B] { def run = f(self.run) }

  def flatMap[B](f: A => IO[B]): IO[B] =
    new IO[B] { def run = f(self.run).run }

  def andThen[B](io: IO[B]): IO[B] =
    new IO[B] { def run = { self.run; io.run } }
}

object IO {
  def apply[A](a: => A): IO[A] = new IO[A] { def run = a }
}

object Console {
  def readLine = IO { io.StdIn.readLine() }
  def print(message: String) = IO { scala.Console.print(message) }
  def printLine(message: String) = IO { scala.Console.println(message) }
}