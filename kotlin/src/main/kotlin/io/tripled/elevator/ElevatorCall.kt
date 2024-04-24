package io.tripled.elevator

@JvmRecord
data class ElevatorCall(val callOrigin: Int, val callDestination: Int)
