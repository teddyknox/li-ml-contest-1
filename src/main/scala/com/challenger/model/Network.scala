package com.challenger.model

import java.util.logging.Logger

import breeze.linalg.DenseVector
import com.challenger.data.TrainingSetLine
import com.challenger.data.enums.Label
import com.challenger.model.function._
import com.challenger.model.layers.FullyConnectedLayer

object Network {

  val defaultAlpha = 0.1

  val defaultLambda = 100.0

  val logger = Logger.getLogger(getClass.getName)

  def apply(
      trainingSet: Seq[TrainingSetLine],
      hiddenLayerSizes: Seq[Int] = Seq.empty,
      outputLayerSize: Int = 1,
      activationFunction: DifferentiableFunction[Double, Double] = relu,
      alpha: Double = defaultAlpha,
      lambda: Double = defaultLambda,
      initializeOnStart: Boolean = false): Network = {
    new Network(trainingSet, hiddenLayerSizes, outputLayerSize, activationFunction, alpha, lambda, initializeOnStart)
  }
}

class Network(
    trainingSet: Seq[TrainingSetLine],
    hiddenLayerSizes: Seq[Int],
    outputLayerSize: Int,
    activationFunction: DifferentiableFunction[Double, Double],
    alpha: Double,
    lambda: Double,
    initializeOnStart: Boolean) {

  import Network.logger

  private val featureVectors = trainingSet map { line => line.features.vector -> line.label }

  private val inputSizes = featureVectors.head._1.size +: hiddenLayerSizes
  private val outputSizes = hiddenLayerSizes :+ outputLayerSize

  val layers = inputSizes zip outputSizes map { case (input, output) =>
    FullyConnectedLayer(input, output, activationFunction, alpha, lambda, trainingSet.size)
  }

  if (initializeOnStart) {
    initialize()
  }

  def initialize(): Unit = {
    featureVectors.zipWithIndex foreach { case ((features, label), i) =>

      if (i % 100 == 0) {
        logger.info(s"working with the training set line #$i...")
      }

      // forward propagate and fold activation of each layer into the next one
      val output = layers.foldLeft(features) { case (activations, layer) => layer.forward(activations) }

      // computes the output layer loss
      val outputLoss = (output - DenseVector(label.value.toDouble)) :* layers.last.activationPrimes

      // backward propagate and update the weights
      // the result is the "input layer" loss, which can be ignored
      layers.reverse.foldLeft(outputLoss) { case (losses, layer) => layer.backward(losses) }
    }
  }

  def classify(features: DenseVector[Double]): Label = {
    val output = layers.foldLeft(features) { case (activations, layer) => layer.forward(activations) }
    // TODO: figure out what to do with tanh/relu
    Label.get(output(0))
  }
}
