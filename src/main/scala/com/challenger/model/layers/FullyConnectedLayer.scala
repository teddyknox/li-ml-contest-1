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
      lambda: Double,
      m: Int) = {
    new FullyConnectedLayer(weights, biases, activationFunction, alpha, lambda, m)
  }

  def apply(
      numInputs: Int,
      numOutputs: Int,
      activationFunction: DifferentiableFunction[Double, Double],
      alpha: Double,
      lambda: Double,
      m: Int) = {
    new FullyConnectedLayer(
      DenseMatrix.create(
        rows = numInputs,
        cols = numOutputs,
        data = Array.fill(numInputs * numOutputs) { Random.nextDouble() }),
      DenseVector.zeros[Double](numOutputs),
      activationFunction,
      alpha,
      lambda,
      m)
  }
}

class FullyConnectedLayer(
    weights: DenseMatrix[Double],
    biases: DenseVector[Double],
    activationFunction: DifferentiableFunction[Double, Double],
    alpha: Double,
    lambda: Double,
    m: Int) extends Layer {

  private[this] var _activations = Option.empty[DenseVector[Double]]
  private[this] var _activationPrimes = Option.empty[DenseVector[Double]]

  private val weightGrad = DenseMatrix.zeros[Double](rows = weights.rows, cols = weights.cols)
  private val biasGrad = DenseVector.zeros[Double](biases.length)

  def forward(inputs: DenseVector[Double]): DenseVector[Double] = {
    val z = (weights * inputs) + biases
    _activations = Some(z map activationFunction.apply)
    _activationPrimes = Some(z map activationFunction.prime)
    _activations.get
  }

  /**
    * Takes in the gradient from previous layer, computes the gradient for this layer and return it.
    * This method will also update the weights for this layer for the next iteration of forward propagation.
    *
    * @param losses These are losses from the previous layer. Previous layer = layer 1 CLOSER to the output layer
    * @return gradient/losses for this layer.
    */
  def backward(losses: DenseVector[Double]): DenseVector[Double] = {

    if (_activations.isEmpty || _activationPrimes.isEmpty) {
      sys.error("cannot run backward propagation without forward propagation being run for this iteration/layer.")
    }

    // compute loss for this layer. this value will be returned so that next layer of neurons can continue to backward propagate.
    val delta = (weights.t * losses) :* _activationPrimes.get

    // partial derivatives to update the gradient
    val gradientDeltas = losses * _activations.get.t
    val biasDeltas = losses

    // update the gradients
    weightGrad += gradientDeltas
    biasGrad += biasDeltas

    // update the weights based on gradient and regularization
    weights -= alpha * ((weightGrad / m.toDouble) + (weights * lambda))

    // update the biases based on gradient
    biases -= alpha * (biasGrad / m.toDouble)

    delta
  }
}
