/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

import DatabaseManager.CustomerManager;
import Services.Hotel;
import Services.Room;
import java.util.ArrayList;
import Reservation.Reservation;
import Services.ReservationInfo;
import java.time.LocalDate;

/**
 *
 * @author bprieto
 */
public class ReservationSystem {

    private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    private ArrayList<Hotel> hotels = new ArrayList<Hotel>();
    CustomerManager dataManager=new CustomerManager();

    public ReservationSystem() {
    }
    private static ReservationSystem INSTANCE = new ReservationSystem();

    public static ReservationSystem getInstance() {
        return INSTANCE;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void makeReservation(String username, LocalDate checkIn, LocalDate checkOut, String location, String roomType,String nbGuests,int random) {
        
        // we have to check if random not already use
        this.getReservations().add(new Reservation(random, username, checkIn, checkOut));
        int i = 0;
        int indexHotel = 0;
        for (Hotel h : this.hotels) {
            if (h.getLocation().equals(location)) {
                indexHotel = i;
                break;
            }
            i++;

        }
        boolean available = true;

        for (Room r : this.hotels.get(indexHotel).getRooms()) {
            

            if (r.getType().equals(roomType)) {
                available = true;

                for (ReservationInfo rInfo : r.getResInfo()) {
                    if ((checkIn.isBefore(rInfo.getCheckOut()) && checkOut.isAfter(rInfo.getCheckIn())) || (checkOut.isAfter(rInfo.getCheckOut()) && checkIn.isBefore(rInfo.getCheckOut())) || (checkIn.isAfter(rInfo.getCheckIn()) && checkOut.isBefore(rInfo.getCheckOut()))) {
                        available = false;
                    }

                }
                if (available == true) {
                    r.getResInfo().add(new ReservationInfo(checkIn, checkOut, random));
                    break;
                    
                }

            }

        }
        

    }

    public void displayAllRooms() {
        for (Hotel h : this.hotels) {
            for (Room r : h.getRooms()) {
                System.out.println(r.getType());
                for (ReservationInfo rInfo : r.getResInfo()) {
                    System.out.println(rInfo.getUniqueReservationCode());
                }
            }

            System.out.println("");
            System.out.println("");
            System.out.println("");
        }
    }

    public void displayAllReservations() {
        for (Reservation r : this.getReservations()) {
            System.out.println(r.getUniqueReservationCode());
        }
    }

    public boolean testRoom(String location, String roomType, LocalDate checkIn, LocalDate checkOut) {
        int i = 0;
        int indexHotel = 0;
        for (Hotel h : this.hotels) {
            if (h.getLocation().equals(location)) {
                indexHotel = i;
                break;
            }
            i++;

        }
        boolean available = true;
        for (Room r : this.hotels.get(indexHotel).getRooms()) {
            available = true;
            if (r.getType().equals(roomType)) {
                for (ReservationInfo rInfo : r.getResInfo()) {
                    if ((checkIn.isBefore(rInfo.getCheckOut()) && checkOut.isAfter(rInfo.getCheckIn())) || (checkOut.isAfter(rInfo.getCheckOut()) && checkIn.isBefore(rInfo.getCheckOut())) || (checkIn.isAfter(rInfo.getCheckIn()) && checkOut.isBefore(rInfo.getCheckOut()))) {
                        available = false;
                    }

                }
                if (available == true) {
                    return true;
                }

            }

        }
        return false;

    }

}
