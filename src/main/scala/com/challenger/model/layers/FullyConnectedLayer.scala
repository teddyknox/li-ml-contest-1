package com.challenger.model.layers

import breeze.linalg.{Matrix, Vector => BreezeVector}

import scala.util.Random

object FullyConnectedLayer {

  def apply(
      weights: Matrix[Double],
      biases: BreezeVector[Double],
      activationFunction: Function[Double, Double]) = {
    new FullyConnectedLayer(weights, biases, activationFunction)
  }

  def apply(numInputs: Int, numOutputs: Int, activationFunction: Function[Double, Double]) = {
    new FullyConnectedLayer(
      Matrix.create(
        rows = numOutputs,
        cols = numInputs,
        data = Array.fill(numInputs * numOutputs) { Random.nextDouble() }),
      BreezeVector.zeros[Double](numOutputs),
      activationFunction)
  }
}

class FullyConnectedLayer(
  weightsT: Matrix[Double],
  biases: BreezeVector[Double],
  activationFunction: Function[Double, Double]) extends Layer {

  def forward(inputs: BreezeVector[Double]): BreezeVector[Double] = {
    ((weightsT * inputs) + biases) map { activationFunction.apply }
  }

  def backward(losses: BreezeVector[Double]): BreezeVector[Double] = {
    null
  }
}
