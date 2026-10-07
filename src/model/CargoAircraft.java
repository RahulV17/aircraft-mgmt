package model;

import exception.InvalidAircraftException;

public class CargoAircraft extends Aircraft{
    private double cargoCapacity;
    private String cargoType;

    public CargoAircraft(String aircraftId, String model, String manufacturer, int capacity, double cargoCapacity, String cargoType) throws InvalidAircraftException {
        super(aircraftId, model, manufacturer, capacity);
        this.cargoCapacity = cargoCapacity;
        this.cargoType = cargoType;
    }

    @Override
    public void displayAircraftDetails() {
        System.out.println("[Cargo] Id: " + getAircraftId() +", Model:" + getModel() + ", Status: " + getStatus() + ",Cargo: " + cargoCapacity + ",tons: " + cargoType);
    }

    @Override
    public String getAircraftType() {
        return "Cargo Aircraft";
    }
}
