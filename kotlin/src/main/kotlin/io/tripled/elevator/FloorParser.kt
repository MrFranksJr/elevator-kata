package io.tripled.elevator

import kotlin.math.abs

enum class FloorParser {
    FLOOR_PARSER;

    fun toNumber(value: String): Result<Int> {
        return when {
            value.equals("b", ignoreCase = true) -> Result.success(-1)
            value.equals("g", ignoreCase = true) -> Result.success(0)
            isValidInteger(value) -> Result.success(value.toInt())
            else -> Result.failure(IllegalArgumentException("Invalid floor value '$value'"))
        }
    }

    private fun isValidInteger(value: String): Boolean {
        return value.toIntOrNull() != null
    }

    fun toText(currentElevatorFloor: Int): String {
        return when (currentElevatorFloor) {
            -1 -> "Basement"
            0 -> "Ground floor"
            else -> {
                returnCorrectSuffix(currentElevatorFloor)
            }
        }
    }

    private fun returnCorrectSuffix(currentElevatorFloor: Int): String {
        val absoluteFloorNumber = abs(currentElevatorFloor)
        val floorSuffix = when (absoluteFloorNumber % 10) {
            1 -> if (absoluteFloorNumber % 100 == 11) "th" else "st"
            2 -> if (absoluteFloorNumber % 100 == 12) "th" else "nd"
            3 -> if (absoluteFloorNumber % 100 == 13) "th" else "rd"
            else -> "th"
        }
        return "$currentElevatorFloor$floorSuffix floor"
    }
}