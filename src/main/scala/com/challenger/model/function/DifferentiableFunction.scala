package com.challenger.model.function

trait DifferentiableFunction[-A, +B] extends Function[A, B] with Differentiability[A, B]

trait Differentiability[-A, +B] {
  def prime(v: A): B
}
