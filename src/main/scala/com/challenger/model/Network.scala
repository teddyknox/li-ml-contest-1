package com.challenger.model

import com.challenger.model.layers.{FullyConnectedLayer, Layer}

import scala.collection.mutable.ArrayBuffer

object Network {

  def layers = {

    val trainingLayers: Seq[Layer] = Seq(
      new InputLayer(103),
      new FullyConnectedLayer(103, 40),
      new FullyConnectedLayer(40, 40),
      new FullyConnectedLayer(40, 2)
    )
  }

  def forward(input: ArrayBuffer[Double]): ArrayBuffer[Double] = {
    for (layer <- 0 to shape.size) {

    }
  }
}
