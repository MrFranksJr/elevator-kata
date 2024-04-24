# Elevator Kata

### Description

We will implement an elevator controller. Where an elevator responds to calls containing a source floor and a destination. Whereupon the elevator picks up and
delivers the passengers to requested floors. All actions take time, we want to minimize the amount of time that passengers have to wait on an elevator and the
time spend inside an elevator. Where we prefer to let them wait for an elevator than placing them longer inside the elevator. Optimization of our elevator logic
will be measured by how fast we can execute our calls.

```gherkin
Given the elevator is positioned on the third floor
And the current time is T0
When there is a call from the first/ground floor to go to the basement
Then the doors of the elevator will open at the ground floor to onboard the passenger and in the basements to deliver the passenger to their destination
And the current time is T14
```

Inspired by [Lift Kata](https://kata-log.rocks/lift-kata)
and [Agile Technical Practices Distilled](https://www.amazon.com/Agile-Technical-Practices-Distilled-principles-ebook)

### Overall design requirements

Below are all the high level requirements of what we eventually want to have. We will of course implement this piece by piece.

#### Basic Elevator logic

+ An elevator should be able to pick up and deliver a passenger.
+ An elevator does not move with open doors.
+ An elevator has a maximum capacity. When the weight is too high the elevator will not depart. We will try to prevent this by limiting the amount of calls an
  elevator can execute simultaneously to 5.

#### Multiple Elevators

+ There could be multiple elevators. Make sure we can add elevators.
+ When there are multiple elevators, there are multiple ways to determine which elevator gets to execute a call. This is the **Elevator selection strategy**.
  Make sure we can easily modify this.
+ As a first implementation of the Elevator selection strategy use the *Distribute equally strategy* where you can choose the elevator that has the least calls  assigned and is closest to the origin of the call

#### Handling calls by an elevator

+ When an elevator has multiple calls there are multiple ways to determine which call to execute next. This is an elevators **Next Call strategy**. Make sure we
  can easily modify this. As a first implementation just queue the registered call and take them in the order they came in.
+ When an elevator is executing a call, we can choose the handle other calls along the way. This is an elevators **Pickup strategy**. Make sure we can easily
  modify this. As a first implementation, don't pick-up passengers.
+ If we handle one call at a time, the elevator maximum capacity is not an issue. But we will limit the amount of calls an elevator can execute simultaneously
  to 5.

#### The concept of time

The following time durations apply:

+ The elevator moving from one floor to another takes **one timeslot** (real life would be say 2 sec ).
+ Opening the doors takes **one timeslot**.
+ Closing the doors takes **one timeslot**.
+ The onboarding of passenger takes **three timeslots**.

```gherkin
Scenario: Time example scenario
Given the elevator is positioned on the ground floor at T0
When there is a call from floor 2 to go to floor 3 at T0
Then the elevator arrives at floor 2 at T2 ( = T0 + 2 * 1 timeslot for moving two floors up)
Then the doors should open at floor 2 at T3 ( = T2 + 1 timeslot for opening doors)
And the doors should close at floor 2 at T7 ( = T3 + 3 timeslot for onboarding + 1 timeslot for closing doors )
And the elevator arrives at floor 3 at T8 ( = T7 + 1 timeslot for moving one floor up)
And the doors should open at floor 3 at T9 ( = T8 + 1 timeslot for opening doors)
And the doors should close at floor 3 at T13 ( = T9 + 3 timeslot for onboarding + 1 timeslot for closing doors )
```

### Getting started

While the main logic of the kata needs to be implemented, there is a basic CLI in place to use the "application" from the command line. So if you checked out
the code, you should have a running application. The goal of the kata is to complete the logic inside the ElevatorController. The rest of the code present is a
simple CLI to illustrate how the code should work and to have a convenient CLI to use the logic, run other simulations. Feel free to modify the internals of the
CLI in any way you see fit, change the api etc...

Before you start the exercise make sure that the launch command works. The launch command is just a small wrapper around maven that compiles the code, runs the
tests and starts the application.

This is what you should see on windows

```batch
>elevator.bat
**************************
**    Elevator Kata     **
**************************
```

This is what you should see on mac/linux

```batch
>./elevator
**************************
**    Elevator Kata     **
**************************
```

### Basic Elevator functionality

Implement a lift controller for a building that has 5 total floors including basement and ground.
The elevator can be called at any floor only when it is not in use via one call button.

Simplifications:
+ There is only one elevator
+ Don't worry yet about time
+ Execute one call at a time

```gherkin
Scenario: Part One Scenario
Given the elevator is positioned on the ground floor
When there is a call from floor3 to go to basement
And there is a call from ground to go to basement
And there is a call from floor2 to go to basement
And there is a call from floor1 to go to floor 3
Then the doors should open at floor3, basement, ground, basement, floor2, basement, floor1 and floor3 in this order
```

When the elevator is to a floor, no new call can be registered.

Running the above scenario through the CLI this might look something like this:

```text
**************************
**    Elevator Kata     **
**************************
> p
The elevator is currently at ground floor

> 3-b

Elevator at 1 floor
Elevator at 2 floor
Elevator at 3 floor
<DING> - door open at 3 floor
Elevator at 2 floor
Elevator at 1 floor
Elevator at ground floor
Elevator at basement
<DING> - door open at basement

> 0-1

Elevator at ground floor
<DING> - door open at ground floor
Elevator at 1 floor
<DING> - door open at 1 floor

> 2-b

Elevator at 1 floor
Elevator at 2 floor
<DING> - door open at 2 floor
Elevator at 1 floor
Elevator at ground floor
Elevator at basement
<DING> - door open at basement

> 1-3

Elevator at ground floor
Elevator at 1 floor
<DING> - door open at 1 floor
Elevator at 2 floor
Elevator at 3 floor
<DING> - door open at 3 floor


>p
The elevator is currently at 3 floor
```
### The stage is yours

Once you have this up and running, it is up to you how you proceed.

+ Allow multiple elevators
+ Add a time concept
+ Optimize handling of elevator calls by improving the strategies.
  + Better **Next Call strategy**, how does the elevator select the next call. 
+ Optimize **Pickup strategy**
  + How to make the most efficient use of the elevator route. Which calls can be handled along the way.

Remember we want to minimize the amount of time that passengers have to wait on an elevator and the
time they spend inside an elevator
