package com.yetu.household

import com.yetu.household.things.{FibaroWallPlugBuilder, CThing}

/**
 * Package object containing values used by helper classes
 */
package object helpers {
  val householdId = "70cfdabd-4c5a-409c-a270-e5b88d13c623"
  val gatewayId = "859dd0f6-4a37-486f-8998-066d1c3d1caa"
  val discoverySessionId = "4d7f590e-c40d-11e4-8dfc-aa07a5b093db"
  val removalSessionId = "fb257bf0-c643-11e4-8731-1681e6b88ec1"

  val fibaroWallPlugForDiscoverySession = FibaroWallPlugBuilder.build
  
  val thingsList: List[CThing] = List(fibaroWallPlugForDiscoverySession)

  val thingsMap: Map[String, CThing] = thingsList.map(thing => (thing.id -> thing)).toMap
}

