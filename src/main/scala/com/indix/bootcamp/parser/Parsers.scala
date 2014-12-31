package com.indix.bootcamp.parser

import com.indix.bootcamp.models.{Product, Price, Result}
import org.jsoup.nodes.Document

class FlipkartParser extends Parser {
  override def parseProduct(document: Document, pageUrl: String): Product = {
    val title = document.select("h1[itemprop=name]").text()
    val description = document.select(".description .sectionTitle").text()
    Product(title, description, pageUrl)
  }

  override def parsePrice(document: Document): Price = {
    val listPrice = document.select("span[class = price]").text().replace("Rs. ", "").replace(",", "").toDouble
    val salePrice = document.select(".selling-price").attr("data-evar48").toDouble

    Price(listPrice, salePrice)
  }
}

class JabongParser extends Parser {
  override def parseProduct(document: Document, pageUrl: String): Product = {
    val title = document.select("span[itemprop = name]").text()
    val description = document.select("p[itemprop = description]").text()
    Product(title,description,pageUrl)
  }

  override def parsePrice(document: Document): Price = {
    val listPrice = document.select("span[itemprop = price]").text().toDouble
    val salePrice = document.select("div[id = pdp-voucher-price]").text().replace("Rs. ", "").toDouble
    Price(listPrice, salePrice)

  }

}
