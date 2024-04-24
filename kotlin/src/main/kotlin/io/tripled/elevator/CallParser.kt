package io.tripled.elevator

import java.util.*

enum class CallParser {
    CALL_PARSER;


    fun parse(input: String): Optional<ElevatorCall> {
        val splitTokens = input.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return if (hasTwoPositions(splitTokens)) {
            Optional.of(createCall(splitTokens))
        } else Optional.empty()
    }

    private fun hasTwoPositions(splitTokens: Array<String>): Boolean {
        return splitTokens.size == 2
    }

    companion object {
        private fun createCall(splitTokens: Array<String>): ElevatorCall {
            val origin = splitTokens[0]
            val destination = splitTokens[1]
            return ElevatorCall(FloorParser.FLOOR_PARSER.toNumber(origin), FloorParser.FLOOR_PARSER.toNumber(destination))
        }
    }
}
