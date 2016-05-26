package com.challenger.data.enums

abstract class NonNumericFeature(val size: Int, val positiveIndex: Option[Int]) {
  def featureVector: Seq[Double] = Seq.tabulate(size) { i => if (positiveIndex contains i) 1.0 else 0.0 }
}
