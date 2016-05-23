package com.challenger.model.layers

import scala.collection.mutable.ArrayBuffer

trait Layer {
  def forward(inputs: ArrayBuffer[Double]): ArrayBuffer[Double]
  def backward(losses: ArrayBuffer[Double]): ArrayBuffer[Double]
}
