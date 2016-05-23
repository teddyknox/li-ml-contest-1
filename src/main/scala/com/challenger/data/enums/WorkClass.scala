package com.challenger.data.enums

sealed abstract class WorkClass(positiveIndex: Int) extends NonNumericFeature(8, positiveIndex)

object WorkClass extends EnumLike[WorkClass] {

  case object Private extends WorkClass(0)
  case object `Self-emp-not-inc` extends WorkClass(1)
  case object `Self-emp-inc` extends WorkClass(2)
  case object `Federal-gov` extends WorkClass(3)
  case object `Local-gov` extends WorkClass(4)
  case object `State-gov` extends WorkClass(5)
  case object `Without-pay` extends WorkClass(6)
  case object `Never-worked` extends WorkClass(7)

  override val values = Seq(
    Private,
    `Self-emp-not-inc`,
    `Self-emp-inc`,
    `Federal-gov`,
    `Local-gov`,
    `State-gov`,
    `Without-pay`,
    `Never-worked`)
}
