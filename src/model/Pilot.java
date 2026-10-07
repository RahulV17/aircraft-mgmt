package model;

import exception.PilotAlreadyAssignedException;

public class Pilot {
    private String pilotId;
    private String pilotName;
    private String licenseNumber;
    private int experience;
    private boolean available;

    public Pilot(String pilotId, String pilotName, String licenseNumber, int experience) {
        if(pilotId == null || pilotId.trim().isEmpty()){
            throw new IllegalArgumentException("Pilot ID cannot be empty");
        }
        if(experience < 0){
            throw new IllegalArgumentException("Pilot experience cannot be negative");
        }
        this.pilotId = pilotId;
        this.pilotName = pilotName;
        this.licenseNumber = licenseNumber;
        this.experience = experience;
        this.available = true;
    }

    public String getPilotId() {
        return pilotId;
    }

    public String getPilotName() {
        return pilotName;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getAvailability(){
        return available ? "Available" : "Assigned";
    }

    public void assignToFlight() throws PilotAlreadyAssignedException {
        if(!available){
            throw new PilotAlreadyAssignedException("Pilot " +pilotId +"is already assigned");
        }
        this.available = false;
    }

    public void markAvailable(){
        this.available = true;
    }

    public void displayDetails(){
        System.out.println("Pilot Id: " + pilotId + ", Name: " + pilotName + ", License: " + licenseNumber + ", Experience: " + experience +", Availability: " + getAvailability());
    }
}
