package io.tripled.elevator

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe

class FloorParserTest : FunSpec({
    lateinit var floorParser: FloorParser

    beforeEach {
        floorParser = FloorParser.FLOOR_PARSER
    }

    test("returns -1 when receiving b as floor call input") {
        floorParser.toNumber("b").shouldBeSuccess { it shouldBe -1 }
    }

    test("returns 0 when receiving g as floor call input") {
        floorParser.toNumber("g").shouldBeSuccess { it shouldBe  0 }
    }

    test("returns integer value when input is not g or b") {
        floorParser.toNumber("2").shouldBeSuccess { it shouldBe 2 }
        floorParser.toNumber("-2").shouldBeSuccess { it shouldBe -2 }
        floorParser.toNumber("1").shouldBeSuccess { it shouldBe 1 }
    }

    test("returns basement string value from floor -1 input") {
        floorParser.toText(-1) shouldBe "Basement"
    }

    test("returns ground floor string value from floor 0 input") {
        floorParser.toText(0) shouldBe "Ground floor"
    }

    test("gracefully handles gibberish input") {
        floorParser.toNumber("gfdshgfd").shouldBeFailure { it shouldBe IllegalArgumentException("Invalid floor value 'gfdshgfd'")}
    }

    test("returns correct floornumber string value from if integer input is not -1 or 0") {
        floorParser.toText(1) shouldBe "1st floor"
        floorParser.toText(-2) shouldBe "-2nd floor"
        floorParser.toText(5) shouldBe "5th floor"
        floorParser.toText(11) shouldBe "11th floor"
        floorParser.toText(3) shouldBe "3rd floor"
        floorParser.toText(111) shouldBe "111th floor"
    }
})