import {describe, expect, test} from '@jest/globals';
import {Parser} from "../src/parser";
import {ElevatorCall} from "../src/elevator-call";

describe('Elevator Call Parser', () => {

    function testParseSingleCall(callToken: string, origin: number, destination: number) {
        let elevatorCall = new Parser().parseSingleCall(callToken);
        expect(elevatorCall).toEqual(new ElevatorCall(origin, destination))
    }

    test('parse single call', () => {

        testParseSingleCall("8-9", 8, 9);
        testParseSingleCall("0-4", 0, 4);
        testParseSingleCall("3 - 2", 3, 2);

    });

    test('parse ground floor', () => {

        testParseSingleCall("8-G", 8, 0);
        testParseSingleCall("G-4", 0, 4);
        testParseSingleCall("g-2", 0, 2);

    });

    test('parse basement', () => {

        testParseSingleCall("8-b", 8, -1);
        testParseSingleCall("B-4", -1, 4);
        testParseSingleCall("g-B", 0, -1);

    });

    test('parse multiple calls', () => {

        let elevatorCall = new Parser().parseCalls("0-1 3-2 5-4 2-5");

        let expectedCalls: ElevatorCall[] = [
            new ElevatorCall(0, 1),
            new ElevatorCall(3, 2),
            new ElevatorCall(5, 4),
            new ElevatorCall(2, 5),
        ]

        expect(elevatorCall).toEqual(expectedCalls)

    });
});