package com.challenger.data.enums

abstract class NonNumericFeature(val size: Int, val positiveIndex: Int) {
  def featureVector: Seq[Double] = Seq.tabulate(size) { i => if (i == positiveIndex) 1.0 else 0.0 }
}
