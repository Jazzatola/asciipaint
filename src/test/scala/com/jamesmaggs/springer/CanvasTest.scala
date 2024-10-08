package com.jamesmaggs.springer

import org.scalatest.{Matchers, FunSuite}
import CanvasMatchers._

class CanvasTest extends FunSuite with Matchers {

  test("draw an empty canvas") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3) should lookLike(output)
  }

  test("paint a single pixel") {
    val output = "-----\n" +
                 "|   |\n" +
                 "| c |\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).paint(1, 1, 'c') should lookLike(output)
  }

  test("painting outside the canvas is ignored") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).paint(-1, 1, 'c') should lookLike(output)
    Canvas(3, 3).paint(1, 3, 'c') should lookLike(output)
    Canvas(3, 3).paint(3, 1, 'c') should lookLike(output)
    Canvas(3, 3).paint(1, -1, 'c') should lookLike(output)
  }

  test("draw a horizontal line") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|xxx|\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).drawLine(1, 0, 1, 2) should lookLike(output)
    Canvas(3, 3).drawLine(1, 2, 1, 0) should lookLike(output)
  }

  test("drawing a horizontal line across the canvas is ok") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|xxx|\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).drawLine(1, 0, 1, 3) should lookLike(output)
    Canvas(3, 3).drawLine(1, -1, 1, 2) should lookLike(output)
    Canvas(3, 3).drawLine(1, -1, 1, 3) should lookLike(output)
  }

  test("drawing a horizontal line outside the canvas is ignored") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).drawLine(-1, -1, -1, 3) should lookLike(output)
    Canvas(3, 3).drawLine(3, -1, 3, 3) should lookLike(output)
  }

  test("draw a vertical line") {
    val output = "-----\n" +
                 "| x |\n" +
                 "| x |\n" +
                 "| x |\n" +
                 "-----"

    Canvas(3, 3).drawLine(0, 1, 2, 1) should lookLike(output)
    Canvas(3, 3).drawLine(2, 1, 0, 1) should lookLike(output)
  }

  test("drawing a vertical line across the canvas is ok") {
    val output = "-----\n" +
                 "| x |\n" +
                 "| x |\n" +
                 "| x |\n" +
                 "-----"

    Canvas(3, 3).drawLine(0, 1, 3, 1) should lookLike(output)
    Canvas(3, 3).drawLine(-1, 1, 3, 1) should lookLike(output)
    Canvas(3, 3).drawLine(-1, 1, 3, 1) should lookLike(output)
  }

  test("drawing a vertical line outside the canvas is ignored") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).drawLine(-1, -1, 3, -1) should lookLike(output)
    Canvas(3, 3).drawLine(-1, 3, 3, 3) should lookLike(output)
  }

  test("drawing a diagonal line is ignored") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).drawLine(-1, -1, 3, 3) should lookLike(output)
    Canvas(3, 3).drawLine(0, 0, 2, 1) should lookLike(output)
  }

  test("draw a rectangle") {
    val output = "-------\n" +
                 "|     |\n" +
                 "| xxx |\n" +
                 "| x x |\n" +
                 "| xxx |\n" +
                 "|     |\n" +
                 "-------"

    Canvas(5, 5).drawRectangle(1, 1, 3, 3) should lookLike(output)
  }

  test("drawing a rectangle across the canvas is ok") {
    val output = "-------\n" +
                 "|     |\n" +
                 "| xxxx|\n" +
                 "| x   |\n" +
                 "| xxxx|\n" +
                 "|     |\n" +
                 "-------"

    Canvas(5, 5).drawRectangle(1, 1, 3, 5) should lookLike(output)
  }

  test("drawing a rectangle outside the canvas is ignored") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).drawRectangle(3, 1, 5, 5) should lookLike(output)
    Canvas(3, 3).drawRectangle(-1, -1, 3, 3) should lookLike(output)
  }

  test("fill empty canvas with colour") {
    val output = "-----\n" +
                 "|ooo|\n" +
                 "|ooo|\n" +
                 "|ooo|\n" +
                 "-----"

    Canvas(3, 3).fill(1, 1, 'o') should lookLike(output)
  }

  test("fill outside canvas is ignored") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).fill(-1, -1, 'o') should lookLike(output)
  }

  test("fill on an existing line is ignored") {
    val output = "-----\n" +
                 "|   |\n" +
                 "|xxx|\n" +
                 "|   |\n" +
                 "-----"

    Canvas(3, 3).drawLine(1, 0, 1, 2).fill(1, 1, 'o') should lookLike(output)
  }

  test("fill area of canvas with colour") {
    val output = "-------\n" +
                 "|  xoo|\n" +
                 "|  xoo|\n" +
                 "|  xoo|\n" +
                 "|  xoo|\n" +
                 "|  xoo|\n" +
                 "-------"

    Canvas(5, 5).drawLine(0, 2, 4, 2).fill(2, 4, 'o') should lookLike(output)
  }

  test("extended example") {
    val output = "----------------------\n" +
                 "|oooooooooooooooxxxxx|\n" +
                 "|xxxxxxooooooooox   x|\n" +
                 "|     xoooooooooxxxxx|\n" +
                 "|     xoooooooooooooo|\n" +
                 "----------------------"

    val canvas = Canvas(4, 20)
      .drawLine(1, 0, 1, 5)
      .drawLine(2, 5, 3, 5)
      .drawRectangle(0, 15, 2, 19)
      .fill(2, 9, 'o')

    canvas should lookLike(output)
  }
}
