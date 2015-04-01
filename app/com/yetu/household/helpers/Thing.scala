package com.yetu.household.helpers

import com.yetu.household.things.CThing
import com.yetu.household.ConfigImpl
import com.yetu.siren.model.Entity
import com.yetu.siren.model.Entity.{EmbeddedLink, EmbeddedRepresentation}
import com.yetu.siren.model.{Link, Property}
import org.joda.time.format.{ISODateTimeFormat, DateTimeFormatter}

import scala.collection.mutable
import scala.collection.mutable.MutableList
import scala.util.control.Breaks

/**
 * Singleton object containing necessary values and methods for representing thing entity in Siren/JSON format
 */
object Thing extends ConfigImpl {

  val classes = Option(List("thing"))

  val isoDateTimeFormatter: DateTimeFormatter = ISODateTimeFormat.dateTime();
  
  def getProperties(thing: CThing) = {
    Option(List(
      Property("id", Property.StringValue(thing.id)),
      Property("name", Property.StringValue(thing.name)),
      Property("manufacturer", Property.StringValue(thing.manufacturer)),
      Property("displayType", Property.StringValue(thing.displayType)),
      Property("reachable", Property.BooleanValue(thing.reachable)),
      Property("lastCommunication", Property.StringValue(isoDateTimeFormatter.print(thing.lastCommunication))),
      Property("installationTime", Property.StringValue(isoDateTimeFormatter.print(thing.installationTime)))
    ))
  }
  
  def buildEmbeddedThingLink(thingId: String): EmbeddedLink = {
      Entity.EmbeddedLink(
        classes = classes,
        rel = List(thingRelUri),
        href = thingsUri + "/" + thingId
      )
  }
  
  def buildEmbeddedEntityRepresentation(thing: CThing, istr: Boolean): EmbeddedRepresentation = {
    val entities = if(istr) {
      getEmbeddedSubthingRepresentations(thing)
    } else {
      getEmbeddedSubthingLinks(thing)
    }
    EmbeddedRepresentation(
      rel = List(thingsRelUri),
      classes = classes,
      properties = getProperties(thing),
      entities = entities,
      links = getLinks(thing.id)
    )
  }

  def getEmbeddedSubthingLinks(thing: CThing): Option[List[Entity.EmbeddedLink]] = {
    Option(thing.subthingsList.map(subthing => Subthing.buildEmbededSubThingLink(thing.id, subthing.id)))
  }

  def getEmbeddedSubthingRepresentations(thing: CThing): Option[List[Entity.EmbeddedLink]] = {
    Option(thing.subthingsList.map(subthing => Subthing.buildEmbededSubThingLink(thing.id, subthing.id)))
  }


  def getLinks(thingId: String): Option[List[Link]] = {
    val links: MutableList[Link] = MutableList(
      Link(
        rel = List("self"),
        href = thingsUri + "/" + thingId
      )
    )
    var previousThingId: String = null
    val iterator = thingsList.iterator
    val loop = new Breaks
    loop.breakable {
      while(iterator.hasNext) {
        val thing = iterator.next()
        if(thing.id.equals(thingId)) {
          if(previousThingId != null) {
            links += Link(
              rel = List("previous"),
              href = thingsUri + "/" + previousThingId
            )
          }
          if(iterator.hasNext) {
            val thing = iterator.next()
            links += Link(
              rel = List("next"),
              href = thingsUri + "/" + thing.id
            )
          }
          loop.break
        }
        previousThingId = thing.id
      }
    }
    Some(links.toList)
  }
}