package com.yetu.household
package controllers

import com.yetu.household.helpers.{RemovalSession, DiscoverySession, Gateway}
import com.yetu.siren.json.playjson.PlayJsonSirenFormat._
import com.yetu.siren.model.Entity
import com.yetu.siren.model.Entity.RootEntity
import play.api.libs.json._
import play.api.mvc._

/**
 * Controller for REST API of yetu gateway
 */
object GatewayApi extends Controller {


  /**
   * This is a dummy response which is sent to the client when client tries to create a device discovery session.
   */
  //@TODO: try to mock real behaviour of creating a device discovery session

  private lazy val createSessionDummyResponse =
    "This is a dummy Response\n" +
    "------------------------\n\n" +
    "Response Code = 200 (In case of gateway is online and everything went fine)\n" +
    "Response Code = 504 (In case of gateway is offline)"

  /**
   * Gateway entity in Siren/JSON format
   */
  private lazy val gatewayEntity: Entity.RootEntity = RootEntity(
    classes = Gateway.classes,
    properties = Gateway.properties,
    entities = Option(List(DiscoverySession.embeddedLink)),
    links = Gateway.links,
    actions = Gateway.actions
  )

  /**
   * Device discovery session entity in Siren/JSON format
   */
  private lazy val discoverySessionEntity: Entity.RootEntity = RootEntity(
    classes = DiscoverySession.classes,
    properties = DiscoverySession.properties,
    links = DiscoverySession.linksWithDiscoveredThing
  )

  /**
   * Device removal session entity in Siren/JSON format
   */
  private lazy val removalSessionEntity: Entity.RootEntity = RootEntity(
    classes = RemovalSession.classes,
    properties = RemovalSession.properties,
    links = RemovalSession.links
  )

  /**
   * Handles the REST API request for getting a representation of yetu gateway
   * @return representation of yetu gateway in Siren/JSON format
   */
  def index = Action {
    Ok(Json.toJson(gatewayEntity))
  }

  /**
   * This method is called when REST API for creating a device session (discovery or removal) is used
   * @return [[createSessionDummyResponse]] in plain text
   */
  def createSession = Action {
    Ok(createSessionDummyResponse)
  }

  /**
   * Handles the REST API request for getting a device discovery session representation
   * @param sessionId device session id
   * @return representation of device discovery session in Siren/JSON format
   */
  def getDiscoverySession(sessionId: String) = Action {
    Ok(Json.toJson(discoverySessionEntity))
  }

  /**
   * Handles the REST API request for getting a device removal session representation
   * @param sessionId device session id
   * @return representation of device removal session in Siren/JSON format
   */
  def getRemovalSession(sessionId: String) = Action {
    Ok(Json.toJson(removalSessionEntity))
  }
}