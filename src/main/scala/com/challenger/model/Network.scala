package com.challenger.model

import com.challenger.model.layers.{FullyConnectedLayer, InputLayer, Layer}

import scala.collection.mutable.ArrayBuffer

object Network {

//  val layers: Seq[Layer]

  def newNetwork(): Seq[Layer] = {
    Seq(
      new FullyConnectedLayer(103, 40),
      new FullyConnectedLayer(40, 40),
      new FullyConnectedLayer(40, 1))
  }


}

class Network(layers: Seq[Layer]) {

//  def forward(input: ArrayBuffer[Double]): ArrayBuffer[Double] = {
//    layers foldLeft(input) { (intermediates, layer) =>
//      layer.forward(intermediates)
//    }
//  }

}
