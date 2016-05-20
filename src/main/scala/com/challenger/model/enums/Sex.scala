package com.challenger.model.enums

sealed abstract class Sex(val value: Int)

object Sex extends EnumLike[Sex] {

  case object Male extends Sex(1)
  case object Female extends Sex(0)

  override val values = Seq(
    Male,
    Female)
}
