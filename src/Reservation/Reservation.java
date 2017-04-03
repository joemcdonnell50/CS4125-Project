/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

import java.time.LocalDate;

/**
 *
 * @author bprieto
 */
public class Reservation {
    
    private int uniqueReservationCode;
    private String username;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalCost=0;
    private int numberOfRooms;
    private String reservationType;
    private boolean havePaid;

    public Reservation(int uniqueReservationCode, String reservationName, LocalDate checkInDate, LocalDate checkOutDate) {
        this.uniqueReservationCode = uniqueReservationCode;
        this.username = reservationName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
       // this.havePaid = havePaid;
    }

    public int getUniqueReservationCode() {
        return uniqueReservationCode;
    }

    public void setUniqueReservationCode(int uniqueReservationCode) {
        this.uniqueReservationCode = uniqueReservationCode;
    }

    public String getReservationName() {
        return username;
    }

    public void setReservationName(String reservationName) {
        this.username = reservationName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

   

  

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public boolean isHavePaid() {
        return havePaid;
    }

    public void setHavePaid(boolean havePaid) {
        this.havePaid = havePaid;
    }

}
