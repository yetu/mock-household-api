package com.yetu.household.things

import play.api.libs.json._
import com.yetu.siren.model.Link
import com.yetu.siren.model.Property

/**
 * This class represents a sub-thing of a thing
 * @param id sub-thing id
 * @param thingId thing id to which this sub-thing belongs to
 * @param name name of a sub-thing
 * @param subthingType type of a sub-thing according to yetu's thing abstraction concept. It can also be used for showing respective icon in front-end
 * @param capabilities list of sub-thing's capabilities
 * @param tags list of sub-thing's tags
 * @param additionalProperties additional properties of a sub-thing which comes from its capabilities
 * @param actions list of actions which supported by sub-thing
 * @param descriptionLinks list of additional links for Siren/JSON representation of sub-thing
 */
class CSubthing(val id: String, val thingId: String, val name: String, val subthingType: String, val capabilities: Array[String], val tags: Array[String],
                val additionalProperties: JsObject, val actions: Option[Seq[Action]], val descriptionLinks: Option[Seq[Link]]) {

  def getAllPropertiesForSiren = {
    val defaultProps = JsObject(Seq(
      "id" -> JsString(id),
      "name" -> JsString(name),
      "type" -> JsString(subthingType),
      "capabilities" -> Json.toJson(capabilities)
    ))
    defaultProps ++ additionalProperties
  }
}
