package com.challenger.data.enums

sealed abstract class WorkClass(positiveIndex: Option[Int]) extends NonNumericFeature(8, positiveIndex)

object WorkClass extends EnumLike[WorkClass] {

  case object Private extends WorkClass(Some(0))
  case object `Self-emp-not-inc` extends WorkClass(Some(1))
  case object `Self-emp-inc` extends WorkClass(Some(2))
  case object `Federal-gov` extends WorkClass(Some(3))
  case object `Local-gov` extends WorkClass(Some(4))
  case object `State-gov` extends WorkClass(Some(5))
  case object `Without-pay` extends WorkClass(Some(6))
  case object `Never-worked` extends WorkClass(Some(7))
  case object ? extends WorkClass(None)

  override val values = Seq(
    Private,
    `Self-emp-not-inc`,
    `Self-emp-inc`,
    `Federal-gov`,
    `Local-gov`,
    `State-gov`,
    `Without-pay`,
    `Never-worked`,
    ?)
}
