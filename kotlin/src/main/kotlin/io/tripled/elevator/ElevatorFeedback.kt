package io.tripled.elevator

interface ElevatorFeedback {
    fun startsMovingUp()
    fun startsMovingDown()
    fun doorsOpened(floor: Int)
    fun floorPassed(floor: Int)
    fun doorsClosed()
    fun callReceived(callOrigin: Int, callDestination: Int)
    fun arrivedAtDestination(floor: Int)
    fun arrivedAtCallOrigin(floor: Int)
}