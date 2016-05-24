package com.challenger.model.layers

import breeze.linalg.Vector

trait Layer {
  def forward(inputs: Vector[Double]): Vector[Double]
  def backward(losses: Vector[Double]): Vector[Double]
}
