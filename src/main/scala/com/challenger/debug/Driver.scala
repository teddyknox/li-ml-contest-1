package com.challenger.debug

import java.io.PrintWriter
import java.util.logging.Logger

import breeze.linalg.DenseVector
import com.challenger.data.enums.{Label, Sex}
import com.challenger.loader.DataLoader._
import com.challenger.model.Network
import com.challenger.model.function._

object Driver extends App {

  val logger = Logger.getLogger(getClass.getName)

  val trainingPath = "debug-training-set.tsv"
  val outputFilePath = Option(System.getProperty("output.path")) getOrElse sys.error("Missing required property output.path.")

  val hiddenLayers = Option(System.getProperty("hidden.layers"))
    .map { _ split "," filter { _.trim.nonEmpty } }
    .map { arr => arr map { _.toInt } }
    .map { _.to[Vector] }
    .getOrElse { Seq.empty }

  val activationFunction = Option(System.getProperty("activation.function"))
    .map { DifferentiableFunction.valueOf }
    .getOrElse { DifferentiableFunction.relu }

  val learningRate = Option(System.getProperty("learning.rate"))
    .map { _.toDouble }
    .getOrElse { Network.defaultAlpha }
  logger.info(s"Using learning rate alpha: $learningRate")

  val regularizationParam = Option(System.getProperty("regularization.param"))
    .map { _.toDouble }
    .getOrElse { Network.defaultLambda }
  logger.info(s"Using regularization parameter lambda: $regularizationParam")

  logger.info("Loading training set...")
  val featureVectors = load(trainingPath) { line =>
    val Array(feature, label) = line split "\\s+"
    DenseVector(feature.toDouble) -> Label.valueOf(label)
  }

  logger.info("Initializing neural network...")
  val neuralNetwork = Network(
    featureVectors = featureVectors,
    hiddenLayerSizes = hiddenLayers,
    activationFunction = activationFunction,
    alpha = learningRate,
    lambda = regularizationParam,
    initializeOnStart = true)

  logger.info("Classifying test set...")
  val outputs = 0 to 1000 map { _.toDouble } map { d => DenseVector(d) } map { neuralNetwork.classify }
  val results = outputs map { _(0) } map { v => v + "\t" + Label.get(v) }

  val outputWriter = new PrintWriter(outputFilePath, "UTF-8")
  val weightsWriter = new PrintWriter("/Users/bkuang/Documents/temporary-files/kaggle/weights.txt", "UTF-8")
  val biasesWriter = new PrintWriter("/Users/bkuang/Documents/temporary-files/kaggle/biases.txt", "UTF-8")

  try {
    results foreach outputWriter.println
    neuralNetwork.layers foreach { layer =>
      weightsWriter.println(layer.weights)
      weightsWriter.println("=" * 50)

      biasesWriter.println(layer.biases)
      biasesWriter.println("=" * 50)
    }
  } finally {
    outputWriter.close()
    weightsWriter.close()
    biasesWriter.close()
  }
}

