from elevator_kata.cli import __print_intro, read_calls
from elevator_kata.elevator import ElevatorController

__print_intro()
controller = ElevatorController()
read_calls(controller)
