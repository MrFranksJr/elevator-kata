import promptSync from "prompt-sync";
import {Parser} from "./parser";
import {ElevatorController} from "./elevator-controller";
import {ElevatorCall} from "./elevator-call";

const prompt = promptSync({sigint: true});

export class CLIElevatorApplication {
    private readonly parser: Parser = new Parser();
    private readonly presenter: CLIPresenter = new CLIPresenter();
    private readonly elevatorController = new ElevatorController();

    public run() {
        this.presenter.printIntro();
        this.readCalls();
    }


    private processCalls(callTokens: string) {
        let elevatorCalls = this.parser.parseCalls(callTokens);
        if (elevatorCalls.length == 0)
            this.presenter.printAPI()
        else {
            elevatorCalls.forEach(this.presenter.printCall)
            this.elevatorController.handleCalls(elevatorCalls);
        }
    }


    private readCalls() {
        let keepRunning: boolean = true;
        while (keepRunning) {
            const input = prompt('Register elevator calls: ');
            switch (input) {
                case "q":
                case "Q":
                    keepRunning = false;
                    break;
                case "p":
                case "P":
                    this.presenter.printPosition(this.elevatorController.getCurrentElevatorFloor());
                    break;
                default :
                    this.processCalls(input);
            }
        }
    }
}

class CLIPresenter {
    printCall(elevatorCall: ElevatorCall) {
        console.log("A call was received from the floor [" + this.printFloor(elevatorCall.origin) + "] with destination [" + this.printFloor(elevatorCall.destination) + "]");
    }

    printIntro() {
        console.log("*************************")
        console.log("*    Elevator Kata      *")
        console.log("*************************")
    }

    printAPI() {
        console.log("")
        console.log("Press P to get the current position of the elevator");
        console.log("Press Q to quit the application");
        console.log("To give the elevator a Call the format must be ");
        console.log("  3-B : A call from the third floor to go to the basement ");
        console.log("  G-5 : A call from the ground floor to go to the fifth floor ");
        console.log("  4-2 : A call from the fourth floor to go to the second floor");
        console.log("")
    }

    printPosition(currentElevatorFloor: number) {


        console.log("The elevator is currently at " + this.printFloor(currentElevatorFloor));
    }

    printFloor(currentElevatorFloor: number) {
        switch (currentElevatorFloor) {
            case 0 :
                return "G";
            case -1 :
                return "B";
            default :
                return currentElevatorFloor.toString()
        }
    }
}
