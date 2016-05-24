package com.challenger.model

import com.challenger.data.TrainingSetLine
import com.challenger.model.layers.FullyConnectedLayer

object Network {

  /**
    * Linear rectifier (ReLU) activation function applied
    */
  def relu(input: Double): Double = math.max(0, input)

  def apply(
      trainingSet: Seq[TrainingSetLine],
      hiddenLayerSizes: Seq[Int],
      outputLayerSize: Int = 1,
      activationFunction: Function[Double, Double] = relu): Network = {
    new Network(trainingSet, hiddenLayerSizes, outputLayerSize, activationFunction)
  }

  def apply(
      trainingSet: Seq[TrainingSetLine],
      hiddenLayerSize: Int,
      outputLayerSize: Int = 1,
      activationFunction: Function[Double, Double] = relu): Network = {
    new Network(trainingSet, Seq(hiddenLayerSize), outputLayerSize, activationFunction)
  }
}

class Network(
    trainingSet: Seq[TrainingSetLine],
    hiddenLayerSizes: Seq[Int],
    outputLayerSize: Int,
    activationFunction: Function[Double, Double]) {

  private val featureVectors = trainingSet map { _.features.vector }

  private val inputSizes = featureVectors.head.size +: hiddenLayerSizes
  private val outputSizes = hiddenLayerSizes :+ outputLayerSize

  val layers = inputSizes zip outputSizes map { case (input, output) => FullyConnectedLayer(input, output, activationFunction) }

  def initialize(): Unit = {
  }
}
