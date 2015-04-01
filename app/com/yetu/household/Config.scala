package com.yetu.household

trait Config {
  def relBaseUri:String
  def householdUri:String
  def gatewayUri:String
  def gatewayRelUri:String

  def discoverySessionsUri:String
  def discoverySessionRelUri:String
  def discoveredThingRelUri:String

  def removalSessionsUri:String
  def removalSessionRelUri:String

  def thingsUri:String
  def thingsRelUri:String
  def thingRelUri:String

  def subthingText:String
  def subthingRelUri:String
  def actionsText:String
}
