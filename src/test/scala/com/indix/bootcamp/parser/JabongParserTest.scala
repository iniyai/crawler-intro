package com.indix.bootcamp.parser

import org.scalatest.{Matchers, FunSuite}
import com.indix.bootcamp.utils.TestUtils

class JabongParserTest extends FunSuite with Matchers with TestUtils {

  test("should parse product page") {
    val document = readDocument("/jabong/jabong_sheet.html")
    val parser = new JabongParser
    val parsedProduct = parser.parseProduct(document, "http://www.jabong.com/house-this-Sqaure-Double-Comforters-Red-142033.html")
    parsedProduct.name should be("Sqaure Double Comforters Red")
    parsedProduct.description should include("bedroom interior with this")
  }

  test("should parse prices from product page") {
    val document = readDocument("/jabong/jabong_sheet.html")
    val parser = new JabongParser
    val parsedPrice = parser.parsePrice(document)
    parsedPrice.listPrice should be(1999.0)
    parsedPrice.salePrice should be(1399.0)
  }
}
