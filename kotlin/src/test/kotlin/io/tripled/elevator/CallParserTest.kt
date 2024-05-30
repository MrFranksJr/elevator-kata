package io.tripled.elevator

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.shouldBe
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.isAccessible

class CallParserTest : FunSpec({

    test("gracefully handles gibberish input") {
        val createCallMethod = CallParser.Companion::class.declaredFunctions.find { it.name == "createCall" }
        createCallMethod?.isAccessible = true

        val result = createCallMethod?.call(CallParser, arrayOf("foo", "bar")) as Result<ElevatorCall>

        result.shouldBeFailure()
        result.exceptionOrNull()?.message shouldBe "Invalid call origin floor: foo and Invalid call destination floor: bar"
    }

})
