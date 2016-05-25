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

  /**
    * Say we have 3 features and outputs 1 label (numInputs = 3, numOutputs = 1).
    *
    * Weights should be [w1, w2, w3].
    * Input should be
    * i1
    * i2
    * i3
    * since DenseVector is vertical.
    *
    * Therefore, weights has 1 row, 3 columns. Therefore, cols = numInputs, rows = numOutputs
    */
  def apply(
      numInputs: Int,
      numOutputs: Int,
      activationFunction: DifferentiableFunction[Double, Double],
      alpha: Double,
      lambda: Double,
      m: Int) = {
    new FullyConnectedLayer(
      DenseMatrix.create(
        rows = numOutputs,
        cols = numInputs,
        data = Array.fill(numInputs * numOutputs) { Random.nextDouble() / 100.0 }),
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

  /**
    * forward propagate without updating activations.
    * this will be used during classification instead of training.
    */
  override def compute(inputs: DenseVector[Double]): DenseVector[Double] = {
    (weights * inputs) + biases
  }

  override def forward(inputs: DenseVector[Double]): DenseVector[Double] = {
    val z = compute(inputs)
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
  override def backward(losses: DenseVector[Double]): DenseVector[Double] = {

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

  def activations = _activations getOrElse {
    sys.error("activations are not yet computed for this layer")
  }

  def activationPrimes = _activationPrimes getOrElse {
    sys.error("activation' are not yet computed for this layer")
  }
}
