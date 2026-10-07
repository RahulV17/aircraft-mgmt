package model;

import exception.InvalidAircraftException;

public class PrivateAircraft extends Aircraft{
    private String OwnerName;
    private String luxuryLevel;

    public PrivateAircraft(String aircraftId, String model, String manufacturer, int capacity, String ownerName, String luxuryLevel) throws InvalidAircraftException {
        super(aircraftId, model, manufacturer, capacity);
        OwnerName = ownerName;
        this.luxuryLevel = luxuryLevel;
    }

    @Override
    public void displayAircraftDetails() {
        System.out.println("[Private] Id: " + getAircraftId() + ", Model: " + getModel() + ", Status: " + getStatus() + ", Owner: " + OwnerName + ", Luxury Level: " + luxuryLevel);
    }

    @Override
    public String getAircraftType() {
        return "Private Aircraft";
    }
}
