package com.yetu.household

package object things {

  case class Action(
     name: String,
     path: String,
     method: Action.Method = Action.Method.GET,
     `type`: Option[Action.Encoding] = None,
     `class`: Option[Seq[String]] = None,
     title: Option[String] = None,
     fields: Option[Seq[Action.Field]] = None)

  object Action {

    case class Field(name: String, `type`: String)

    /**
     * A sum type that represents a method that can be specified for an action.
     * The available methods are a subset of the HTTP verbs.
     */
    sealed trait Method extends Enum.Val[Method]
    /** Companion object of the [[Method]] trait. **/
    object Method extends Enum[Method] {
      /** The HTTP GET method */
      case object GET extends Method
      /** The HTTP PUT method */
      case object PUT extends Method
      /** The HTTP POST method */
      case object POST extends Method
      /** The HTTP DELETE method */
      case object DELETE extends Method
      /** The HTTP PATCH method */
      case object PATCH extends Method

      override val values = List(GET, PUT, POST, DELETE, PATCH)
    }

    /**
     * Sum type that encompasses all the supported encodings for actions in Siren.
     */
    sealed trait Encoding extends Enum.Val[Encoding]
    /** Companion object for the [[Encoding]] trait. */
    object Encoding extends Enum[Encoding] {
      /** The application/x-www-form-urlencoded encoding for an action's payload. */
      case object `application/x-www-form-urlencoded` extends Encoding
      /** The application/json encoding for an action's payload. */
      case object `application/json` extends Encoding

      override val values = List(`application/json`, `application/x-www-form-urlencoded`)
    }

  }

  /**
   * Base type for enumerations.
   * @tparam A the type of the enumerated values
   */
  trait Enum[A <: Enum.Val[A]] {
    /**
     * All values of this enumeration in order.
     */
    def values: List[A]
    /**
     * Returns the enumeration value with the specified name as a [[Some]], or [[None]] if no
     * enumeration value with that name exists.
     * @param name the name for which a corresponding enumeration value is to be returned
     */
    def forName(name: String): Option[A] = values find (_.name == name)

    def unapply(name: String): Option[A] = forName(name)
  }
  /**
   * Companion object of the [[Enum]] trait.
   */
  object Enum {
    /**
     * Base trait for enumerated values.
     * @tparam A the type of the enumerated values
     */
    trait Val[A] {
      /**
       * the name of the enumeration value
       */
      def name: String = toString
    }
  }
}
