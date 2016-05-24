package com.challenger.model

package object function {

  object relu extends DifferentiableFunction[Double, Double] {
    override def apply(v: Double): Double = math.max(0, v)
    override def prime(v: Double): Double = {
      if (v < 0) 0.0
      else if (v > 0) 1.0
      else sys.error("ReLU function is not differentiable at x = 0")
    }
  }

  object sigmoid extends DifferentiableFunction[Double, Double] {
    override def apply(v: Double): Double = breeze.numerics.sigmoid.sigmoidImplDouble.apply(v)
    override def prime(v: Double): Double = apply(v) * (1 - apply(v))
  }

  object tanh extends DifferentiableFunction[Double, Double] {
    override def apply(v: Double): Double = breeze.numerics.tanh.tanDoubleImpl.apply(v)
    override def prime(v: Double): Double = {
      val t = apply(v)
      1 - (t * t)
    }
  }
}
