package com.yetu.household.helpers

import com.yetu.household.ConfigImpl
import com.yetu.household.helpers.Thing._
import com.yetu.household.things.Action.Field
import com.yetu.household.things.{CThing, CSubthing}
import com.yetu.siren.model.Entity.EmbeddedRepresentation
import com.yetu.siren.model.{Link, Entity, Property}
import play.api.libs.json._

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

object Subthing extends ConfigImpl {

  val classesList = List("subthing")

  val classes = Option(classesList)

  implicit val fieldWrites = new Writes[Field] {
    def writes(field: Field) = Json.obj(
      "name" -> field.name,
      "type" -> field.`type`
    )
  }

  def buildEmbededSubThingLink(thingId: String, subthingId: String) = {
    val strBuilder = new StringBuilder
    strBuilder.append(thingsUri).append("/").append(thingId).append("/").append(subthingText).append("/").append(subthingId)
    Entity.EmbeddedLink(
      classes = classes,
      rel = List(subthingRelUri),
      href = strBuilder.toString
    )
  }

  def buildEmbeddedSubthingRepresentation(thing: CThing, subthing: CSubthing): EmbeddedRepresentation = {
    EmbeddedRepresentation(
      rel = List(subthingRelUri),
      classes = classes,
      entities = getEmbeddedSubthingLinks(thing),
      links = getLinks(thing.id)
    )
  }

  def actions(subthing: CSubthing): Option[Seq[JsObject]] = {
    val strBuilder = new StringBuilder
    strBuilder.append(thingsUri).append("/").append(subthing.thingId).append("/")
      .append(subthingText).append("/").append(subthing.id).append("/").append(actionsText)
    subthing.actions.map(actionList =>
      actionList.map(action => {
        var jsonAction = JsObject(Seq(
          "name" -> JsString(action.name),
          "href" -> JsString(strBuilder.toString() + action.path),
          "method" -> JsString(action.method.name)
        ))
        action.title.map(title => jsonAction += ("title", JsString(title)))
        action.`type`.map(encoding => jsonAction += ("type", JsString(encoding.name)))
        action.fields.map((fields: Seq[Field]) => jsonAction += ("fields", Json.toJson(fields)))
        jsonAction
      })
    )
  }

  def links(subthing: CSubthing, thing: CThing) = {
    val strBuilder = new StringBuilder
    val thingUriStrBilder = strBuilder.append(thingsUri).append("/").append(thing.id).clone()
    strBuilder.append("/").append(subthingText).append("/").append(subthing.id)
    val links: ListBuffer[Link] = ListBuffer(
      Link(
        rel = List("self"),
        href = strBuilder.toString
      )
    )
    var previousSubThingId: String = null
    val iterator = thing.subthingsList.iterator
    val loop = new Breaks
    loop.breakable {
      while(iterator.hasNext) {
        val subthing = iterator.next()
        if(subthing.id.equals(subthing.id)) {
          if(previousSubThingId != null) {
            val pSubThingUriStrBilder = thingUriStrBilder.clone().append("/").append(subthingText).append("/").append(previousSubThingId)
            links += Link(
              rel = List("previous"),
              href = pSubThingUriStrBilder.toString
            )
          }
          if(iterator.hasNext) {
            val nSubthing = iterator.next()
            val nSubThingUriStrBilder = thingUriStrBilder.clone().append("/").append(subthingText).append("/").append(nSubthing.id)
            links += Link(
              rel = List("next"),
              href = nSubThingUriStrBilder.toString
            )
          }
          loop.break
        }
        previousSubThingId = subthing.id
      }
    }
    val descriptionLinks = subthing.descriptionLinks.map(links =>
      links.map(link => link.copy(
        href = relBaseUri + link.href
      ))
    )
    descriptionLinks.map(descLinks => links ++ descLinks)
  }
}
