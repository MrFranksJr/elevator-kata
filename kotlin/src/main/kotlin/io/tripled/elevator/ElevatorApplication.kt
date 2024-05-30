package io.tripled.elevator

import java.util.*


class ElevatorApplication {
    private val elevatorController: ElevatorController

    init {
        val elevatorFeedback = SimpleElevatorFeedback()
        elevatorController = ElevatorController(elevatorFeedback)
    }

    private fun handleCommand(applicationCommand: ApplicationCommand, input: String) {
        when (applicationCommand) {
            ApplicationCommand.QUIT -> {
                println("*********END*********")
            }
            ApplicationCommand.POSITION -> {
                println(elevatorPositionMessage())
            }
            ApplicationCommand.MOVE -> {
                handleMoveCommand(input)
            }
            ApplicationCommand.UNKNOWN -> {
                println(this.apiMessage())
            }
        }
    }

    private fun handleMoveCommand(input: String) {
        val result = CallParser.CALL_PARSER.parse(input)
        if (result.isSuccess) {
            handleCall(result.getOrThrow())
        } else {
            println("Error: ${result.exceptionOrNull()?.message}")
            println(apiMessage())
        }
    }


    private fun elevatorPositionMessage(): String {
        val currentElevatorFloor = elevatorController.currentFloor()
        return "The elevator is currently at ${FloorParser.FLOOR_PARSER.toText(currentElevatorFloor)}"
    }

    private fun handleCall(elevatorCall: ElevatorCall) {
        elevatorController.handleCall(elevatorCall)
    }

    private fun apiMessage(): String = """
            |API:
            |Press P to get the current position of the elevator
            |Press Q to quit the application
            |To give the elevator a Call the format must be
            |  3 - B : A call from the third floor to go to the basement 
            |  G - 5 : A call from the ground floor to go to the fifth floor 
            |  4 - 2 : A call from the fourth floor to go to the second floor
        """.trimMargin()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("""
                **************************
                **    Elevator Kata     **
                **************************
            """.trimIndent())
            readInput()
        }

        private fun readInput() {
            val application = ElevatorApplication()
            Scanner(System.`in`).use { scanner ->
                do {
                    val input = scanner.nextLine()
                    val applicationCommand = ApplicationCommand.parse(input)
                    application.handleCommand(applicationCommand, input)
                    if (applicationCommand == ApplicationCommand.QUIT) {
                        break
                    }
                } while (true)
            }
        }
    }
}
