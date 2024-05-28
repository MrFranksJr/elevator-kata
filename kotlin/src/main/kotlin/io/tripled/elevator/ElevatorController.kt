package io.tripled.elevator

class ElevatorController {
    private var currentElevatorFloor: Int = -1
    private var doorsOpen: Boolean = false



    fun handleCall(call: ElevatorCall) {
        moveToCallOrigin(call)
        toggleDoorsOpen()
        moveToCallDestination(call)
        toggleDoorsOpen()
    }

    fun currentFloor(): Int = currentElevatorFloor

    private fun moveToCallDestination(call: ElevatorCall) {
        currentElevatorFloor = call.callDestination
    }

    private fun moveToCallOrigin(call: ElevatorCall) {
        currentElevatorFloor = call.callOrigin
    }

    fun toggleDoorsOpen() {
        doorsOpen = !doorsOpen
    }
}
