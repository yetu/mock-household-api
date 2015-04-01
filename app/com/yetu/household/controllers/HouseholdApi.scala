package com.yetu.household
package controllers

import com.yetu.household.helpers.{Things, Gateway, Household}
import com.yetu.siren.model.Entity
import play.api.mvc._
import com.yetu.siren.json.playjson.PlayJsonSirenFormat._
import play.api.libs.json._
import com.yetu.siren.model.Entity.RootEntity


/**
 * Controller for REST API of household
 */

object HouseholdApi extends Controller {

  /**
   * Siren/JSON household entity representation
   */
  private lazy val householdEntity: Entity.RootEntity = RootEntity(
    classes = Household.classes,
    properties = Household.properties,
    entities = Option(List(Gateway.embeddedLink, Things.embeddedLink)),
    links = Household.links
  )
  
  def index = Action {
    Ok(Json.toJson(householdEntity))
  }
}