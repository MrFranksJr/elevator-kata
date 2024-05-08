from elevator_kata.elevator import ElevatorCall, Controller
from elevator_kata.parser import Parser


def read_calls(controller: Controller) -> str:
    keep_running = True
    while keep_running:
        input = input("Register elevator calls: ")
        match input.lower():
            case "q":
                keep_running = False
            case "p":
                print_position(controller.current_elevator_floor)
            case _:
                _process_calls(input)


def _process_calls(self, call_tokens: str) -> None:
    elevator_calls = Parser().parse_calls(call_tokens)
    if len(elevator_calls) == 0:
        self.presenter.print_api()
    else:
        for elevator_call in elevator_calls:
            self.presenter.print_call(elevator_call)
        self.elevator_controller.handle_calls(elevator_calls)


def print_call(elevator_call: ElevatorCall) -> None:
    print(
        f"A call was received from the floor [{print_floor(elevator_call.origin_floor)}] with destination [{print_floor(elevator_call.destination_floor)}]")


def print_intro() -> None:
    print("*************************")
    print("*    Elevator Kata      *")
    print("*************************")


def print_api() -> None:
    print("")
    print("Press P to get the current position of the elevator")
    print("Press Q to quit the application")
    print("To give the elevator a Call the format must be ")
    print("  3-B : A call from the third floor to go to the basement ")
    print("  G-5 : A call from the ground floor to go to the fifth floor ")
    print("  4-2 : A call from the fourth floor to go to the second floor")
    print("")


def print_position(current_elevator_floor: int) -> None:
    print(f"The elevator is currently at {print_floor(current_elevator_floor)}")


def print_floor(current_elevator_floor: int) -> str:
    match current_elevator_floor:
        case 0:
            return "G"
        case -1:
            return "B"
        case _:
            return str(current_elevator_floor)
