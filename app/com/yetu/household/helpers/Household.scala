package com.yetu.household.helpers

import com.yetu.household.ConfigImpl
import com.yetu.siren.model.{Link, Property}

/**
 * Singleton object containing necessary values and methods for representing household entity in Siren/JSON format
 */
object Household extends ConfigImpl{
  val classes = Option(List("household"))
  val properties = Option(List(
    Property("householdId", Property.StringValue(householdId))
  ))
  val uri =  householdUri
  val links = Option(List(
    Link(
      rel = List("self"),
      href = uri
    )
  ))
}