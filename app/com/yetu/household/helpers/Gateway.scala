package com.yetu.household
package helpers

import com.yetu.siren.model.{Action, Link, Entity, Property}

/**
 * Singleton object containing necessary values and methods for representing Gateway entity in Siren/JSON format
 */
object Gateway extends ConfigImpl {
  lazy val classes = Option(List("gateway"))
  lazy val properties = Option(List(
    Property("gatewayId", Property.StringValue(gatewayId)),
    Property("online", Property.BooleanValue(true))
  ))
  lazy val embeddedLink = Entity.EmbeddedLink(
    classes = classes,
    rel = List(gatewayRelUri),
    href = gatewayUri
  )
  lazy val links = Option(List(
    Link(
      rel = List("self"),
      href = gatewayUri
    )
  ))
  lazy val actions = Option(List(
    Action(
      name = "create-device-discovery-session",
      href = discoverySessionsUri,
      method = Option(Action.Method.POST)
    ),
    Action(
      name = "create-device-removal-session",
      href = removalSessionsUri,
      method = Option(Action.Method.POST)
    )
  ))
}