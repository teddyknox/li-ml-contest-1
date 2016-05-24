package com.challenger.model.layers

import breeze.linalg.{Matrix, Vector => BreezeVector}

import scala.util.Random

object FullyConnectedLayer {

  /**
    * Linear rectifier (ReLU) activation function applied
    */
  def relu(input: Double): Double = math.max(0, input)

  def apply(weights: Matrix[Double], biases: BreezeVector[Double]) = new FullyConnectedLayer(weights, biases)

  def apply(numInputs: Int, numOutputs: Int) = new FullyConnectedLayer(
    Matrix.create(
      rows = numOutputs,
      cols = numInputs,
      data = Array.fill(numInputs * numOutputs) { Random.nextDouble() }),
    BreezeVector.zeros[Double](numOutputs))
}

class FullyConnectedLayer(weightsT: Matrix[Double], biases: BreezeVector[Double]) extends Layer {

  def forward(inputs: BreezeVector[Double]): BreezeVector[Double] = {
    ((weightsT * inputs) + biases) map { FullyConnectedLayer.relu }
  }

  def backward(losses: BreezeVector[Double]): BreezeVector[Double] = {
    null
  }
}
