package com.challenger.model

import com.challenger.model.function.DifferentiableFunction

case class NetworkConfiguration(
  inputSize: Int,
  hiddenLayerSizes: Seq[Int],
  outputLayerSize: Int,
  activationFunction: DifferentiableFunction[Double, Double],
  alpha: Double,
  lambda: Double,
  epochs: Int,
  batchSize: Int)
