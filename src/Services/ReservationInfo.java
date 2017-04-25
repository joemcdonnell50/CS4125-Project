/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.time.LocalDate;

/**
 *
 * @author bprieto
 */
public class ReservationInfo {
    //Factory method for ReservationInfo
    public static ReservationInfo create(LocalDate checkIn, LocalDate checkOut, int uniqueReservationCode) {
        return new ReservationInfo(checkIn, checkOut, uniqueReservationCode);
    }
    
    LocalDate checkIn;
    LocalDate checkOut;
    int uniqueReservationCode;

    private ReservationInfo(LocalDate checkIn, LocalDate checkOut, int uniqueReservationCode) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.uniqueReservationCode = uniqueReservationCode;
    }
    
    

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getUniqueReservationCode() {
        return uniqueReservationCode;
    }

    public void setUniqueReservationCode(int uniqueReservationCode) {
        this.uniqueReservationCode = uniqueReservationCode;
    }
    
    
    
    
}
