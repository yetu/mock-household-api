package com.yetu.household

import play.api.{Logger, Application, GlobalSettings}
import play.api.mvc.EssentialAction

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    Logger.info("Application has started---  !!!")
  }
}
