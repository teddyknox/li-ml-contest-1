package com.challenger.data.enums

sealed abstract class Occupation(positiveIndex: Int) extends NonNumericFeature(14, positiveIndex)

object Occupation extends EnumLike[Occupation] {

  case object `Tech-support` extends Occupation(0)
  case object `Craft-repair` extends Occupation(1)
  case object `Other-service` extends Occupation(2)
  case object Sales extends Occupation(3)
  case object `Exec-managerial` extends Occupation(4)
  case object `Prof-specialty` extends Occupation(5)
  case object `Handlers-cleaners` extends Occupation(6)
  case object `Machine-op-inspct` extends Occupation(7)
  case object `Adm-clerical` extends Occupation(8)
  case object `Farming-fishing` extends Occupation(9)
  case object `Transport-moving` extends Occupation(10)
  case object `Priv-house-serv` extends Occupation(11)
  case object `Protective-serv` extends Occupation(12)
  case object `Armed-Forces` extends Occupation(13)

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
    `Armed-Forces`)
}
