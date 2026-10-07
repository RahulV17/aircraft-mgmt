package service;

import exception.AircraftAlreadyExistsException;
import exception.AircraftNotFoundException;
import exception.InvalidAircraftException;
import model.Aircraft;

public class AircraftManager {
    private Aircraft[] aircrafts = new Aircraft[50];
    private int aircraftCount = 0;

    public void addAircraft(Aircraft aircraft) throws AircraftAlreadyExistsException, InvalidAircraftException {
        if (aircraft == null) throw new InvalidAircraftException("Aircraft cannot be null");
        if (aircraftCount >= aircrafts.length) throw new InvalidAircraftException("Storage is Full");

        for (int i = 0; i < aircraftCount; i++) {
            if (aircrafts[i].getAircraftId().equalsIgnoreCase(aircraft.getAircraftId())) {
                throw new AircraftAlreadyExistsException("Aircraft " + aircraft.getAircraftId() + "already Registered ");
            }
        }
        aircrafts[aircraftCount++] = aircraft;
    }

    public Aircraft searchAircraft(String aircraftId) throws AircraftNotFoundException {
        for (int i = 0; i < aircraftCount; i++) {
            if (aircrafts[i].getAircraftId().equalsIgnoreCase(aircraftId)) {
                return aircrafts[i];
            }
        }
        throw new AircraftNotFoundException("Aircraft " + aircraftId + "does not exist");
    }

    public Aircraft[] searchAircraft(String model, String manufacturer) {
        Aircraft[] temp = new Aircraft[aircraftCount];
        int found = 0;
        for (int i = 0; i < aircraftCount; i++) {
            boolean m1 = model == null || model.isEmpty() || aircrafts[i].getModel().equalsIgnoreCase(model);
            boolean m2 = manufacturer == null || manufacturer.isEmpty() || aircrafts[i].getManufacturer().equalsIgnoreCase(manufacturer);
            if (m1 && m2) temp[found++] = aircrafts[i];
        }
        Aircraft[] result = new Aircraft[found];
        for (int i = 0; i < found; i++) {
            result[i] = temp[i];
        }
            return result;
        }

    public void displayAll() {
        if (aircraftCount == 0) {
            System.out.println("No Aircrafts Registered");
            return;
        }
        for (int i = 0; i < aircraftCount; i++) {
            aircrafts[i].displayAircraftDetails();
        }
    }
}
