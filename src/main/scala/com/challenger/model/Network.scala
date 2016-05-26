package com.challenger.model

import java.util.logging.Logger

import breeze.linalg.DenseVector
import com.challenger.data.enums.Label
import com.challenger.model.function.DifferentiableFunction
import com.challenger.model.function.DifferentiableFunction._
import com.challenger.model.layers.FullyConnectedLayer

import scala.util.Random

object Network {

  val defaultAlpha = 0.1

  val defaultLambda = 100.0

  val logger = Logger.getLogger(getClass.getName)

  def apply(
      featureVectors: Seq[(DenseVector[Double], Label)],
      hiddenLayerSizes: Seq[Int] = Seq.empty,
      outputLayerSize: Int = 1,
      activationFunction: DifferentiableFunction[Double, Double] = relu,
      alpha: Double = defaultAlpha,
      lambda: Double = defaultLambda,
      initializeOnStart: Boolean = false,
      epochs: Int = 1): Network = {
    new Network(
      featureVectors,
      hiddenLayerSizes,
      outputLayerSize,
      activationFunction,
      alpha,
      lambda,
      initializeOnStart,
      epochs)
  }
}

class Network(
    featureVectors: Seq[(DenseVector[Double], Label)],
    hiddenLayerSizes: Seq[Int],
    outputLayerSize: Int,
    activationFunction: DifferentiableFunction[Double, Double],
    alpha: Double,
    lambda: Double,
    initializeOnStart: Boolean,
    epochs: Int) {

  import Network.logger

  private val inputSizes = featureVectors.head._1.size +: hiddenLayerSizes
  private val outputSizes = hiddenLayerSizes :+ outputLayerSize

  val layers = inputSizes zip outputSizes map { case (input, output) =>
    FullyConnectedLayer(input, output, activationFunction, alpha, lambda, featureVectors.size)
  }

  if (initializeOnStart) {
    initialize()
  }

  def initialize(): Unit = 1 to epochs foreach { i =>
    if (i % 10 == 0) {
      val mse = calculateMeanSquareError(featureVectors(Random.nextInt(featureVectors.size))._1)
      logger.info(s"At epoch #$i, mse: $mse")
    }
    initializeSingleEpoch()
  }

  protected def initializeSingleEpoch(): Unit = {
    featureVectors.zipWithIndex foreach { case ((features, label), i) =>

      // forward propagate and fold activation of each layer into the next one
      val output = layers.foldLeft(features) { case (activations, layer) => layer.forward(activations) }

      // computes the output layer loss
      val outputLoss = (output - DenseVector(label.value.toDouble)) :* (output map { activationFunction.primeAtY })

      // backward propagate and update the weights
      // the result is the "input layer" loss, which can be ignored
      layers.foldRight(outputLoss) { case (layer, losses) => layer.backward(losses) }
    }
  }

  def classify(features: DenseVector[Double]): DenseVector[Double] = {
    layers.foldLeft(features) { case (activations, layer) => layer.forward(activations) }
  }

  def calculateMeanSquareError(features: DenseVector[Double]): Double = {
    val output = classify(features)
    output dot output
  }
}
