package io.tripled.elevator

enum class CallParser {
    CALL_PARSER;

    fun parse(input: String): Result<ElevatorCall> {
        val splitTokens = input.split(" - ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return if (hasTwoPositions(splitTokens)) createCall(splitTokens)
        else Result.failure(IllegalArgumentException("Input must contain two floor numbers separated by a dash"))
    }

    private fun hasTwoPositions(splitTokens: Array<String>): Boolean = splitTokens.size == 2

    companion object {
        private fun createCall(splitTokens: Array<String>): Result<ElevatorCall> {
            val callOriginResult = floorToNumberResult(splitTokens[0])
            val callDestinationResult = floorToNumberResult(splitTokens[1])

            return if (callOriginResult.isSuccess && callDestinationResult.isSuccess) {
                Result.success(ElevatorCall(callOriginResult.getOrThrow(), callDestinationResult.getOrThrow()))
            } else {
                val errorMessage = mutableListOf<String>()
                if (callOriginResult.isFailure) errorMessage.add("Invalid call origin floor: ${splitTokens[0]}")
                if (callDestinationResult.isFailure) errorMessage.add("Invalid call destination floor: ${splitTokens[1]}")
                Result.failure(IllegalArgumentException(errorMessage.joinToString(" and ")))
            }
        }

        private fun floorToNumberResult(splitToken: String) = FloorParser.FLOOR_PARSER.toNumber(splitToken)
    }
}
