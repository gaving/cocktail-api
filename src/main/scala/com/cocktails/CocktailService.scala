package com.cocktails

import akka.actor.Actor
import spray.routing._
import scala.concurrent.ExecutionContext

//the actor which will accept request and distribute to other actors/objects
class CocktailServiceActor(cocktailRepo: CocktailRepository) extends Actor with CocktailService {

  def actorRefFactory = context

  def receive = runRoute(cocktailRoute)

  override val cocktailRepository: CocktailRepository = cocktailRepo
}

// this trait defines our service behavior independently from the service actor
trait CocktailService extends HttpService {
  implicit val ec: ExecutionContext = actorRefFactory.dispatcher
  val cocktailRepository: CocktailRepository

  val cocktailRoute =
    path("cocktails" / Segment) {
      cocktailId => {
        get {
          complete {
            //must import SprayJsonSupport to get a json mashaller
            import spray.httpx.SprayJsonSupport._
            cocktailRepository fetch cocktailId
          }
        }
      }
    }
}
