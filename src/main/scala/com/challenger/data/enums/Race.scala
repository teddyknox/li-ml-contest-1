package com.challenger.data.enums

sealed abstract class Race(positiveIndex: Option[Int]) extends NonNumericFeature(5, positiveIndex)

object Race extends EnumLike[Race] {

  case object White extends Race(Some(0))
  case object `Asian-Pac-Islander` extends Race(Some(1))
  case object `Amer-Indian-Eskimo` extends Race(Some(2))
  case object Other extends Race(Some(3))
  case object Black extends Race(Some(4))
  case object ? extends Race(None)

  override val values = Seq(
    White,
    `Asian-Pac-Islander`,
    `Amer-Indian-Eskimo`,
    Other,
    Black,
    ?)
}
