package com.challenger.data.enums

sealed abstract class MaritalStatus(positiveIndex: Option[Int]) extends NonNumericFeature(7, positiveIndex)

object MaritalStatus extends EnumLike[MaritalStatus] {

  case object `Married-civ-spouse` extends MaritalStatus(Some(0))
  case object Divorced extends MaritalStatus(Some(1))
  case object `Never-married` extends MaritalStatus(Some(2))
  case object Separated extends MaritalStatus(Some(3))
  case object Widowed extends MaritalStatus(Some(4))
  case object `Married-spouse-absent` extends MaritalStatus(Some(5))
  case object `Married-AF-spouse` extends MaritalStatus(Some(6))
  case object ? extends MaritalStatus(None)

  override val values = Seq(
    `Married-civ-spouse`,
    Divorced,
    `Never-married`,
    Separated,
    Widowed,
    `Married-spouse-absent`,
    `Married-AF-spouse`,
    ?)
}
