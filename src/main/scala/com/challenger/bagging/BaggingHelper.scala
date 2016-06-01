package com.challenger.bagging

import scala.util.Random

object BaggingHelper {

  implicit class Bagging[T](val items: Seq[T]) {
    def sample(n: Int): Seq[T] = Random.shuffle(items).take(n)
  }
}
