package io.tripled.elevator

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class FloorParserTest : FunSpec({
    test("returns -1 when receiving b as floor call input") {
        val floorParser = FloorParser.FLOOR_PARSER

        floorParser.toNumber("b") shouldBe -1
    }

    test("returns 0 when receiving g as floor call input") {
        val floorParser = FloorParser.FLOOR_PARSER

        floorParser.toNumber("g") shouldBe 0
    }

    test("returns integer value when input is not g or b") {
        val floorParser = FloorParser.FLOOR_PARSER

        floorParser.toNumber("2") shouldBe 2
        floorParser.toNumber("-2") shouldBe -2
        floorParser.toNumber("1") shouldBe 1
    }

    test("returns basement string value from floor -1 input") {
        val floorParser = FloorParser.FLOOR_PARSER

        floorParser.toText(-1) shouldBe "Basement"
    }

    test("returns ground floor string value from floor 0 input") {
        val floorParser = FloorParser.FLOOR_PARSER

        floorParser.toText(0) shouldBe "Ground floor"
    }

    test("returns correct floornumber string value from if integer input is not -1 or 0") {
        val floorParser = FloorParser.FLOOR_PARSER

        floorParser.toText(1) shouldBe "1st floor"
        floorParser.toText(-2) shouldBe "-2nd floor"
        floorParser.toText(5) shouldBe "5th floor"
        floorParser.toText(11) shouldBe "11th floor"
        floorParser.toText(3) shouldBe "3rd floor"
        floorParser.toText(111) shouldBe "111th floor"
    }
})