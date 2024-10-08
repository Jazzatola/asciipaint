package com.jamesmaggs.springer

import org.scalatest.matchers.{MatchResult, Matcher}

trait CanvasMatchers {

  class CanvasStringRepresentationMatcher(expected: String) extends Matcher[Canvas] {
    override def apply(canvas: Canvas): MatchResult = {
      val representation = canvas.mkString
      MatchResult(
        representation == expected,
        s"This canvas:\n\n$representation\n\ndoes not look like this one:\n\n$expected\n",
        s"This canvas:\n\n$representation\n\nlooks just like this one:\n\n$expected\n"
      )
    }
  }

  def lookLike(canvas: String) = new CanvasStringRepresentationMatcher(canvas)
}

object CanvasMatchers extends CanvasMatchers