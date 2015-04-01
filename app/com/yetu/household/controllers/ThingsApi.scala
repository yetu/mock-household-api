package com.yetu.household.controllers

import com.yetu.household.helpers.{Subthings, Thing, Things, Subthing}
import com.yetu.siren.model.Entity
import com.yetu.siren.model.Entity.RootEntity
import play.api.mvc.{Action, Controller}
import com.yetu.siren.json.playjson.PlayJsonSirenFormat._
import play.api.libs.json._


/**
 * Controller for handling REST API of things belonging to household
 */
object ThingsApi extends Controller {


  /**
   * Handles a request to handle
   * @param itr boolean representing whether to include things representations instead of embedded links; true to include and false to not; default is false
   * @param istr boolean representing whether to include sub-things representations in things representations instead of embedded links; true to include and false to not; default is false
   * @return List of all things in a household
   */
  def index(itr: Boolean, istr: Boolean) = Action {
    if(itr) {
      Ok(Json.toJson(
        RootEntity(
          classes = Things.classes,
          entities = Things.entityEmbeddedRepresentations(istr),
          links = Things.links
        )
      ))
    } else {
      Ok(Json.toJson(
        RootEntity(
          classes = Things.classes,
          entities = Things.entityEmbeddedLinks,
          links = Things.links
        )
      ))
    }
  }

  /**
   * Handles request for getting a thing representation
   * @param thingId thing id
   * @return thing representation in Siren/JSON format
   */
  def getThing(thingId: String) = Action {
    Things.getThing(thingId) match {
      case Some(thing) =>
        val thingEntity: Entity.RootEntity = RootEntity(
          classes = Thing.classes,
          properties = Thing.getProperties(thing),
          entities = Thing.getEmbeddedSubthingLinks(thing),
          links = Thing.getLinks(thing.id)
        )
        Ok(Json.toJson(thingEntity))
      case None => NotFound("")
    }
  }

  /**
   * Handles request for getting a list of all sub-things for a thing
   * @param thingId thing id
   * @return thing representation in Siren/JSON format
   */
  def getSubthings(thingId: String) = Action {
    Things.getThing(thingId) match {
      case Some(thing) =>
        val thingEntity: Entity.RootEntity = RootEntity(
          classes = Subthings.classes,
          entities = Thing.getEmbeddedSubthingLinks(thing),
          links = Subthings.buildLinks(thing.id)
        )
        Ok(Json.toJson(thingEntity))
      case None => NotFound("")
    }
  }

  /**
   * Handles request for getting a sub-thing representation of a thing
   * @param thingId thing id
   * @param subthingId sub-thing id
   * @return thing representation in Siren/JSON format
   */
  def getSubthing(thingId: String, subthingId: String) = Action {
    Things.getThing(thingId) match {
      case Some(thing) =>
        thing.getSubthing(subthingId) match {
          case Some(subthing) =>
            var jsonResponse = JsObject(Seq(
              "properties" -> subthing.getAllPropertiesForSiren,
              "class" -> Json.toJson(Subthing.classesList)
            ))
            Subthing.actions(subthing).map(actions => jsonResponse += ("actions", Json.toJson(actions)))
            Subthing.links(subthing, thing).map(links => jsonResponse += ("links", Json.toJson(links)))
            Ok(jsonResponse)
          case None => NotFound("")
        }
      case None => NotFound("")
    }
  }
}
