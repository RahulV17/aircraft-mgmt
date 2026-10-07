package service;

import exception.PilotAlreadyAssignedException;
import exception.PilotNotFoundException;
import model.Pilot;

public class PilotManager {
    private Pilot[] pilots = new Pilot[50];
    private int pilotCount = 0;

    public void addPilot(Pilot pilot) throws PilotAlreadyAssignedException {
        if(pilot == null) throw new IllegalArgumentException("Pilot cannot be null");
        if(pilotCount >= pilots.length) throw new IllegalStateException("Pilots are fully assigned");
        for(int i = 0; i < pilotCount; i++){
            if(pilots[i].getPilotId().equalsIgnoreCase(pilot.getPilotId())){
                throw new PilotAlreadyAssignedException("Pilot" + pilot.getPilotId() + "already exists");
            }
        }
        pilots[pilotCount++] = pilot;
    }

    public Pilot searchPilot(String pilotId) throws PilotNotFoundException {
        for(int i = 0; i < pilotCount; i++){
            if(pilots[i].getPilotId().equalsIgnoreCase(pilotId)){
                return pilots[i];
            }
        }
        throw new PilotNotFoundException("Pilot " + pilotId + " does not exist");
    }

    public void displayAll() {
        if(pilotCount == 0){
            System.out.println("No Pilots Registered");
            return;
        }
        for(int i = 0; i < pilotCount; i++){
            pilots[i].displayDetails();
        }
    }
}
