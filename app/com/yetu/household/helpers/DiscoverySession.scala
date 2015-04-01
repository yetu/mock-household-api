package com.yetu.household.helpers

import com.yetu.household.ConfigImpl
import com.yetu.siren.model.{Link, Entity, Property}

/**
 * Singleton object containing necessary values and methods for representing Device discovery session entity in Siren/JSON format
 */
object DiscoverySession extends ConfigImpl {
  lazy val classes = Option(List("session", "discovery"))
  lazy val properties = Option(List(
    Property("sessionId", Property.StringValue(discoverySessionId)),
    Property("state", Property.StringValue(Session.State.FINISHED.toString))
  ))
  lazy val uri = discoverySessionsUri + "/" + discoverySessionId
  lazy val embeddedLink = Entity.EmbeddedLink(
    rel = List(discoverySessionRelUri),
    href = uri
  )
  lazy val links = Option(List(
    Link(
      rel = List("self"),
      href = uri
    )
  ))
  lazy val linksWithDiscoveredThing = Option(List(
    Link(
      rel = List("self"),
      href = uri
    ),
    Link(
      rel = List(discoveredThingRelUri),
      href = thingsUri + "/" + fibaroWallPlugForDiscoverySession.id
    )
  ))
}
