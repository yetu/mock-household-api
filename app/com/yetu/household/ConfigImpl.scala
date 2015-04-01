package com.yetu.household

import play.Play

/**
 * Default implementation of Configuration for this application
 */
abstract class ConfigImpl extends Config {
  def config = Play.application().configuration()
  def relBaseUri = config.getString("relations.baseUri")
  def householdUri = config.getString("uris.household")
  def gatewayUri = config.getString("uris.gateway")
  def gatewayRelUri = config.getString("relationsuris.gateway")
 
  def discoverySessionsUri = config.getString("uris.discoverysessions")
  def discoverySessionRelUri = config.getString("relationsuris.discoverysession")
  def discoveredThingRelUri = config.getString("relationsuris.discoveredThing")
 
  def removalSessionsUri = config.getString("uris.removalsessions")
  def removalSessionRelUri = config.getString("relationsuris.removalsession")

  def thingsUri = config.getString("uris.things")
  def thingsRelUri = config.getString("relationsuris.things")
  def thingRelUri = config.getString("relationsuris.thing")
  
  def subthingText = config.getString("subthingText")
  def subthingRelUri = config.getString("relationsuris.subthing")
  def actionsText = config.getString("actionsText")
}
