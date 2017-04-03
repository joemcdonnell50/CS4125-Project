/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import Reservation.Reservation;
import Reservation.ReservationSystem;
import Services.Hotel;
import Services.Room;
import Services.Service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bprieto
 */
public class CustomerManager {

    FileReader readFile = null;

    BufferedReader buffer = null;

    FileWriter writeFile = null;

    BufferedWriter out = null;

    PrintWriter fich = null;

    public CustomerManager() {

    }

    public boolean isCustomer(String username, String password) {
        try {
            readFile = new FileReader("filesRequired/customer.csv");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        buffer = new BufferedReader(readFile);
        String line = null;

        while (true) {

            try {
                line = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (line == null) {
                return false;
            }
            String[] parts = line.split(",");
           

            if ((username.equals(parts[0])) && (password.equals(parts[1]))) {
                return true;

            }

        }
    }

    public void addCustomer(String username, String password, String firstname, String surname, String email, String phone) {

        try {
            fich = new PrintWriter(new BufferedWriter(new FileWriter("filesRequired/customer.csv", true)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        fich.print(username + ","
                + password + ","
                + firstname + ","
                + surname + ","
                + email + ","
                + phone);

        fich.println("");

        fich.close();

    }
    public void addCreditCardDetails(String nameOnCard, String cardNumber, String expiryDate, String CV2Num, String saveDetails) {

        try {
            fich = new PrintWriter(new BufferedWriter(new FileWriter("filesRequired/CardDetails.csv", true)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        fich.print(nameOnCard + ","
                + cardNumber + ","
                + expiryDate + ","
                + CV2Num + ","
                + saveDetails);

        fich.println("");

        fich.close();

    }
    public void writeReservation(String username,String location,String roomType,String nbOfGuest,LocalDate dateArrival, int numberOfNights, int uniqueNumber){
         DateTimeFormatter dateToString = DateTimeFormatter.ofPattern("dd/MM/yyyy");
             String arrivalDate=dateArrival.format(dateToString);
        
            
        try {
            fich = new PrintWriter(new BufferedWriter(new FileWriter("filesRequired/reservations.csv", true)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
        fich.print(username+","
                + location+","
                +roomType+","
                + nbOfGuest+","
                + arrivalDate+","
                + numberOfNights+","
                +uniqueNumber);
      

        fich.println("");

        fich.close();
        
    }
    public void initializeReservations(ReservationSystem rs){
        try {
            readFile = new FileReader("filesRequired/reservations.csv");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        buffer = new BufferedReader(readFile);
        String line = null;
        
        

        while (true) {
            

            try {
                line = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (line == null) {
                break;
            }
            String[] parts = line.split(",");
            String username =parts[0];
            String location = parts[1];
            String roomType= parts[2];
            String nbOfGuests= parts[3];
            LocalDate checkIn= LocalDate.parse(parts[4], DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
            LocalDate checkOut= checkIn.plusDays(Integer.parseInt(parts[5]));
            int uniqueReservationNumber= Integer.parseInt(parts[6]);
            DateTimeFormatter dateToString = DateTimeFormatter.ofPattern("dd/MM/yyyy");
             String arrivalDate=checkOut.format(dateToString);
             
            rs.makeReservation(username, checkIn, checkOut, location, roomType, nbOfGuests, uniqueReservationNumber);
            
        
    }
        
    }
    public ArrayList<String> getReservation(){
         ArrayList<String> Reservations = new ArrayList<String>();
            FileReader readFile = null;
            BufferedReader buffer = null;
        try {
            readFile = new FileReader("filesRequired/reservations.csv");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        buffer = new BufferedReader(readFile);
        String line = null;
        while (true) {
            try {
                line = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
           Reservations.add(line);
        }
        return Reservations;
    }
    
    public void initalizeHotelRoom(ReservationSystem rs){
        
        try {
            readFile = new FileReader("filesRequired/HotelRoom.csv");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        buffer = new BufferedReader(readFile);
        String line = null;
        
        int i=0;

        while (true) {
            

            try {
                line = buffer.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (line == null) {
                break;
            }
            String[] parts = line.split(",");

            rs.getHotels().add(new Hotel(parts[0],new Service()));
            int nbr = parts.length-1/4;
            int k=1;
            int master=parts.length/4;
            for(int g = 0 ; g<nbr;g++){
                for (int j=0; j<Integer.parseInt(parts[k]);j++){
                    rs.getHotels().get(i).getRooms().add(new Room(parts[k+1],Integer.parseInt(parts[k+2]),Double.parseDouble(parts[k+3])));
                       // in the file parts[1]= nbre of rooms of the following type
                       //parts[2]=the type
                       //parts[3]=the max occupancy 
                       // parts[4]= the price
                
                }
                k=k+4;
                
                if(k==nbr){
                    break;
                }
      
            }
           
            i++;

        }
        
    }
}
