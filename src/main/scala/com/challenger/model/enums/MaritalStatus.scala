package com.challenger.model.enums

sealed abstract class MaritalStatus(positiveIndex: Int) extends NonNumericFeature(7, positiveIndex)

object MaritalStatus extends EnumLike[MaritalStatus] {

  case object `Married-civ-spouse` extends MaritalStatus(0)
  case object Divorced extends MaritalStatus(1)
  case object `Never-married` extends MaritalStatus(2)
  case object Separated extends MaritalStatus(3)
  case object Widowed extends MaritalStatus(4)
  case object `Married-spouse-absent` extends MaritalStatus(5)
  case object `Married-AF-spouse` extends MaritalStatus(6)

  override val values = Seq(
    `Married-civ-spouse`,
    Divorced,
    `Never-married`,
    Separated,
    Widowed,
    `Married-spouse-absent`,
    `Married-AF-spouse`)
}
