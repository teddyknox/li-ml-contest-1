package com.challenger.model.layers

import scala.collection.mutable.ArrayBuffer

class InputLayer(inputSize: Int) extends Layer {

  def forward(inputs: ArrayBuffer[Double]): ArrayBuffer[Double] = {

  }

  def backward(losses: ArrayBuffer[Double]): ArrayBuffer[Double] = ArrayBuffer()
}
