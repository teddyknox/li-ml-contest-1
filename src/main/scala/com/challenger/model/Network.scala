package com.challenger.model

import breeze.numerics.sigmoid
import com.challenger.model.layers.FullyConnectedLayer

object Network {

  def apply(
      layerSizes: Seq[Int] = Seq(103, 40, 40, 1),
      activationFunction: Function[Double, Double] = sigmoid.sigmoidImplDouble.apply): Network = {
    new Network(layerSizes, activationFunction)
  }
}

class Network(layerSizes: Seq[Int], activationFunction: Function[Double, Double]) {
  val layers = layerSizes.dropRight(1)
    .zip { layerSizes.drop(1) }
    .map { case (inputs, outputs) => FullyConnectedLayer(inputs, outputs) }
}
