package com.challenger.loader

import com.challenger.data.{TestSetLine, TrainingSetLine}
import com.challenger.parser.DataParser

import scala.io.Source

object DataLoader extends DataParser {

  def loadTrainingSet(): Seq[TrainingSetLine] = {
    val source = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("training.tsv"))
    try {
      source.getLines().to[Vector] map { parseTrainingSet }
    } finally {
      source.close()
    }
  }

  def loadTestSet(): Seq[TestSetLine] = {
    val source = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("training.tsv"))
    try {
      source.getLines().to[Vector] map { parseTestSet }
    } finally {
      source.close()
    }
  }
}
