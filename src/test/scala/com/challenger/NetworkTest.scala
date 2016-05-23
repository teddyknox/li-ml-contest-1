package com.challenger

import com.challenger.model.Network
import org.scalatest._

import scala.collection.mutable.ArrayBuffer

class NetworkTest extends FunSpec with Matchers {
  describe("Network") {
    it("should correctly multiply a vector and a matrix") {
      val a = ArrayBuffer[ArrayBuffer[Double]](
        ArrayBuffer[Double](1, 2, 3),
        ArrayBuffer[Double](4, 5, 6),
        ArrayBuffer[Double](7, 8, 9))
      val b = ArrayBuffer[Double](1, 2, 3)
      Network.transposedMatrixMultiply(a, b) shouldBe ArrayBuffer(14, 32, 50)
    }
  }
}

