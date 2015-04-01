package com.yetu.household.helpers

object Session {

  /**
   * State enum of device session
   */
  object State extends Enumeration {
    type State = Value
    val REQUESTED = Value("REQUESTED")
    val CREATED = Value("CREATED")
    val FINISHED = Value("FINISHED")
    val EXPIRED = Value("EXPIRED")
  }
}
