package com.challenger.model.function

sealed abstract class DifferentiableFunction[-A, +B](val name: String) extends Function[A, B] with Differentiability[A, B]

trait Differentiability[-A, +B] {
  def primeAtX(x: A): B
  def primeAtY(y: A): B
}

object DifferentiableFunction {

  case object relu extends DifferentiableFunction[Double, Double]("relu") {
    override def apply(x: Double): Double = math.max(0, x)
    override def primeAtX(x: Double): Double = {
      if (x < 0.0) 0.0
      else if (x > 0.0) 1.0
      else sys.error("ReLU function is not differentiable at x = 0")
    }
    override def primeAtY(y: Double): Double = {
      if (y == 0.0) 0.0
      else if (y > 0.0) 1.0
      else sys.error("ReLU cannot return values < 0.")
    }
  }

  case object sigmoid extends DifferentiableFunction[Double, Double]("sigmoid") {
    override def apply(x: Double): Double = breeze.numerics.sigmoid.sigmoidImplDouble.apply(x)
    override def primeAtX(x: Double): Double = apply(x) * (1.0 - apply(x))
    override def primeAtY(y: Double): Double = y * (1.0 - y)
  }

  case object tanh extends DifferentiableFunction[Double, Double]("tanh") {
    override def apply(v: Double): Double = breeze.numerics.tanh.tanDoubleImpl.apply(v)
    override def primeAtX(v: Double): Double = {
      val t = apply(v)
      1.0 - (t * t)
    }
    override def primeAtY(y: Double): Double = 1.0 - (y * y)
  }

  val values = Seq(relu, sigmoid, tanh)

  def valueOf(name: String): DifferentiableFunction[Double, Double] = {
    values find { _.name equalsIgnoreCase name } getOrElse sys.error(s"$name is not a valid neural network activation function. use [relu, sigmoid, tanh].")
  }
}
