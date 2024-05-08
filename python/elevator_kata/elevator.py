from dataclasses import dataclass


@dataclass
class ElevatorCall:
    origin_floor: int
    destination_floor: int

    def __post_init__(self):
        if self.origin_floor is None or self.destination_floor is None:
            raise ValueError("Origin and destination floors must be provided in ElevatorCall")
        if not self.origin_floor.is_integer() or not self.destination_floor.is_integer():
            raise ValueError("Not a valid number in ElevatorCall")


class Controller:
    def handle_call(self, call: ElevatorCall) -> None:
        pass

    @property
    def current_elevator_floor(self) -> int:
        return -1
