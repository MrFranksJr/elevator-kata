package io.tripled.elevator

enum class FloorParser {
    FLOOR_PARSER;

    fun toNumber(value: String): Int {
        if (value.equals("b", ignoreCase = true)) return -1
        if (value.equals("g", ignoreCase = true)) return 0
        return value.toInt()
    }

    fun toText(currentElevatorFloor: Int): String {
        return when (currentElevatorFloor) {
            -1 -> "basement"
            0 -> "ground floor"
            else -> "$currentElevatorFloor floor"
        }
    }
}
