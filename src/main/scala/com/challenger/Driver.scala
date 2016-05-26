package com.challenger

import java.io.PrintWriter
import java.util.logging.Logger

import com.challenger.loader.DataLoader._
import com.challenger.model.Network
import com.challenger.model.function._

object Driver extends App {

  val logger = Logger.getLogger(getClass.getName)

  val trainingPath = Option(System.getProperty("training.classpath")) getOrElse "training.tsv"
  val testPath = Option(System.getProperty("test.classpath")) getOrElse "test.tsv"
  val outputFilePath = Option(System.getProperty("output.path")) getOrElse sys.error("Missing required property output.path.")

  val hiddenLayers = Option(System.getProperty("hidden.layers"))
    .map { _ split "," }
    .map { arr => arr map { _.toInt } }
    .map { _.to[Vector] }
    .getOrElse { Seq(40) }

  val activationFunction = Option(System.getProperty("activation.function"))
    .map { getAvailableFunction }
    .getOrElse { relu }

  val learningRate = Option(System.getProperty("learning.rate"))
    .map { _.toDouble }
    .getOrElse { Network.defaultAlpha }

  val regularizationParam = Option(System.getProperty("regularization.param"))
    .map { _.toDouble }
    .getOrElse { Network.defaultLambda }

  logger.info("Loading training set...")
  val trainingSet = load(trainingPath) { parseTrainingSet }

  logger.info("Loading testing set...")
  val testSet = load(testPath) { parseTestSet }

  logger.info("Initializing neural network...")
  val neuralNetwork = Network(
    trainingSet = trainingSet,
    hiddenLayerSizes = hiddenLayers,
    activationFunction = activationFunction,
    alpha = learningRate,
    lambda = regularizationParam,
    initializeOnStart = true)

  logger.info("Classifying test set...")
  val labels = testSet map { _.features.vector } map { neuralNetwork.classify } map { _.toString }

  val writer = new PrintWriter(outputFilePath, "UTF-8")

  try {
    labels foreach writer.println
  } finally {
    writer.close()
  }
}
