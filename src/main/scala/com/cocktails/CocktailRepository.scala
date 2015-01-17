package com.cocktails

import scala.concurrent._

class CocktailRepository(val ec: ExecutionContext) {
  def fetch(s: String): Future[Cocktail] =
    future {
      Cocktail(s, s)
    } (ec)
}

object CocktailRepository {
  def apply(ec: ExecutionContext) = new CocktailRepository(ec)
}
