package io.tripled.elevator

enum class ApplicationCommand {
    QUIT, POSITION, MOVE, UNKNOWN;

    companion object {
        fun parse(input: String): ApplicationCommand {
            if (isQuitCommand(input)) return QUIT
            if (isPositionCommand(input)) return POSITION
            if (isMoveCommand(input)) return MOVE
            return UNKNOWN
        }

        private fun isMoveCommand(input: String): Boolean {
            return input.contains("-")
        }

        private fun isQuitCommand(input: String): Boolean {
            return "q".equals(input, ignoreCase = true)
        }

        private fun isPositionCommand(input: String): Boolean {
            return input.equals("p", ignoreCase = true)
        }
    }
}

