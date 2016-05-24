package com.challenger.model.layers

import breeze.linalg.{DenseMatrix, DenseVector}
import com.challenger.model.function.DifferentiableFunction

import scala.util.Random

object FullyConnectedLayer {

  def apply(
      weights: DenseMatrix[Double],
      biases: DenseVector[Double],
      activationFunction: DifferentiableFunction[Double, Double],
      alpha: Double,
      lambda: Double) = {
    new FullyConnectedLayer(weights, biases, activationFunction, alpha, lambda)
  }

  def apply(
      numInputs: Int,
      numOutputs: Int,
      activationFunction: DifferentiableFunction[Double, Double],
      alpha: Double,
      lambda: Double) = {
    new FullyConnectedLayer(
      DenseMatrix.create(
        rows = numOutputs,
        cols = numInputs,
        data = Array.fill(numInputs * numOutputs) { Random.nextDouble() }),
      DenseVector.zeros[Double](numOutputs),
      activationFunction,
      alpha,
      lambda)
  }
}

class FullyConnectedLayer(
    weightsT: DenseMatrix[Double],
    biases: DenseVector[Double],
    activationFunction: DifferentiableFunction[Double, Double],
    alpha: Double,
    lambda: Double) extends Layer {

  private[this] var _activations = Option.empty[DenseVector[Double]]
  private[this] var _activationPrimes = Option.empty[DenseVector[Double]]

  def forward(inputs: DenseVector[Double]): DenseVector[Double] = {
    val z = (weightsT * inputs) + biases
    _activations = Some(z map activationFunction.apply)
    _activationPrimes = Some(z map activationFunction.prime)
    _activations.get
  }

  def backward(losses: DenseVector[Double]): DenseVector[Double] = {

    if (_activations.isEmpty || _activationPrimes.isEmpty) {
      sys.error("cannot run backward propagation without forward propagation being run for this iteration/layer.")
    }

    val delta = (weightsT.t * losses) :* _activationPrimes.get

    // TODO: regularization adjustments


    null
  }
}
