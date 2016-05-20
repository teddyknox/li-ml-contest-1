package com.challenger.model.enums

abstract class NonNumericFeature(val size: Int, val positiveIndex: Int) {

  def featureVector: Seq[Double] = {
    var index = 0

    def indicatorVariable = {
      val value = if (index == positiveIndex) 1.0 else 0.0
      index += 1
      value
    }
    Seq.fill(size) { indicatorVariable }
  }
}
