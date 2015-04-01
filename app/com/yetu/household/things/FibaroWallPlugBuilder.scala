package com.yetu.household.things

import java.util.UUID

import org.joda.time.DateTime
import play.api.libs.json._
import com.yetu.siren.model.Link

/**
 * Builder for fibaro wall plug
 */
object FibaroWallPlugBuilder {

  /**
   * Counter for fibaro wall plug created by this builder
   */
  var counter = 0;

  /**
   * Name prefix of fibaro wall plug
   */
  val name = "Fibaro wall plug"

  /**
   * Factory method for fibaro wall plug
   * @return [[CThing]] fibaro wall plug
   */
  def build = {
    counter += 1;
    val thingId: String = UUID.randomUUID().toString;
    new CThing(
      thingId, name+"-"+counter, "Fibaro",
      "socket", true, new DateTime(2015, 3, 12, 4, 12), new DateTime(2015, 1, 9, 8, 21),
      List(
        new CSubthing(
          UUID.randomUUID().toString, thingId, "Consumption lamp RGB LED ring", "lamp", Array("switchable", "colorable", "dimmable"), Array(),
          Json.obj(
            "on" -> JsBoolean(false),
            "color" -> Json.obj(
              "hue" -> 123,
              "saturation" -> 34,
              "brightness" -> 98
            ),
            "level" -> JsNumber(23)
          ), Option(List(
            Action("get-on", "/on"),
            Action(
              "set-on", "/on", Action.Method.PUT, Option(Action.Encoding.`application/json`),
              fields = Option(List(Action.Field("on", "boolean")))
            ),
            Action("get-color", "/color"),
            Action(
              "set-color", "/color", Action.Method.PUT, Option(Action.Encoding.`application/json`),
              fields = Option(List(Action.Field("color", "hsbColor")))
            ),
            Action("get-level", "/level"),
            Action(
              "set-level", "/level", Action.Method.PUT, Option(Action.Encoding.`application/json`),
              fields = Option(List(Action.Field("level", "percentage")))
            )
          )), Option(List(
            Link( rel = List("type"), href = "/subthingtypes/lamp" ),
            Link( rel = List("capability"), href = "/capabilities/switchable" ),
            Link( rel = List("capability"), href = "/capabilities/colorable" ),
            Link( rel = List("capability"), href = "/capabilities/dimmable" ),
            Link( rel = List("valueType"), href = "/valueTypes/boolean" ),
            Link( rel = List("valueType"), href = "/valueTypes/hsbColor" ),
            Link( rel = List("valueType"), href = "/valueTypes/percentage" )
          ))
        ),
        new CSubthing(
          UUID.randomUUID().toString, thingId, "Socket of wall plug", "socket", Array("switchable"), Array(),
          Json.obj(
            "on" -> JsBoolean(true)
          ), Option(List(
            Action("get-on", "/on"),
            Action(
              "set-on", "/on", Action.Method.PUT, Option(Action.Encoding.`application/json`),
              fields = Option(List(Action.Field("on", "boolean")))
            )
          )), Option(List(
            Link( rel = List("type"), href = "/subthingtypes/socket" ),
            Link( rel = List("capability"), href = "/capabilities/switchable" ),
            Link( rel = List("valueType"), href = "/valueTypes/boolean" )
          ))
        ),
        new CSubthing(
          UUID.randomUUID().toString, thingId, "Power measurement sensor", "sensor", Array("measurement"), Array(),
          Json.obj(
            "value" -> JsNumber(230),
            "unit" -> JsString("watt")
          ), Option(List(
            Action("get-value", "/value"),
            Action("get-unit", "/unit")
          )), Option(List(
            Link( rel = List("type"), href = "/subthingtypes/sensor" ),
            Link( rel = List("capability"), href = "/capabilities/measurement" )
          ))
        )
      )
    )
  }
}