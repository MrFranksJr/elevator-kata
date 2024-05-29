package io.tripled.elevator

class TestElevatorFeedback: ElevatorFeedback {
    private var floorsPassed : MutableList<Int> = mutableListOf()
    private var doorsOpenedAtFloors : MutableList<Int> = mutableListOf()
    override fun startsMovingUp() {
        println("The elevator moves up")
    }

    override fun startsMovingDown() {
        println("The elevator moves down")
    }

    override fun doorsOpened(floor: Int) {
        doorsOpenedAtFloors.add(floor)
        println("Doors opened at floor $floor")
    }

    override fun floorPassed(floor: Int) {
        floorsPassed.add(floor)
        println("Floor $floor passed")
    }

    override fun doorsClosed() {
        println("Doors closed")
    }

    override fun callReceived(callOrigin: Int, callDestination: Int) {
        println( "A call was received from the floor [$callOrigin] with destination [$callDestination]")
    }

    override fun arrivedAtDestination(floor: Int) {
        println( "Lift arrived at destination floor $floor")
    }

    override fun arrivedAtCallOrigin(floor: Int) {
        println("Lift arrived at call origin floor $floor")
    }

    fun doorsOpenedAtFloors(): List<Int> {
        return doorsOpenedAtFloors
    }

    fun floorsPassed(): List<Int> {
        return floorsPassed
    }
}