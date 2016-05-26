package com.challenger.data.enums

sealed abstract class Occupation(positiveIndex: Option[Int]) extends NonNumericFeature(14, positiveIndex)

object Occupation extends EnumLike[Occupation] {

  case object `Tech-support` extends Occupation(Some(0))
  case object `Craft-repair` extends Occupation(Some(1))
  case object `Other-service` extends Occupation(Some(2))
  case object Sales extends Occupation(Some(3))
  case object `Exec-managerial` extends Occupation(Some(4))
  case object `Prof-specialty` extends Occupation(Some(5))
  case object `Handlers-cleaners` extends Occupation(Some(6))
  case object `Machine-op-inspct` extends Occupation(Some(7))
  case object `Adm-clerical` extends Occupation(Some(8))
  case object `Farming-fishing` extends Occupation(Some(9))
  case object `Transport-moving` extends Occupation(Some(10))
  case object `Priv-house-serv` extends Occupation(Some(11))
  case object `Protective-serv` extends Occupation(Some(12))
  case object `Armed-Forces` extends Occupation(Some(13))
  case object ? extends Occupation(None)

  override val values = Seq(
    `Tech-support`,
    `Craft-repair`,
    `Other-service`,
    Sales,
    `Exec-managerial`,
    `Prof-specialty`,
    `Handlers-cleaners`,
    `Machine-op-inspct`,
    `Adm-clerical`,
    `Farming-fishing`,
    `Transport-moving`,
    `Priv-house-serv`,
    `Protective-serv`,
    `Armed-Forces`,
    ?)
}
