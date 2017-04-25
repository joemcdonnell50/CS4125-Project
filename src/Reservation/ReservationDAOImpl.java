/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservation;

import Services.Hotel;
import java.util.ArrayList;

/**
 *
 * @author bprieto
 */
public interface ReservationDAOImpl {
   
public ArrayList<Reservation> getReservations();
public Reservation getReservation(int index);
public void deleteReservation(Reservation reservation);
    
}