package com.challenger.loader

import com.challenger.data.{Line, TestSetLine, TrainingSetLine}
import com.challenger.parser.DataParser

import scala.io.Source

object DataLoader extends DataParser {

  def load[T <: Line](classpath: String)(f: String => T) = {
    val source = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(classpath))
    try {
      source.getLines().to[Vector] map f
    } finally {
      source.close()
    }
  }

  def loadTrainingSet(): Seq[TrainingSetLine] = load("training.tsv") { parseTrainingSet }

  def loadTestSet(): Seq[TestSetLine] = load("test.tsv") { parseTestSet }
}
