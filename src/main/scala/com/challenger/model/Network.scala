package com.challenger.model

import java.util.logging.Logger

import breeze.linalg.{DenseMatrix, DenseVector, norm}
import com.challenger.data.enums.Label
import com.challenger.model.function.DifferentiableFunction
import com.challenger.model.function.DifferentiableFunction._
import com.challenger.model.layers.FullyConnectedLayer

object Network {

  val defaultAlpha = 0.1

  val defaultLambda = 0.1

  val logger = Logger.getLogger(getClass.getName)

  def apply(
      inputSize: Int,
      hiddenLayerSizes: Seq[Int] = Seq.empty,
      outputLayerSize: Int = 1,
      activationFunction: DifferentiableFunction[Double, Double] = relu,
      alpha: Double = defaultAlpha,
      lambda: Double = defaultLambda): Network = {
    new Network(inputSize, hiddenLayerSizes, outputLayerSize, activationFunction, alpha, lambda)
  }

  def apply(config: NetworkConfiguration): Network = {
    apply(
      config.inputSize,
      config.hiddenLayerSizes,
      config.outputLayerSize,
      config.activationFunction,
      config.alpha,
      config.lambda)
  }
}

class Network(
    inputSize: Int,
    hiddenLayerSizes: Seq[Int],
    outputLayerSize: Int,
    activationFunction: DifferentiableFunction[Double, Double],
    alpha: Double,
    lambda: Double) {

  private val inputSizes = inputSize +: hiddenLayerSizes
  private val outputSizes = hiddenLayerSizes :+ outputLayerSize

  val layers = inputSizes zip outputSizes map { case (input, output) =>
    FullyConnectedLayer(input, output, activationFunction, alpha, lambda)
  }

  def updateWeights(examples: Seq[(DenseVector[Double], Label)]): Double = {

    // FullyConnectedLayer(s) hold state, and update their weights, clear their gradient accumulators
    // when told to.

    val mse = examples.foldLeft(0.0) { case (mseAcc, (features, label)) =>
      // forward propagate and fold activation of each layer into the next one
      val output = layers.foldLeft(features) { case (activations, layer) => layer.forward(activations) }

      // computes the output layer error
      val error = DenseVector(label.value.toDouble) - output
      val outputError = (-1.0 * error) :* output.map(activationFunction.primeAtY)

      // backward propagate and update the weights
      // the result is the "input layer" loss, which can be ignored
      layers.foldRight(outputError) { case (layer, err) => layer.backward(err) }

      // keep track of mse
      mseAcc + (0.5 * (error dot error))
    }

    // update parameters using alpha and lambda regularization
    layers foreach { _.doUpdate() }

    // return mse for stats
    mse
  }

  def classify(features: DenseVector[Double]): DenseVector[Double] = {
    layers.foldLeft(features) { case (activations, layer) => layer.forward(activations) }
  }

  def calculateMeanSquareError(features: DenseVector[Double]): Double = {
    val output = classify(features)
    output dot output
  }
}
