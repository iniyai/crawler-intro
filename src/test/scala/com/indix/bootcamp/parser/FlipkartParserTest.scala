package com.indix.bootcamp.parser

import org.scalatest.{Matchers, FunSuite}
import com.indix.bootcamp.utils.TestUtils

class FlipkartParserTest extends FunSuite with Matchers with TestUtils {

  test("should parse product page") {
    val document = readDocument("/flipkart/flipkart_windows.html")
    val parser = new FlipkartParser
    val parsedProduct = parser.parseProduct(document, "http://www.flipkart.com/microsoft-windows-8-8-1-sl-oem-64-bit/p/itmdztjtw8gymtwd?pid=OPSDNW94WXHFDGAF&srno=b_1&ref=4847fd47-daaa-4b4e-ab56-c3a3ac84c7d5")
    parsedProduct.name should be("Microsoft Windows 8 / 8.1 SL OEM 64 bit")
    parsedProduct.description should include("Microsoft Windows 8 / 8.1 SL OEM 64 bit Price: Rs. 5,555")
  }

  test("should parse prices from product page") {
    val document = readDocument("/flipkart/flipkart_windows.html")
    val parser = new FlipkartParser
    val parsedPrice = parser.parsePrice(document)
    parsedPrice.listPrice should be(8590.0)
    parsedPrice.salePrice should be(5555.0)
  }
}
