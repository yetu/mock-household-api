package com.yetu.household.things

import org.joda.time.DateTime

import scala.collection.mutable.HashMap
import scala.collection.mutable.MutableList

/**
 * This class represents a thing of a household
 * @param id thing id
 * @param name thing name
 * @param manufacturer thing manufacturer
 * @param displayType display type of a thing which is used by frontend to show relevant icon
 * @param reachable boolean value stating weather thing is reachable by yetu platform or not
 * @param lastCommunication joda time stamp of the thing when it had a last communication to yetu platform
 * @param installationTime joda time stamp of the thing when it was connected to yetu platform for the first time
 * @param subthings list of sub-things which belongs to this thing
 */
class CThing(val id: String, val name: String, val manufacturer: String, val displayType: String, val reachable: Boolean,
             val lastCommunication: DateTime, val installationTime: DateTime, val subthings: List[CSubthing]) {

  lazy val subthingsList: List[CSubthing] = {
    val list: MutableList[CSubthing] = MutableList.empty
    for(subthing <- subthings) {
      list += subthing
    }
    list.toList
  }

  lazy val subthingsMap: Map[String, CSubthing] = {
    val subthingsMap = new HashMap[String, CSubthing]
    for(subthing <- subthings) {
      subthingsMap.put(subthing.id, subthing)
    }
    collection.immutable.HashMap() ++ subthingsMap
  }

  def getSubthing(subthingId: String): Option[CSubthing] = {
    subthingsMap.get(subthingId)
  }
}
