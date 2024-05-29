package io.tripled.elevator

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ElevatorControllerTest : FunSpec({
    lateinit var elevatorController: ElevatorController
    lateinit var testElevatorFeedback: TestElevatorFeedback

    beforeTest {
        testElevatorFeedback = TestElevatorFeedback()
        elevatorController = ElevatorController(testElevatorFeedback)
    }

    test("controller should return its currentfloor") {
        elevatorController.currentFloor() shouldBe -1
    }

    test("when doors have opened at call origin, they should close and move to call destination and doors should open again") {
        elevatorController.handleCall(ElevatorCall(3, 0))

        elevatorController.currentFloor() shouldBe 0
        elevatorController.doorsOpen() shouldBe false
    }

    test("elevator reports all floors passed") {
        elevatorController.handleCall(ElevatorCall(3, 0))

        testElevatorFeedback.floorsPassed() shouldBe listOf(0, 1, 2, 2, 1)
    }

    test("elevator reports where doors were opened") {
        elevatorController.handleCall(ElevatorCall(3, 0))

        testElevatorFeedback.doorsOpenedAtFloors() shouldBe listOf(3,0)
    }

})
