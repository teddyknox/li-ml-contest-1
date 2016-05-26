package com.challenger.data.enums

sealed abstract class NativeCountry(positiveIndex: Option[Int]) extends NonNumericFeature(41, positiveIndex)

object NativeCountry extends EnumLike[NativeCountry] {

  case object `United-States` extends NativeCountry(Some(0))
  case object Cambodia extends NativeCountry(Some(1))
  case object England extends NativeCountry(Some(2))
  case object `Puerto-Rico` extends NativeCountry(Some(3))
  case object Canada extends NativeCountry(Some(4))
  case object Germany extends NativeCountry(Some(5))
  case object `Outlying-US(Guam-USVI-etc)` extends NativeCountry(Some(6))
  case object India extends NativeCountry(Some(7))
  case object Japan extends NativeCountry(Some(8))
  case object Greece extends NativeCountry(Some(9))
  case object South extends NativeCountry(Some(10))
  case object China extends NativeCountry(Some(11))
  case object Cuba extends NativeCountry(Some(12))
  case object Iran extends NativeCountry(Some(13))
  case object Honduras extends NativeCountry(Some(14))
  case object Philippines extends NativeCountry(Some(15))
  case object Italy extends NativeCountry(Some(16))
  case object Poland extends NativeCountry(Some(17))
  case object Jamaica extends NativeCountry(Some(18))
  case object Vietnam extends NativeCountry(Some(19))
  case object Mexico extends NativeCountry(Some(20))
  case object Portugal extends NativeCountry(Some(21))
  case object Ireland extends NativeCountry(Some(22))
  case object France extends NativeCountry(Some(23))
  case object `Dominican-Republic` extends NativeCountry(Some(24))
  case object Laos extends NativeCountry(Some(25))
  case object Ecuador extends NativeCountry(Some(26))
  case object Taiwan extends NativeCountry(Some(27))
  case object Haiti extends NativeCountry(Some(28))
  case object Columbia extends NativeCountry(Some(29))
  case object Hungary extends NativeCountry(Some(30))
  case object Guatemala extends NativeCountry(Some(31))
  case object Nicaragua extends NativeCountry(Some(32))
  case object Scotland extends NativeCountry(Some(33))
  case object Thailand extends NativeCountry(Some(34))
  case object Yugoslavia extends NativeCountry(Some(35))
  case object `El-Salvador` extends NativeCountry(Some(36))
  case object `Trinadad&Tobago` extends NativeCountry(Some(37))
  case object Peru extends NativeCountry(Some(38))
  case object Hong extends NativeCountry(Some(39))
  case object `Holand-Netherlands` extends NativeCountry(Some(40))
  case object ? extends NativeCountry(None)

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
    `Holand-Netherlands`,
    ?)
}
