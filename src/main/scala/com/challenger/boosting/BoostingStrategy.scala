package com.challenger.boosting

import breeze.linalg.DenseVector
import com.challenger.data.enums.EnumLike

import scala.util.Random

sealed abstract class BoostingStrategy {
  def apply(outputs: Seq[DenseVector[Double]]): DenseVector[Double]
}

object BoostingStrategy extends EnumLike[BoostingStrategy] {

  case object RandomSelection extends BoostingStrategy {
    override def apply(outputs: Seq[DenseVector[Double]]): DenseVector[Double] = {
      outputs(Random.nextInt(outputs.size))
    }
  }

  case object AverageSelection extends BoostingStrategy {
    override def apply(outputs: Seq[DenseVector[Double]]): DenseVector[Double] = {
      val sums = outputs reduce { _ + _ }
      sums :/ outputs.size.toDouble
    }
  }

  override val values = Seq(RandomSelection, AverageSelection)
}
