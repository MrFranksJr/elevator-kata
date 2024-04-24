export class ElevatorCall {
    readonly origin: number;
    readonly destination: number;

    constructor(origin: number, destination: number) {
        if (isNaN(origin) || isNaN(destination)) {
            throw new Error("Not a valid number in ElevatorCall")
        }
        if (undefined == origin || undefined == destination) {
            throw new Error("Undefined in ElevatorCall")
        }
        this.origin = origin
        this.destination = destination
    }
}