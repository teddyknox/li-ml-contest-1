package com.challenger.model

import com.challenger.model.enums._

case class TrainingSetLine(
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
  label: Label) extends Line
