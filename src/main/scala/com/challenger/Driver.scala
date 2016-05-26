package com.challenger

import java.io.PrintWriter
import java.util.logging.Logger

import com.challenger.data.enums.Label
import com.challenger.loader.DataLoader._
import com.challenger.model.Network
import com.challenger.model.function._

object Driver extends App {

  val logger = Logger.getLogger(getClass.getName)

  val trainingPath = Option(System.getProperty("training.classpath")) getOrElse "training.tsv"
  val testPath = Option(System.getProperty("test.classpath")) getOrElse "test.tsv"
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

  val regularizationParam = Option(System.getProperty("regularization.param"))
    .map { _.toDouble }
    .getOrElse { Network.defaultLambda }

  val epochs = Option(System.getProperty("epochs"))
    .map { _.toInt }
    .getOrElse { 1 }

  logger.info("Loading training set...")
  val trainingSet = load(trainingPath) { parseTrainingSet }

  logger.info("Loading testing set...")
  val testSet = load(testPath) { parseTestSet }

  logger.info("Initializing neural network...")
  val featureVectors = trainingSet map { line => line.features.vector -> line.label }
  val neuralNetwork = Network(
    featureVectors = featureVectors,
    hiddenLayerSizes = hiddenLayers,
    activationFunction = activationFunction,
    alpha = learningRate,
    lambda = regularizationParam,
    initializeOnStart = true,
    epochs = epochs)

  logger.info("Classifying test set...")
  val outputs = testSet map { _.features.vector } map { neuralNetwork.classify }
  val results = outputs map { _(0) } map { v => v + "\t" + Label.get(v) }

  val outputWriter = new PrintWriter(outputFilePath, "UTF-8")

  try {
    results foreach outputWriter.println
  } finally {
    outputWriter.close()
  }
}
