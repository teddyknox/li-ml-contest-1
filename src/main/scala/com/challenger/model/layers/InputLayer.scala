package com.challenger.model.layers

import breeze.linalg.DenseVector

class InputLayer(inputSize: Int) extends Layer {

  def forward(inputs: DenseVector[Double]): DenseVector[Double] = {
    null
  }

  def backward(losses: DenseVector[Double]): DenseVector[Double] = null
}
