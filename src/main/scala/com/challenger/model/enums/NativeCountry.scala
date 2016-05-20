package com.challenger.model.enums

sealed abstract class NativeCountry(positiveIndex: Int) extends NonNumericFeature(41, positiveIndex)

object NativeCountry extends EnumLike[NativeCountry] {

  case object `United-States` extends NativeCountry(0)
  case object Cambodia extends NativeCountry(1)
  case object England extends NativeCountry(2)
  case object `Puerto-Rico` extends NativeCountry(3)
  case object Canada extends NativeCountry(4)
  case object Germany extends NativeCountry(5)
  case object `Outlying-US(Guam-USVI-etc)` extends NativeCountry(6)
  case object India extends NativeCountry(7)
  case object Japan extends NativeCountry(8)
  case object Greece extends NativeCountry(9)
  case object South extends NativeCountry(10)
  case object China extends NativeCountry(11)
  case object Cuba extends NativeCountry(12)
  case object Iran extends NativeCountry(13)
  case object Honduras extends NativeCountry(14)
  case object Philippines extends NativeCountry(15)
  case object Italy extends NativeCountry(16)
  case object Poland extends NativeCountry(17)
  case object Jamaica extends NativeCountry(18)
  case object Vietnam extends NativeCountry(19)
  case object Mexico extends NativeCountry(20)
  case object Portugal extends NativeCountry(21)
  case object Ireland extends NativeCountry(22)
  case object France extends NativeCountry(23)
  case object `Dominican-Republic` extends NativeCountry(24)
  case object Laos extends NativeCountry(25)
  case object Ecuador extends NativeCountry(26)
  case object Taiwan extends NativeCountry(27)
  case object Haiti extends NativeCountry(28)
  case object Columbia extends NativeCountry(29)
  case object Hungary extends NativeCountry(30)
  case object Guatemala extends NativeCountry(31)
  case object Nicaragua extends NativeCountry(32)
  case object Scotland extends NativeCountry(33)
  case object Thailand extends NativeCountry(34)
  case object Yugoslavia extends NativeCountry(35)
  case object `El-Salvador` extends NativeCountry(36)
  case object `Trinadad&Tobago` extends NativeCountry(37)
  case object Peru extends NativeCountry(38)
  case object Hong extends NativeCountry(39)
  case object `Holland-Netherlands` extends NativeCountry(40)

  override val values = Seq(
    `United-States`,
    Cambodia,
    England,
    `Puerto-Rico`,
    Canada,
    Germany,
    `Outlying-US(Guam-USVI-etc)`,
    India,
    Japan,
    Greece,
    South,
    China,
    Cuba,
    Iran,
    Honduras,
    Philippines,
    Italy,
    Poland,
    Jamaica,
    Vietnam,
    Mexico,
    Portugal,
    Ireland,
    France,
    `Dominican-Republic`,
    Laos,
    Ecuador,
    Taiwan,
    Haiti,
    Columbia,
    Hungary,
    Guatemala,
    Nicaragua,
    Scotland,
    Thailand,
    Yugoslavia,
    `El-Salvador`,
    `Trinadad&Tobago`,
    Peru,
    Hong,
    `Holland-Netherlands`)
}
