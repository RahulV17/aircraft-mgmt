# Aircraft Management System

A console-based Core Java application for managing aircraft, pilots, and flight scheduling. Built as a hands-on OOP exercise demonstrating inheritance, polymorphism, abstraction, interfaces, custom exceptions, and array-based storage — no database or Collection Framework.

## Features

- Register and search Passenger, Cargo, and Private aircraft
- Add and search pilots with availability tracking
- Schedule flights with full validation (duplicate IDs, availability, source != destination)
- Cancel flights and automatically release aircraft and pilots
- Custom exception hierarchy for all business rule violations
- Menu-driven CLI that recovers gracefully from errors

## Tech Stack

- Java 17
- Scanner I/O
- In-memory arrays (no database, no Collection Framework)
- No build tool required (compile with `javac`)

## How to Run

```bash
# Compile
javac -d out src/contract/Maintenance.java src/exception/*.java src/model/*.java src/service/*.java src/main/AircraftManagementApplication.java

# Run
java -cp out main.AircraftManagementApplication
```

## Project Structure

```
src/
├── contract/
│   └── Maintenance.java          # Interface for maintenance operations
├── exception/
│   ├── AircraftAlreadyExistsException.java
│   ├── AircraftNotAvailableException.java
│   ├── AircraftNotFoundException.java
│   ├── FlightNotFoundException.java
│   ├── InvalidAircraftException.java
│   ├── InvalidFlightException.java
│   ├── PilotAlreadyAssignedException.java
│   └── PilotNotFoundException.java
├── model/
│   ├── Aircraft.java             # Abstract base class
│   ├── PassengerAircraft.java
│   ├── CargoAircraft.java
│   ├── PrivateAircraft.java
│   ├── Pilot.java
│   └── Flight.java
├── service/
│   ├── AircraftManager.java
│   ├── PilotManager.java
│   └── FlightManager.java
└── main/
    └── AircraftManagementApplication.java
```

## OOP Concepts Demonstrated

| Concept | Where |
|---------|-------|
| Encapsulation | Private fields with controlled access in all model classes |
| Inheritance | PassengerAircraft, CargoAircraft, PrivateAircraft extend Aircraft |
| Abstraction | Abstract Aircraft class + Maintenance interface |
| Polymorphism | Aircraft[] holds different subclasses; displayAircraftDetails() overridden |
| Method Overriding | displayAircraftDetails() and getAircraftType() in each subclass |
| Method Overloading | searchAircraft(String) vs searchAircraft(String, String) |
| Constructors | All domain classes initialize mandatory state; constructor chaining via super() |
| Has-A Relationship | Flight contains Aircraft and Pilot references |
| Custom Exceptions | 8 domain-specific exceptions for business rule violations |
| Arrays of Objects | Aircraft[50], Pilot[50], Flight[50] in manager classes |

## Business Rules

- Aircraft ID must be unique
- Pilot ID must be unique
- Flight ID must be unique
- Aircraft capacity must be greater than zero
- Pilot experience must not be negative
- Source and destination cannot be the same
- An aircraft cannot be assigned to multiple active flights
- A pilot cannot be assigned to multiple active flights
- A cancelled flight releases its aircraft and pilot

## Assignment Requirements

This project fulfills the requirements of the Aircraft Management System assignment:
- Console-based application with menu-driven interface
- No database, no Collection Framework, no frameworks
- Array-based in-memory storage
- Custom exceptions for all business validation failures
- Input validation with graceful error recovery
- Separation of concerns: model / service / exception / main

