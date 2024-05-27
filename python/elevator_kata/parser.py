from elevator_kata.elevator import ElevatorCall


def __map_floor(floor: str) -> int:
    match floor:
        case "G" | "g":
            return 0
        case "B" | "b":
            return -1
        case _:
            return int(floor)


def __parse_call(unparsed_call: str) -> ElevatorCall:
    origin, destination = unparsed_call.split("-")
    origin_floor = __map_floor(origin)
    destination_floor = __map_floor(destination)
    return ElevatorCall(origin_floor, destination_floor)


def parse_calls(calls: str) -> list[ElevatorCall]:
    try:
        call_tokens = calls.split(" ")
        return [__parse_call(unparsed_call) for unparsed_call in call_tokens]
    except ValueError:
        return []
