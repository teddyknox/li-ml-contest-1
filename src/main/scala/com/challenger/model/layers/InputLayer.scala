package com.challenger.model.layers

import breeze.linalg.Vector

class InputLayer(inputSize: Int) extends Layer {

  def forward(inputs: Vector[Double]): Vector[Double] = {
    null
  }

  def backward(losses: Vector[Double]): Vector[Double] = null
}
