package com.challenger.model

import com.challenger.model.enums._

trait Line {
  val age: Int
  val workClass: WorkClass
  val sampleWeight: Int
  val education: Education
  val educationNum: Int
  val maritalStatus: MaritalStatus
  val occupation: Occupation
  val relationship: Relationship
  val race: Race
  val sex: Sex
  val capitalGain: Int
  val capitalLoss: Int
  val hoursPerWeek: Int
  val nativeCountry: NativeCountry

  def features: Features = {
    Features(
      age = age.toDouble,
      workClass = workClass.featureVector,
      sampleWeight = sampleWeight.toDouble,
      education = education.featureVector,
      educationNum = educationNum.toDouble,
      maritalStatus = maritalStatus.featureVector,
      occupation = occupation.featureVector,
      relationship = relationship.featureVector,
      race = race.featureVector,
      sex = sex.value,
      capitalGain = capitalGain.toDouble,
      capitalLoss = capitalLoss.toDouble,
      hoursPerWeek = hoursPerWeek.toDouble,
      nativeCountry = nativeCountry.featureVector)
  }
}
