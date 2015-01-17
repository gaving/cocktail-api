package com.cocktails

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

case class Cocktail(name: String, origin: String)
object Cocktail {
  implicit def cocktailJsonFormat: RootJsonFormat[Cocktail] = jsonFormat2(Cocktail.apply)
}
