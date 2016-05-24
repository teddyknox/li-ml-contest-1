package com.challenger.model

import com.challenger.data.TrainingSetLine
import com.challenger.model.function._
import com.challenger.model.layers.FullyConnectedLayer

object Network {

  val defaultAlpha = 0.1

  val defaultLambda = 100.0

  def apply(
      trainingSet: Seq[TrainingSetLine],
      hiddenLayerSizes: Seq[Int] = Seq(1),
      outputLayerSize: Int = 1,
      activationFunction: DifferentiableFunction[Double, Double] = relu,
      alpha: Double = defaultAlpha,
      lambda: Double = defaultLambda): Network = {
    new Network(trainingSet, hiddenLayerSizes, outputLayerSize, activationFunction, alpha, lambda)
  }
}

class Network(
    trainingSet: Seq[TrainingSetLine],
    hiddenLayerSizes: Seq[Int],
    outputLayerSize: Int,
    activationFunction: DifferentiableFunction[Double, Double],
    alpha: Double,
    lambda: Double) {

  private val featureVectors = trainingSet map { _.features.vector }

  private val inputSizes = featureVectors.head.size +: hiddenLayerSizes
  private val outputSizes = hiddenLayerSizes :+ outputLayerSize

  val layers = inputSizes zip outputSizes map { case (input, output) =>
    FullyConnectedLayer(input, output, activationFunction, alpha, lambda, trainingSet.size)
  }

  def initialize(): Unit = {
  }
}
