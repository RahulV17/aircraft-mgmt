package model;

import exception.InvalidFlightException;

public class Flight {
    private String flightId;
    private String source;
    private String destination;
    private Aircraft aircraft;
    private Pilot pilot;
    private String flightStatus;

    public Flight(String flightId, String source, String destination, Aircraft aircraft, Pilot pilot) throws InvalidFlightException {
        if(flightId == null || flightId.trim().isEmpty()){
            throw new InvalidFlightException("Flight ID cannot be empty");
        }
        if(source.trim().equalsIgnoreCase(destination.trim())){
            throw new InvalidFlightException("Source and destination cannot be same");
        }
        if(aircraft == null || pilot == null) {
            throw new InvalidFlightException("Flight must have Aircraft and Pilot");
        }
        this.flightId = flightId;
        this.source = source;
        this.destination = destination;
        this.aircraft = aircraft;
        this.pilot = pilot;
        this.flightStatus = "Scheduled";
    }

    public String getFlightId() {
        return flightId;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void cancel(){
        this.flightStatus = "Cancelled";
    }

    public void displayDetails(){
        System.out.println("Flight ID: " + flightId + ", " + source + ", ->: " + destination + ", Status: " + flightStatus + ", Aircraft: " + aircraft.getAircraftId() + ", Pilot: " + pilot.getPilotId());
    }
}
