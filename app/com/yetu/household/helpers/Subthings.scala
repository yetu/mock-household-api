package com.yetu.household.helpers

import com.yetu.household.ConfigImpl
import com.yetu.household.helpers.Things._
import com.yetu.siren.model.{Link, Entity}

/**
 * Singleton object containing necessary values and methods for representing list of sub-things in Siren/JSON format
 */
object Subthings extends ConfigImpl {

  val classes = Option(List("collection", "subthings"))

  def buildLinks(thingId: String) = {
    val strBuilder = new StringBuilder
    strBuilder.append(thingsUri).append("/").append(thingId).append("/").append(subthingText)
    Option(List(
      Link(
        rel = List("self"),
        href = strBuilder.toString
      )
    ))
  }
}
