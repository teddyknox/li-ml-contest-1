package com.challenger.boosting

import java.util.logging.Logger

import breeze.linalg.DenseVector
import com.challenger.data.TrainingSetLine
import com.challenger.data.enums.{EnumLike, Label}
import com.challenger.model.{Network, NetworkConfiguration}

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

object LogisticSelection {
  val logger = Logger.getLogger(getClass.getName)

  def apply(
    networkConfig: NetworkConfiguration,
    examples: Seq[(DenseVector[Double], Label)]): LogisticSelection = {
    new LogisticSelection(networkConfig, examples)
  }
}

class LogisticSelection(
  networkConfig: NetworkConfiguration,
  examples: Seq[(DenseVector[Double], Label)]) extends BoostingStrategy {

  import LogisticSelection._

  val nn = Network(networkConfig)
  val batches = examples.grouped(networkConfig.batchSize).to[Vector]
  1 to networkConfig.epochs foreach { epoch =>
    val mse = batches.map(nn.updateWeights).sum
    logger.info(s"Epoch #$epoch; MSE: $mse")
  }

  def apply(outputs: Seq[DenseVector[Double]]): DenseVector[Double] = {
    val vectorizedOutputs = DenseVector(outputs.map(_(0)).to[Array])
    nn.classify(vectorizedOutputs)
  }
}
