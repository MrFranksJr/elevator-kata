import {ElevatorCall} from "./elevator-call";

export class Parser {

    parseSingleCall(callToken: string): ElevatorCall {
        let floors = callToken.split("-");
        let numbers = floors.map(x => this.mapFloor(x));
        let origin: number = numbers[0];
        let destination: number = numbers[1];
        return new ElevatorCall(origin, destination)
    }

    private mapFloor(x: string) {
        switch (x) {
            case "G":
            case "g":
                return 0
            case "B":
            case "b":
                return -1
            default :
                return parseInt(x, 10);
        }
    }

    public parseCalls(calls: string): ElevatorCall[] {
        try {
            const callTokens = calls.split(" ");
            return callTokens.map(x => this.parseSingleCall(x))
        } catch (error) {
            return [];
        }
    }
}