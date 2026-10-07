package model;

import exception.InvalidAircraftException;

public class PassengerAircraft extends Aircraft{

    private int numberOfPassengers;
    private boolean businessClassAvailable;

    public PassengerAircraft(String aircraftId, String model, String manufacturer, int capacity, int numberOfPassengers, boolean businessClassAvailable) throws InvalidAircraftException {
        super(aircraftId, model, manufacturer, capacity);
        this.numberOfPassengers = numberOfPassengers;
        this.businessClassAvailable = businessClassAvailable;
    }


    @Override
    public void displayAircraftDetails() {
        System.out.println("[Passenger] Id: " + getAircraftId() + ",Model:" + getModel() + ",Status:" + getStatus() + ",Passengers:" +numberOfPassengers + ", BusinessClass:" + (businessClassAvailable ? "Yes" : "No"));
    }

    @Override
    public String getAircraftType() {
        return "Passenger Aircraft";
    }
}
