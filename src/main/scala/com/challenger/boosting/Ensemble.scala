package com.challenger.boosting

import java.util.logging.Logger

import breeze.linalg.DenseVector
import com.challenger.boosting.bagging.BaggingHelper._
import com.challenger.data.TrainingSetLine
import com.challenger.model.{Network, NetworkConfiguration}

object Ensemble {

  val logger = Logger.getLogger(getClass.getName)

  object Defaults {
    val ensembleSize: Int = 30
    val baggingFraction: Double = 0.5
    val boostingStrategy: BoostingStrategy = BoostingStrategy.AverageSelection
  }

  def apply(
      ensembleSize: Int,
      baggingFraction: Double,
      boostingStrategy: BoostingStrategy,
      networkConfig: NetworkConfiguration,
      trainingSet: Seq[TrainingSetLine]) = {
    new Ensemble(
      ensembleSize,
      baggingFraction,
      boostingStrategy,
      networkConfig,
      trainingSet)
  }
}

class Ensemble(
    ensembleSize: Int,
    baggingFraction: Double,
    boostingStrategy: BoostingStrategy,
    networkConfig: NetworkConfiguration,
    trainingSet: Seq[TrainingSetLine]) {

  import Ensemble._

  if (baggingFraction <= 0.0 || baggingFraction > 1.0) {
    sys.error("Bagging fraction has to be (0, 1.0]")
  }

  val trainingSets = if (baggingFraction == 1.0) {
    Seq.fill(ensembleSize) { trainingSet map { e => e.features.vector -> e.label } }
  } else {
    Seq.fill(ensembleSize) { trainingSet map { e => e.features.vector -> e.label } } map { _.sample(math.round(baggingFraction * trainingSet.size).toInt) }
  }

  val networks = trainingSets.zipWithIndex map { case (examples, ensembleIndex) =>
    logger.info(s"Example size: ${examples.size}")
    val nn = Network(networkConfig)
    val batches = examples.grouped(networkConfig.batchSize).to[Vector]
    1 to networkConfig.epochs foreach { epoch =>
      val mse = batches.map(nn.updateWeights).sum
      logger.info(s"Ensemble #$ensembleIndex; Epoch #$epoch; MSE: $mse")
    }
    nn
  }

  def classify(features: DenseVector[Double]): DenseVector[Double] = {
    boostingStrategy(networks map { _.classify(features) })
  }
}
