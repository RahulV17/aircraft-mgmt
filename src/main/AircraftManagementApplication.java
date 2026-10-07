package main;

import java.util.Scanner;
import exception.AircraftNotFoundException;
import exception.FlightNotFoundException;
import exception.PilotNotFoundException;
import model.Aircraft;
import model.CargoAircraft;
import model.Flight;
import model.PassengerAircraft;
import model.Pilot;
import model.PrivateAircraft;
import service.AircraftManager;
import service.FlightManager;
import service.PilotManager;

public class AircraftManagementApplication {
  private static Scanner sc = new Scanner(System.in);
  private static AircraftManager aircraftManager = new AircraftManager();
  private static PilotManager pilotManager = new PilotManager();
  private static FlightManager flightManager = new FlightManager(aircraftManager, pilotManager);

  public static void main(String[] args) {
    while (true) {
      printMenu();
      int choice;
      try {
        choice = Integer.parseInt(sc.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Enter 1-11.");
        continue;
      }
      try {
        switch (choice) {
          case 1 -> registerAircraft();
          case 2 -> aircraftManager.displayAll();
          case 3 -> searchAircraft();
          case 4 -> addPilot();
          case 5 -> pilotManager.displayAll();
          case 6 -> searchPilot();
          case 7 -> scheduleFlight();
          case 8 -> flightManager.displayAll();
          case 9 -> searchFlight();
          case 10 -> cancelFlight();
          case 11 -> {
            System.out.println("Exiting. Goodbye!");
            return;
          }
          default -> System.out.println("Invalid choice. Enter 1-11.");
        }
      } catch (Exception e) {
        System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
      } finally {
        System.out.println("----------------------------------------");
      }
    }
  }

  private static void printMenu() {
    System.out.println("=========================================");
    System.out.println("       AIRCRAFT MANAGEMENT SYSTEM");
    System.out.println("=========================================");
    System.out.println("1. Register Aircraft\n2. Display All Aircraft\n3. Search Aircraft");
    System.out.println("4. Add Pilot\n5. Display All Pilots\n6. Search Pilot");
    System.out.println("7. Schedule Flight\n8. Display All Flights\n9. Search Flight");
    System.out.println("10. Cancel Flight\n11. Exit");
    System.out.print("Enter your choice: ");
  }

  private static void registerAircraft() {
    try {
      System.out.println("1. Passenger  2. Cargo  3. Private");
      System.out.print("Enter choice: ");
      int type = Integer.parseInt(sc.nextLine().trim());
      System.out.print("Enter Aircraft ID: "); String id = sc.nextLine().trim();
      System.out.print("Enter Model: "); String model = sc.nextLine().trim();
      System.out.print("Enter Manufacturer: "); String mfr = sc.nextLine().trim();
      System.out.print("Enter Capacity: "); int cap = Integer.parseInt(sc.nextLine().trim());
      Aircraft a = null;
      if (type == 1) {
        System.out.print("Passengers: "); int n = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Business class (true/false): "); boolean b = Boolean.parseBoolean(sc.nextLine().trim());
        a = new PassengerAircraft(id, model, mfr, cap, n, b);
      } else if (type == 2) {
        System.out.print("Cargo tons: "); double cc = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Cargo type: "); String ct = sc.nextLine().trim();
        a = new CargoAircraft(id, model, mfr, cap, cc, ct);
      } else if (type == 3) {
        System.out.print("Owner: "); String o = sc.nextLine().trim();
        System.out.print("Luxury level: "); String l = sc.nextLine().trim();
        a = new PrivateAircraft(id, model, mfr, cap, o, l);
      } else { System.out.println("Invalid type."); return; }
      aircraftManager.addAircraft(a);
      System.out.println("Aircraft registered.");
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Capacity must be a number.");
    } catch (Exception e) {
      System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
    }
  }

  private static void searchAircraft() {
    System.out.print("Enter Aircraft ID: "); String id = sc.nextLine().trim();
    try { aircraftManager.searchAircraft(id).displayAircraftDetails(); }
    catch (AircraftNotFoundException e) { System.out.println(e.getMessage()); }
  }
  private static void addPilot() {
    try {
      System.out.print("Pilot ID: "); String id = sc.nextLine().trim();
      System.out.print("Name: "); String name = sc.nextLine().trim();
      System.out.print("License: "); String lic = sc.nextLine().trim();
      System.out.print("Experience: "); int exp = Integer.parseInt(sc.nextLine().trim());
      pilotManager.addPilot(new Pilot(id, name, lic, exp));
      System.out.println("Pilot added.");
    } catch (NumberFormatException e) { System.out.println("Experience must be a number."); }
    catch (Exception e) { System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage()); }
  }
  private static void searchPilot() {
    System.out.print("Pilot ID: "); String id = sc.nextLine().trim();
    try { pilotManager.searchPilot(id).displayDetails(); }
    catch (PilotNotFoundException e) { System.out.println(e.getMessage()); }
  }
  private static void scheduleFlight() {
    try {
      System.out.print("Flight ID: "); String fid = sc.nextLine().trim();
      System.out.print("Source: "); String s = sc.nextLine().trim();
      System.out.print("Destination: "); String d = sc.nextLine().trim();
      System.out.print("Aircraft ID: "); String aid = sc.nextLine().trim();
      System.out.print("Pilot ID: "); String pid = sc.nextLine().trim();
      flightManager.scheduleFlight(fid, aid, pid, s, d);
      System.out.println("Flight scheduled.");
    } catch (Exception e) { System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage()); }
  }
  private static void searchFlight() {
    System.out.print("Flight ID: "); String id = sc.nextLine().trim();
    try { Flight f = flightManager.searchFlight(id); f.displayDetails(); }
    catch (FlightNotFoundException e) { System.out.println(e.getMessage()); }
  }
  private static void cancelFlight() {
    System.out.print("Flight ID: "); String id = sc.nextLine().trim();
    try { flightManager.cancelFlight(id); }
    catch (FlightNotFoundException e) { System.out.println(e.getMessage()); }
  }
}

