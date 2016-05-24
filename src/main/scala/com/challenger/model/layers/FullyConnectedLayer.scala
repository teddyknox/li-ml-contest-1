package com.challenger.model.layers

import breeze.linalg._

import scala.util.Random

object FullyConnectedLayer {

  /**
    * Linear rectifier (ReLU) activation function applied
    */
  def relu(input: Double): Double = math.max(0, input)
}

class FullyConnectedLayer(
  weightsT: Matrix[Double],
  biases: Vector[Double])
  extends Layer {

  def this(numInputs: Int, numOutputs: Int) = {
    this(
      Matrix.create(
        rows = numOutputs,
        cols = numInputs,
        data = Array.fill(numInputs * numOutputs) { Random.nextDouble() }),
      Vector.zeros[Double](numOutputs))
  }

  def forward(inputs: Vector[Double]): Vector[Double] = {
    ((weightsT * inputs) + biases) map { FullyConnectedLayer.relu }
  }

  def backward(losses: Vector[Double]): Vector[Double] = {
    null
  }
}
