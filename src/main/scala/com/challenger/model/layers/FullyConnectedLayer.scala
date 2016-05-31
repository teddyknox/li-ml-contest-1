package com.challenger.model.layers

import breeze.linalg.{DenseMatrix, DenseVector}
import com.challenger.model.function.DifferentiableFunction

import scala.util.Random

object FullyConnectedLayer {

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
      lambda: Double) = {
    new FullyConnectedLayer(
      DenseMatrix.tabulate(numOutputs, numInputs)({ case (row, col) =>
        Random.nextDouble() / 10.0
      }),
      DenseVector.zeros[Double](numOutputs),
      activationFunction,
      alpha,
      lambda)
  }
}

class FullyConnectedLayer(
    val weights: DenseMatrix[Double],
    val biases: DenseVector[Double],
    val activationFunction: DifferentiableFunction[Double, Double],
    val alpha: Double,
    val lambda: Double) extends Layer {

  // these are the inputs for THIS layer (activations from previous layer)
  private var _activations = Option.empty[DenseVector[Double]]
  private var weightDelta = DenseMatrix.zeros[Double](rows = weights.rows, cols = weights.cols)
  private var biasDelta = DenseVector.zeros[Double](biases.length)
  private var m = 0

  override def compute(inputs: DenseVector[Double]): DenseVector[Double] = {
    ((weights * inputs) + biases) map { activationFunction }
  }

  override def forward(inputs: DenseVector[Double]): DenseVector[Double] = {
    _activations = Some(inputs)
    compute(inputs)
  }

  /**
    * Takes in the gradient from previous layer, computes the gradient for this layer and return it.
    * This method will also update the weights for this layer for the next iteration of forward propagation.
    *
    * @param error These are error from the previous layer. Previous layer = layer 1 CLOSER to the output layer
    * @return gradient/error for this layer.
    */
  override def backward(error: DenseVector[Double]): DenseVector[Double] = {
    if (_activations.isEmpty) {
      sys.error("cannot run backward propagation without forward propagation being run for this iteration/layer.")
    }

    // remember how many examples we've seen so we can correctly update weights in the flushUpdates step.
    m += 1

    // partial derivatives to update the gradient
    val weightGradient = error * _activations.get.t
    val biasGradient = error

    // update the gradients
    weightDelta += weightGradient
    biasDelta += biasGradient

    // compute loss for this layer. this value will be returned so that next layer of neurons can continue to backward propagate.
    (weights.t * error) :* _activations.get.map(activationFunction.primeAtY)
  }

  def doUpdate(): Unit = {
    // update the weights based on gradient and regularization
    weights -= alpha * ((weightDelta / m.toDouble) + (lambda * weights))

    // update the biases based on gradient
     biases -= alpha * (biasDelta / m.toDouble)

    // Reset state
    weightDelta = DenseMatrix.zeros[Double](rows = weights.rows, cols = weights.cols)
    biasDelta = DenseVector.zeros[Double](biases.length)
    m = 0
  }
}
