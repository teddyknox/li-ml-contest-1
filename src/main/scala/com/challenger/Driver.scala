package com.challenger

import com.challenger.model.Features

object Driver extends App {
  private val BatchSize = 30

  private def train(filename: String) = {
    val data: Iterator[Features] = null
    data
      .grouped(BatchSize)
      .foreach { batch =>
        batch
      }
  }
}
