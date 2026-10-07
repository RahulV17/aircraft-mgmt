package service;

import exception.*;
import model.Aircraft;
import model.Flight;
import model.Pilot;

public class FlightManager {
    private Flight[] flights = new Flight[50];
    private int flightCount = 0;
    private AircraftManager aircraftManager;
    private PilotManager pilotManager;

    public FlightManager(AircraftManager aircraftManager, PilotManager pilotManager) {
        this.aircraftManager = aircraftManager;
        this.pilotManager = pilotManager;
    }

    public void scheduleFlight(String flightId, String aircraftId, String pilotId, String origin, String destination) throws InvalidFlightException, AircraftNotFoundException, PilotNotFoundException, PilotAlreadyAssignedException, AircraftNotAvailableException {
        for (int i = 0; i < flightCount; i++) {
            if(flights[i].getFlightId().equalsIgnoreCase(flightId) && !"Cancelled".equals(flights[i].getFlightStatus())){
                throw new InvalidFlightException("Flight " + flightId + " is already scheduled and not cancelled");
            }
        }
        Aircraft aircraft = aircraftManager.searchAircraft(aircraftId);
        if(!"Available".equals(aircraft.getStatus())) {
            throw new AircraftNotAvailableException("Aircraft " + aircraftId + " is currently assigned");
        }
        Pilot pilot = pilotManager.searchPilot(pilotId);
        if(!pilot.isAvailable()){
            throw new PilotAlreadyAssignedException("Pilot" +pilotId + "is already assigned to another flight");
        }
        Flight flight = new Flight(flightId, origin, destination, aircraft, pilot);
        if(flightCount >= flights.length) {
            throw new IllegalStateException("Flight storage is full");
        }
        flights[flightCount++] = flight;
        aircraft.assignFlight();
        pilot.assignToFlight();
    }

    public Flight searchFlight(String flightId) throws FlightNotFoundException{
        for(int i = 0; i < flightCount; i++){
            if(flights[i].getFlightId().equalsIgnoreCase(flightId)){
                return flights[i];
            }
        }
        throw new FlightNotFoundException("Flight " + flightId + " does not exist");
    }

    public void cancelFlight(String flightId) throws FlightNotFoundException {
        Flight flight = searchFlight(flightId);
        if("Cancelled".equals(flight.getFlightStatus())){
            System.out.println("Flight " + flightId + " is already cancelled");
            return;
        }
        flight.cancel();
        flight.getAircraft().markAvailable();
        flight.getPilot().markAvailable();
        System.out.println("Flight " + flightId + " has been cancelled successfully");
    }

    public void displayAll() {
        if (flightCount == 0) {
            System.out.println("No Flights Scheduled");
            return;
        }
        for (int i = 0; i < flightCount; i++) {
            flights[i].displayDetails();
        }
    }
}
