package com.challenger.model.enums

trait EnumLike[T] {

  val values: Seq[T]

  def valueOf(s: String): T = {
    values find { _.toString equalsIgnoreCase s } getOrElse {
      sys.error("unable to parse enum value " + s)
    }
  }
}
