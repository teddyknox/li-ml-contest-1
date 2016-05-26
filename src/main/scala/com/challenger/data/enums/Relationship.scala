package com.challenger.data.enums

sealed abstract class Relationship(positiveIndex: Option[Int]) extends NonNumericFeature(6, positiveIndex)

object Relationship extends EnumLike[Relationship] {

  case object Wife extends Relationship(Some(0))
  case object `Own-child` extends Relationship(Some(1))
  case object Husband extends Relationship(Some(2))
  case object `Not-in-family` extends Relationship(Some(3))
  case object `Other-relative` extends Relationship(Some(4))
  case object Unmarried extends Relationship(Some(5))
  case object ? extends Relationship(None)

  override val values = Seq(
    Wife,
    `Own-child`,
    Husband,
    `Not-in-family`,
    `Other-relative`,
    Unmarried,
    ?)
}
