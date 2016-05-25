package com.challenger.data.enums

sealed abstract class Label(val value: Int)

object Label extends EnumLike[Label] {

  case object `<=50K` extends Label(0)
  case object `>50K` extends Label(1)

  override val values = Seq(
    `<=50K`,
    `>50K`)

  def get(v: Double): Label = {
    if (v > 0.5) `>50K`
    else `<=50K`
  }
}
