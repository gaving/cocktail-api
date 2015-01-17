package com.cocktails

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import scala.concurrent.future
import org.mockito.Mockito._
import org.mockito.Matchers._
import org.specs2.specification.BeforeExample

class CocktailServiceSpec extends Specification with Specs2RouteTest with BeforeExample with CocktailService {
  def actorRefFactory = system

  override val userRepository: UserRepository = mock(classOf[UserRepository])
  def before = {
    when(userRepository.fetch(anyString())).thenReturn(future{new User("greeting", 1)})
  }


  "CocktailService" should {
    "return a greeting for GET requests to the root path" in {
      Get("/users/greeting") ~> cocktailRoute ~> check {
        responseAs[String] must contain("greeting")
      }
    }
  }
}
