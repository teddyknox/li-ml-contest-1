package com.challenger

import com.challenger.model.Features
import scala.collection.mutable.ArrayBuffer

object Network {
  def transposedMatrixMultiply(matrix: ArrayBuffer[ArrayBuffer[Double]], vector: ArrayBuffer[Double]): Vector[Double] = {
    val acc = ArrayBuffer.fill(matrix.size)(0.0)
    for (i <- matrix.size) {
      for (j <- vector.size) {
        acc(i) += matrix(i)(j) * vector(j)
      }
    }
  }
}

trait Network {
  val shape: Seq[Int] = Seq(103, 10, 10, 2)

  val weights: Seq[ArrayBuffer[ArrayBuffer[Double]]] = Seq()
  val biases: Seq[ArrayBuffer[Double]] = Seq()

  for (i <- 0 to (shape.size - 1)) {
    weights :+ Vector.fill(shape(i), shape(i + 1))({ scala.util.Random.nextDouble() / shape(i) })
  }

  biases :+ Seq(Array())
  for (i <- 1 to (shape.size - 1)) {
    biases :+ Vector.fill(shape(i))
  }

  def forward(input: Features): Label = {
    for (layer <- 0 to shape.size) {

    }
  }
}
