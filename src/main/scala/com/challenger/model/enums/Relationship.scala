package com.challenger.model.enums

sealed abstract class Relationship(positiveIndex: Int) extends NonNumericFeature(6, positiveIndex)

object Relationship extends EnumLike[Relationship] {

  case object Wife extends Relationship(0)
  case object `Own-child` extends Relationship(1)
  case object Husband extends Relationship(2)
  case object `Not-in-family` extends Relationship(3)
  case object `Other-relative` extends Relationship(4)
  case object Unmarried extends Relationship(5)

  override val values = Seq(
    Wife,
    `Own-child`,
    Husband,
    `Not-in-family`,
    `Other-relative`,
    Unmarried)
}
