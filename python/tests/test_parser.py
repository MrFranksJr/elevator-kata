from elevator_kata.elevator import ElevatorCall
from elevator_kata.parser import parse_calls


def assert_result(callToken: str, origin: int, destination: int) -> None:
    elevator_call = parse_calls(callToken)[0]
    assert elevator_call == ElevatorCall(origin, destination)


def test_parse_single_call() -> None:
    assert_result("8-9", 8, 9)
    assert_result("0-4", 0, 4)
    assert_result("3-2", 3, 2)


def test_parse_ground_floor() -> None:
    assert_result("8-G", 8, 0)
    assert_result("G-4", 0, 4)
    assert_result("g-2", 0, 2)


def test_parse_basement_floor() -> None:
    assert_result("8-b", 8, -1)
    assert_result("B-4", -1, 4)
    assert_result("g-B", 0, -1)


def test_parse_multiple_calls() -> None:
    elevator_call = parse_calls("0-1 3-2 5-4 2-5")

    expected_calls = [
        ElevatorCall(0, 1),
        ElevatorCall(3, 2),
        ElevatorCall(5, 4),
        ElevatorCall(2, 5),
    ]

    assert elevator_call == expected_calls
