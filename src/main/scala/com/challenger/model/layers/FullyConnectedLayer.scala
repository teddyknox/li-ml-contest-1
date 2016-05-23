package com.challenger.model.layers

import scala.collection.mutable.ArrayBuffer

object FullyConnectedLayer {

  /**
    * Linear rectifier (ReLU) activation function applied
    */
  def relu(input: Double): Double = math.max(0, input)

  /**
    * Computes `Ay`, where `A` is the matrix parameter and `y` is the vector parameter.
    *
    * Given horizontal input vector x and a weight matrix W, the linear combination step of a
    * neural network's forward pass is computed as `xW`, but because the impedance mismatch of
    * matrix indexing and array indexing memory structures, it's much more cache-efficient to
    * transpose both inputs and necessarily swap their ordering in the multiply (W_t)(x_t)
    * to achieve the same result.
    *
    * @param matrix a 2D ArrayBuffer with shape (m, n), representing an m by n matrix
    * @param vector a 1D ArrayBuffer with shape (n), representing an n-by-1 vertical vector
    * @return a 1D ArrayBuffer with shape (n), representing an n-by-1 vertical vector
    */
  def matrixMultiply(
    matrix: ArrayBuffer[ArrayBuffer[Double]],
    vector: ArrayBuffer[Double]): ArrayBuffer[Double] = {

    ArrayBuffer.tabulate[Double](matrix.size) { i =>
      (0 until vector.size) map { j =>
        matrix(i)(j) * vector(j)
      } sum
    }
  }

  def vectorAdd(a: ArrayBuffer[Double], b: ArrayBuffer[Double]): ArrayBuffer[Double] = {
    ArrayBuffer.tabulate(a.size) { i => a(i) + b(i) }
  }
}

class FullyConnectedLayer(
  weightsT: ArrayBuffer[ArrayBuffer[Double]],
  biases: ArrayBuffer[Double])
  extends Layer {

  def this(numInputs: Int, numOutputs: Int) = {
    this(
      ArrayBuffer.tabulate(numInputs, numOutputs)({ (a, b) =>
        scala.util.Random.nextDouble() / a
      }),
      ArrayBuffer.fill(numOutputs)(0.0))
  }

  def forward(inputs: ArrayBuffer[Double]): ArrayBuffer[Double] = {
    FullyConnectedLayer.vectorAdd(
      FullyConnectedLayer.matrixMultiply(weightsT, inputs),
      biases) map FullyConnectedLayer.relu
  }

  def backward(losses: ArrayBuffer[Double]): ArrayBuffer[Double] = {

  }
}
