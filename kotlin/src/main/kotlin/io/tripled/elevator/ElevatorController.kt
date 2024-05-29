package io.tripled.elevator

class ElevatorController(private val elevatorFeedback: ElevatorFeedback) {
    private var currentElevatorFloor: Int = -1
    private var doorsOpen: Boolean = false

    fun handleCall(call: ElevatorCall) {
        elevatorFeedback.callReceived(call.callOrigin, call.callDestination)
        moveToDestination(call.callOrigin)
        openAndCloseDoors()
        openAndCloseDoors()
        moveToDestination(call.callDestination)
        openAndCloseDoors()
        openAndCloseDoors()
    }

    fun currentFloor(): Int = currentElevatorFloor

    fun doorsOpen(): Boolean = doorsOpen

    private fun moveToDestination(destination: Int) {
        while (currentElevatorFloor != destination) {
            if (currentElevatorFloor < destination) {
                elevatorFeedback.startsMovingUp()
                moveUp()
            } else if (currentElevatorFloor > destination) {
                elevatorFeedback.startsMovingDown()
                moveDown()
            }
            if (currentElevatorFloor != destination) {
                reportFloorPassed(currentElevatorFloor)
            }
        }
        elevatorFeedback.arrivedAtDestination(destination)
    }

    private fun reportFloorPassed(i: Int) {
        elevatorFeedback.floorPassed(currentElevatorFloor)
    }

    private fun moveDown() {
        currentElevatorFloor--
    }

    private fun moveUp() {
        currentElevatorFloor++
    }

    fun openAndCloseDoors() {
        doorsOpen = !doorsOpen
        if (doorsOpen) {
            elevatorFeedback.doorsOpened(currentElevatorFloor)
        } else {
            elevatorFeedback.doorsClosed()
        }
    }
}