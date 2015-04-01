package com.yetu.household.helpers

import com.yetu.household.ConfigImpl
import com.yetu.siren.model.{Entity, Link, Property}

/**
 * Singleton object containing necessary values and methods for representing device removal session entity in Siren/JSON format
 */
object RemovalSession extends ConfigImpl {
  val classes = Option(List("session", "removal"))
  val properties = Option(List(
    Property("sessionId", Property.StringValue(removalSessionId)),
    Property("state", Property.StringValue(Session.State.CREATED.toString))
  ))
  val uri = removalSessionsUri + "/" + removalSessionId
  val embeddedLink = Entity.EmbeddedLink(
    rel = List(removalSessionRelUri),
    href = uri
  )
  val links = Option(List(
    Link(
      rel = List("self"),
      href = uri
    )
  ))
}
