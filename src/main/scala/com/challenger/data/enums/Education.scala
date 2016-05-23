package com.challenger.data.enums

sealed abstract class Education(positiveIndex: Int) extends NonNumericFeature(16, positiveIndex)

object Education extends EnumLike[Education] {
  case object Bachelors extends Education(0)
  case object `Some-college` extends Education(1)
  case object `11th` extends Education(2)
  case object `HS-grad` extends Education(3)
  case object `Prof-school` extends Education(4)
  case object `Assoc-acdm` extends Education(5)
  case object `Assoc-voc` extends Education(6)
  case object `9th` extends Education(7)
  case object `7th-8th` extends Education(8)
  case object `12th` extends Education(9)
  case object `Masters` extends Education(10)
  case object `1st-4th` extends Education(11)
  case object `10th` extends Education(12)
  case object Doctorate extends Education(13)
  case object `5th-6th` extends Education(14)
  case object Preschool extends Education(15)

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
    Preschool)
}
