package com.challenger.boosting

import java.util.logging.Logger

import breeze.linalg.DenseVector
import com.challenger.boosting.bagging.BaggingHelper._
import com.challenger.data.TrainingSetLine
import com.challenger.data.enums.Label
import com.challenger.model.{Network, NetworkConfiguration}

import scala.util.Random

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

  val examples1 = trainingSet map { e => e.features.vector -> e.label }
  val nn1 = Network(networkConfig)
  val batches1 = examples1.grouped(networkConfig.batchSize).to[Vector]
  1 to networkConfig.epochs foreach { epoch =>
    val mse = batches1.map(nn1.updateWeights).sum
    logger.info(s"Epoch #$epoch; MSE: $mse")
  }

  val (correct1, incorrect1) = examples1 partition { case (features, label) => Label.get(nn1.classify(features)(0)) == label }
  val examples2 = correct1.sample((examples1.size.toDouble / 2).floor.toInt) ++
    incorrect1.sample((examples1.size.toDouble / 2).ceil.toInt)
  val nn2 = Network(networkConfig)
  val batches2 = examples2.grouped(networkConfig.batchSize).to[Vector]
  1 to networkConfig.epochs foreach { epoch =>
    val mse = batches2.map(nn2.updateWeights).sum
    logger.info(s"Epoch #$epoch; MSE: $mse")
  }


  val (agree, disagree) = examples1 partition { case (features, label) =>
    val nn1Pred = Label.get(nn1.classify(features)(0))
    val nn2Pred = Label.get(nn2.classify(features)(0))
    nn1Pred == nn2Pred
  }
  val nn3 = Network(networkConfig)
  val batches3 = disagree.grouped(networkConfig.batchSize).to[Vector]
  1 to networkConfig.epochs foreach { epoch =>
    val mse = batches3.map(nn3.updateWeights).sum
    logger.info(s"Epoch #$epoch; MSE: $mse")
  }

  val networks = Seq(nn1, nn2, nn3)

  def classify(features: DenseVector[Double]): DenseVector[Double] = {
    boostingStrategy(networks map { _.classify(features) })
  }
}
