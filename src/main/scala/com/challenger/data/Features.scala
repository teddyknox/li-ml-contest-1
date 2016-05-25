package com.challenger.data

import breeze.linalg.DenseVector

/**
  * 0	age
  **
  * workClass
  * 1	Private
  * 2	Self-emp-not-inc
  * 3	Self-emp-inc
  * 4	Federal-gov
  * 5	Local-gov
     *6	State-gov
     *7	Without-pay
     *8	Never-worked.
     *9	sampleWeight
 **
 *education
    *10	Bachelors
    *11	Some-college
    *12	11th
    *13	HS-grad
    *14	Prof-school
    *15	Assoc-acdm
    *16	Assoc-voc
    *17	9th
    *18	7th-8th
    *19	12th
    *20	Masters
    *21	1st-4th
    *22	10th
    *23	Doctorate
    *24	5th-6th
    *25	Preschool
 **
 *educationNum
    *26	education-num: continuous.
 **
 *maritalStatus
    *27	Married-civ-spouse
    *28	Divorced
    *29	Never-married
    *30	Separated
    *31	Widowed
    *32	Married-spouse-absent
    *33	Married-AF-spouse.
 **
 *occupation
    *34	Tech-support
    *35	Craft-repair
    *36	Other-service
    *37	Sales
    *38	Exec-managerial
    *39	Prof-specialty
    *40	Handlers-cleaners
    *41	Machine-op-inspct
    *42	Adm-clerical
    *43	Farming-fishing
    *44	Transport-moving
    *45	Priv-house-serv
    *46	Protective-serv
    *47	Armed-Forces
 **
 *relationship
    *48	Wife
    *49	Own-child
    *50	Husband
    *51	Not-in-family
    *52	Other-relative
    *53	Unmarried
 **
 *race
    *54	White
    *55	Asian-Pac-Islander
    *56	Amer-Indian-Eskimo
    *57	Other
    *58	Black.
 **
 *sex
    *59	male: 1, female: 0
 **
 *capital gain
    *60	capital-gain: continuous.
 **
 *capital loss
    *61	capital-loss: continuous.
 **
 *hours per week
    *62	hours-per-week: continuous.
 **
 *native-country
    *63	United-States
    *64	Cambodia
    *65	England
    *66	Puerto-Rico
    *67	Canada
    *68	Germany
    *69	Outlying-US(Guam-USVI-etc)
    *70	India
    *71	Japan
    *72	Greece
    *73	South
    *74	China
    *75	Cuba
    *76	Iran
    *77	Honduras
    *78	Philippines
    *79	Italy
    *80	Poland
    *81	Jamaica
    *82	Vietnam
    *83	Mexico
    *84	Portugal
    *85	Ireland
    *86	France
    *87	Dominican-Republic
    *88	Laos
    *89	Ecuador
    *90	Taiwan
    *91	Haiti
    *92	Columbia
    *93	Hungary
    *94	Guatemala
    *95	Nicaragua
    *96	Scotland
    *97	Thailand
    *98	Yugoslavia
    *99	El-Salvador
   *100	Trinadad&Tobago
   *101	Peru
   *102	Hong
   *103	Holland-Netherlands.
  */
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
  nativeCountry: Seq[Double]) {

  def vector: DenseVector[Double] = {
    val arr = Array(age) ++
      workClass ++
      Array(sampleWeight) ++
      education ++
      Array(educationNum) ++
      maritalStatus ++
      occupation ++
      relationship ++
      race ++
      Array(sex) ++
      Array(capitalGain) ++
      Array(capitalLoss) ++
      Array(hoursPerWeek) ++
      nativeCountry
    DenseVector(arr)
  }
}
