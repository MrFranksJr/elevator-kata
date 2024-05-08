from elevator_kata.elevator import ElevatorCall, ElevatorController
from elevator_kata.parser import parse_calls


def read_calls(controller: ElevatorController) -> None:
    keep_running = True
    while keep_running:
        received_input = input("Register elevator calls: ")
        match received_input.lower():
            case "q":
                keep_running = False
            case "p":
                __print_position(controller.current_elevator_floor)
            case _:
                __process_calls(received_input, controller)


def __process_calls(call_tokens: str, elevator_controller: ElevatorController = None) -> None:
    elevator_calls = parse_calls(call_tokens)
    if len(elevator_calls) == 0:
        __print_api()
        return

    for elevator_call in elevator_calls:
        __print_call(elevator_call)
        elevator_controller.handle_call(elevator_call)


def __print_call(elevator_call: ElevatorCall) -> None:
    print(
        f"A call was received from the floor [{__print_floor(elevator_call.origin_floor)}] with destination [{__print_floor(elevator_call.destination_floor)}]")


def __print_intro() -> None:
    print("*************************")
    print("*    Elevator Kata      *")
    print("*************************")


def __print_api() -> None:
    print("")
    print("Press P to get the current position of the elevator")
    print("Press Q to quit the application")
    print("To give the elevator a Call the format must be ")
    print("  3-B : A call from the third floor to go to the basement ")
    print("  G-5 : A call from the ground floor to go to the fifth floor ")
    print("  4-2 : A call from the fourth floor to go to the second floor")
    print("")


def __print_position(current_elevator_floor: int) -> None:
    print(f"The elevator is currently at {__print_floor(current_elevator_floor)}")


def __print_floor(current_elevator_floor: int) -> str:
    match current_elevator_floor:
        case 0:
            return "G"
        case -1:
            return "B"
        case _:
            return str(current_elevator_floor)
