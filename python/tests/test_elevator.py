def test_elevator_call():
    from elevator_kata.elevator import ElevatorCall
    call = ElevatorCall(3, -1)
    assert call.origin_floor == 3
    assert call.destination_floor == -1
    assert str(call) == "A call was received from the floor [3] with destination [-1]"
