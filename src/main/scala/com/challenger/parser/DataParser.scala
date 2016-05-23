package com.challenger.parser

import com.challenger.loader.DataLoader
import com.challenger.data._
import com.challenger.data.enums._

trait DataParser {

  def parseTrainingSet(line: String): TrainingSetLine = {
    val Array(
      age,
      workClass,
      sampleWeight,
      education,
      educationNum,
      maritalStatus,
      occupation,
      relationship,
      race,
      sex,
      capitalGain,
      capitalLoss,
      hoursPerWeek,
      nativeCountry,
      label) = line split "\\s+"
    TrainingSetLine(
      age.toInt,
      WorkClass.valueOf(workClass),
      sampleWeight.toInt,
      Education.valueOf(education),
      educationNum.toInt,
      MaritalStatus.valueOf(maritalStatus),
      Occupation.valueOf(occupation),
      Relationship.valueOf(relationship),
      Race.valueOf(race),
      Sex.valueOf(sex),
      capitalGain.toInt,
      capitalLoss.toInt,
      hoursPerWeek.toInt,
      NativeCountry.valueOf(nativeCountry),
      Label.valueOf(label))
  }

  def parseTestSet(line: String): TestSetLine = {
    val Array(
      age,
      workClass,
      sampleWeight,
      education,
      educationNum,
      maritalStatus,
      occupation,
      relationship,
      race,
      sex,
      capitalGain,
      capitalLoss,
      hoursPerWeek,
      nativeCountry) = line split "\\s+"
    TestSetLine(
      age.toInt,
      WorkClass.valueOf(workClass),
      sampleWeight.toInt,
      Education.valueOf(education),
      educationNum.toInt,
      MaritalStatus.valueOf(maritalStatus),
      Occupation.valueOf(occupation),
      Relationship.valueOf(relationship),
      Race.valueOf(race),
      Sex.valueOf(sex),
      capitalGain.toInt,
      capitalLoss.toInt,
      hoursPerWeek.toInt,
      NativeCountry.valueOf(nativeCountry))
  }
}