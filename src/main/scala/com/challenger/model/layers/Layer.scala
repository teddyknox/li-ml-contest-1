package com.challenger.model.layers

import breeze.linalg.DenseVector

trait Layer {
  def forward(inputs: DenseVector[Double]): DenseVector[Double]
  def backward(losses: DenseVector[Double]): DenseVector[Double]
}
