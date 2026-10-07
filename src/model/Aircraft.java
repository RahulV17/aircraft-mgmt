package model;

import contract.Maintenance;
import exception.AircraftNotAvailableException;
import exception.InvalidAircraftException;

public abstract class Aircraft implements Maintenance {
    private String aircraftId;
    private String model;
    private String manufacturer;
    private int capacity;
    private String status;

    public Aircraft(String aircraftId, String model, String manufacturer, int capacity) throws InvalidAircraftException {
        if (aircraftId == null || aircraftId.trim().isEmpty()) {
            throw new InvalidAircraftException("Aircraft ID cannot be empty");
        }
        this.aircraftId = aircraftId.trim();
        this.model = model;
        this.manufacturer = manufacturer;
        this.capacity = capacity;
        validateCapacity(capacity);
        this.status = "Available";
    }

    public String getAircraftId() {
        return aircraftId;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }


    public void validateCapacity(int capacity) throws InvalidAircraftException {
        if (capacity <= 0) {
            throw new InvalidAircraftException("Capacity must be greater than zero");
        }
    }

    public void assignFlight() throws AircraftNotAvailableException {
        if (!"Available".equals(status)) {
            throw new AircraftNotAvailableException("Aircraft " + aircraftId + " is currently assigned to another flight");
        }
        this.status = "Assigned";
    }

    public void markAvailable(){
        this.status = "Available";
    }

    public void start(){
        System.out.println("Aircraft " + aircraftId + " is starting...");
    }

    public void stop(){
        System.out.println("Aircraft " + aircraftId + " is stopping...");
    }
    
    @Override
    public void performMaintenance(){
        this.status = "Maintenance";
        System.out.println("Aircraft " + aircraftId + " under maintenance");
    }

    @Override
    public boolean isMaintenanceRequired() {
        return "Maintenance".equals(status);
    }

    public abstract void displayAircraftDetails();
    public abstract String getAircraftType();


}

