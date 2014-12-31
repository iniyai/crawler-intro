package com.indix.bootcamp.parser

import com.indix.bootcamp.models.{Price, Result, Product}
import org.jsoup.nodes.Document
import org.jsoup.Jsoup
import com.indix.bootcamp.utils.Utils._

trait Parser {
  def parseProduct(document: Document, pageUrl: String): Product
  def parsePrice(document: Document): Price

  def parse(pageHtml: String, pageUrl: String): Result = {
    val document = Jsoup.parse(pageHtml)
    val product = Managed(parseProduct(document, pageUrl))
    val price = Managed(parsePrice(document))
    println("product inside parse is " + product)
    println("price inside parse is " + price)
    Result(product, price)
  }
}
