package com.challenger.model.layers

import breeze.linalg.DenseVector

class InputLayer(inputSize: Int) extends Layer {
  override def compute(inputs: DenseVector[Double]): DenseVector[Double] = null
  override def forward(inputs: DenseVector[Double]): DenseVector[Double] = null
  override def backward(losses: DenseVector[Double]): DenseVector[Double] = null
}
