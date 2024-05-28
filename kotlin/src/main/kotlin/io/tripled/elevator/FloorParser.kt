package io.tripled.elevator

import kotlin.math.abs

enum class FloorParser {
    FLOOR_PARSER;

    fun toNumber(value: String): Int {
        if (value.equals("b", ignoreCase = true)) return -1
        if (value.equals("g", ignoreCase = true)) return 0
        return value.toInt()
    }

    fun toText(currentElevatorFloor: Int): String {
        return when (currentElevatorFloor) {
            -1 -> "Basement"
            0 -> "Ground floor"
            else -> {
                val absoluteFloorNumber = abs(currentElevatorFloor)
                val floorSuffix = when (absoluteFloorNumber % 10) {
                    1 -> if (absoluteFloorNumber % 100 == 11) "th" else "st"
                    2 -> if (absoluteFloorNumber % 100 == 12) "th" else "nd"
                    3 -> if (absoluteFloorNumber % 100 == 13) "th" else "rd"
                    else -> "th"
                }
                "$currentElevatorFloor$floorSuffix floor"
            }
        }
    }
}