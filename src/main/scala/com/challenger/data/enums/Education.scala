package com.challenger.data.enums

sealed abstract class Education(positiveIndex: Option[Int]) extends NonNumericFeature(16, positiveIndex)

object Education extends EnumLike[Education] {
  case object Bachelors extends Education(Some(0))
  case object `Some-college` extends Education(Some(1))
  case object `11th` extends Education(Some(2))
  case object `HS-grad` extends Education(Some(3))
  case object `Prof-school` extends Education(Some(4))
  case object `Assoc-acdm` extends Education(Some(5))
  case object `Assoc-voc` extends Education(Some(6))
  case object `9th` extends Education(Some(7))
  case object `7th-8th` extends Education(Some(8))
  case object `12th` extends Education(Some(9))
  case object `Masters` extends Education(Some(10))
  case object `1st-4th` extends Education(Some(11))
  case object `10th` extends Education(Some(12))
  case object Doctorate extends Education(Some(13))
  case object `5th-6th` extends Education(Some(14))
  case object Preschool extends Education(Some(15))
  case object ? extends Education(None)

  override val values = Seq(
    Bachelors,
    `Some-college`,
    `11th`,
    `HS-grad`,
    `Prof-school`,
    `Assoc-acdm`,
    `Assoc-voc`,
    `9th`,
    `7th-8th`,
    `12th`,
    `Masters`,
    `1st-4th`,
    `10th`,
    Doctorate,
    `5th-6th`,
    Preschool,
    ?)
}
