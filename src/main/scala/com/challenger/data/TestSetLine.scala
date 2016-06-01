package com.challenger.data

import com.challenger.data.enums._

case class TestSetLine(
  age: Int,
  workClass: WorkClass,
  sampleWeight: Int,
  education: Education,
  educationNum: Int,
  maritalStatus: MaritalStatus,
  occupation: Occupation,
  relationship: Relationship,
  race: Race,
  sex: Sex,
  capitalGain: Int,
  capitalLoss: Int,
  hoursPerWeek: Int,
  nativeCountry: NativeCountry,
  labelOpt: Option[Label] = None) extends Line
