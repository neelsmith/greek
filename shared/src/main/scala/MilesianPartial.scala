package edu.holycross.shot.greek

import scala.scalajs.js
import scala.scalajs.js.annotation._


import edu.holycross.shot.mid.validator._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter

trait MilesianPartial {

  def ascii: String
  def ucode: String
  def toDouble: Double

  def compare(that: MilesianPartial): Int = {
    this.toDouble compare that.toDouble
  }
}
