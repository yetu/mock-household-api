package com.yetu.household.helpers

import com.yetu.household.ConfigImpl
import com.yetu.household.things.CThing
import com.yetu.siren.model.Entity.{EmbeddedLink, EmbeddedRepresentation}
import com.yetu.siren.model.{Link, Entity}

/**
 * Singleton object containing necessary values and methods for representing list of things in Siren/JSON format
 */
object Things extends ConfigImpl {
  val classes = Option(List("things", "collection"))
  
  val entityEmbeddedLinks: Option[List[EmbeddedLink]] = Option(thingsList.map(thing => Thing.buildEmbeddedThingLink(thing.id)))

  def entityEmbeddedRepresentations(istr: Boolean): Option[List[EmbeddedRepresentation]] = Option(thingsList.map(thing => Thing.buildEmbeddedEntityRepresentation(thing, istr)))

  val embeddedLink = Entity.EmbeddedLink(
    classes = classes,
    rel = List(thingsRelUri),
    href = thingsUri
  )
  val uri =  thingsUri
  val links = Option(List(
    Link(
      rel = List("self"),
      href = uri
    )
  ))
  def getThing(thingId: String): Option[CThing] = {
    thingsMap.get(thingId)
  }
}