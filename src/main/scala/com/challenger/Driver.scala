package com.challenger

import java.io.PrintWriter
import java.util.logging.Logger

import com.challenger.boosting.{BoostingStrategy, Ensemble}
import com.challenger.data.enums.Label
import com.challenger.loader.DataLoader._
import com.challenger.model.{Network, NetworkConfiguration}
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

  val batchSize = Option(System.getProperty("batch.size"))
    .map { _.toInt }
    .getOrElse { 30 }

  val ensembleSize = Option(System.getProperty("ensemble.size"))
    .map { _.toInt }
    .getOrElse { Ensemble.Defaults.ensembleSize }

  val baggingFraction = Option(System.getProperty("bagging.fraction"))
    .map { _.toDouble }
    .getOrElse { Ensemble.Defaults.baggingFraction }

  val boostingStrategy = Option(System.getProperty("boosting.strategy"))
    .map { BoostingStrategy.valueOf }
    .getOrElse { Ensemble.Defaults.boostingStrategy }

  logger.info("Loading training set...")
  val trainingSet = load(trainingPath) { parseTrainingSet }
  val examples = trainingSet.map({ line => line.features.vector -> line.label })

  logger.info("Loading testing set...")
  val testSet = load(testPath) { parseTestSet }

  logger.info("Initializing ensemble...")
  val ensemble = Ensemble(
    ensembleSize = ensembleSize,
    baggingFraction = baggingFraction,
    boostingStrategy = boostingStrategy,
    networkConfig = NetworkConfiguration(
      inputSize = examples(0)._1.length,
      hiddenLayerSizes = hiddenLayers,
      outputLayerSize = 1,
      activationFunction = activationFunction,
      alpha = learningRate,
      lambda = regularizationParam,
      epochs = epochs,
      batchSize = batchSize),
    trainingSet = trainingSet)

  logger.info("Classifying test set...")
  val predictions = testSet map { _.features.vector } map { ensemble.classify } map { output =>
    Label.get(output(0))
  }

  if (testSet.head.labelOpt.nonEmpty) {
    val actual = testSet flatMap { _.labelOpt }

    if (actual.size != predictions.size) {
      sys.error("predictions.size != actual.size")
    } else {
      val zipped = predictions zip actual
      val total = zipped.size
      val correct = zipped count { case (prediction, expected) => prediction == expected }
      val percentage = correct.toDouble / total.toDouble * 100.0
      logger.info(f"Got $correct/$total ($percentage%.2f%%) correct.")
    }
  }

  val output = "{\"guesses\":[" + predictions.map(s => "\"" + s.toString + "\"").mkString(", ") + "]}"
  val outputWriter = new PrintWriter(outputFilePath, "UTF-8")
  try {
    outputWriter.write(output)
  } finally {
    outputWriter.close()
  }
}
