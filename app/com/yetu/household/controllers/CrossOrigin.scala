package com.yetu.household.controllers

import play.api.mvc.{Controller, Action}

object CrossOrigin extends Controller {

  def preflight(all: String) = Action {
    NoContent.withHeaders("Access-Control-Allow-Origin" -> "*",
      "Allow" -> "*",
      "Access-Control-Allow-Methods" -> "POST, GET, PUT, DELETE, OPTIONS",
      "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept, Referer, User-Agent")
  }

}