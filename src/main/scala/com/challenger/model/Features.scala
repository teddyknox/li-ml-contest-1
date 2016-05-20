package com.challenger.model

case class Features(
  age: Double,
  workClass: Seq[Double],
  sampleWeight: Double,
  education: Seq[Double],
  educationNum: Double,
  maritalStatus: Seq[Double],
  occupation: Seq[Double],
  relationship: Seq[Double],
  race: Seq[Double],
  sex: Double,
  capitalGain: Double,
  capitalLoss: Double,
  hoursPerWeek: Double,
  nativeCountry: Seq[Double])
