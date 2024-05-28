package io.tripled.elevator

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ElevatorControllerTest : FunSpec({

    test("controller should return its currentfloor") {
        val elevatorController = ElevatorController()

        elevatorController.currentFloor() shouldBe -1
    }

    test("when doors have opened at call origin, they should close and move to call destination and doors should open again") {
        val elevatorController = ElevatorController()

        elevatorController.handleCall(ElevatorCall(3,0))
        elevatorController.toggleDoorsOpen()

        elevatorController.currentFloor() shouldBe 0
        elevatorController.doorsOpen shouldBe true
    }

})