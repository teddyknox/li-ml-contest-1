package com.challenger.data.enums

sealed abstract class Race(positiveIndex: Int) extends NonNumericFeature(5, positiveIndex)

object Race extends EnumLike[Race] {

  case object White extends Race(0)
  case object `Asian-Pac-Islander` extends Race(1)
  case object `Amer-Indian-Eskimo` extends Race(2)
  case object Other extends Race(3)
  case object Black extends Race(4)

  override val values = Seq(
    White,
    `Asian-Pac-Islander`,
    `Amer-Indian-Eskimo`,
    Other,
    Black)
}
