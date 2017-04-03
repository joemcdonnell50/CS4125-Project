package Payment;

import DatabaseManager.CustomerManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class Receipt {
   private String UserName,hotel,roomType,nbOfGuests,dateArrival,NbOfNights,uniqueNumber;
  //private LocalDate ye;
       
        public String getUsername(){
            return UserName;
        }
        public void setUsername(String UserName){
            this.UserName = UserName;
        }
        public String getHotel(){
         return hotel;
        }
        public void setHotel(String hotel){
            this.hotel = hotel;
        }
        public String getRoomType(){
            return roomType;
        }
        public void setRoomType(String roomType){
            this.roomType = roomType;
        }
        public String getNbOfGuests(){
            return nbOfGuests;
        }
        public void setNbOfGuests(String nbOfGuests){
            this.nbOfGuests = nbOfGuests;
        }
        public String getDateArrival(){
            return dateArrival;
        }
        public void setDateArrival(String dateArrival){
            this.dateArrival = dateArrival;
        }
         public String getNbOfNights(){
            return NbOfNights;
        }
        public void setNbOfNights(String NbOfNights){
            this.NbOfNights = NbOfNights;
        }
         public String getUniqueNumber(){
            return uniqueNumber;
        }
        public void setUniqueNumber(String uniqueNumber){
            this.uniqueNumber = uniqueNumber;
        }
        
        public void getReciept(){
        CustomerManager customermanager = new CustomerManager();
        Bill bill = new Bill();
         ArrayList<String> Reservations = new ArrayList<>();
         Reservations = customermanager.getReservation();
        int Lastline = Reservations.size()-2;
        String[] CardDets = Reservations.get(Lastline).split(",");
        setUsername(CardDets[0]);
        setHotel(CardDets[1]);
        setRoomType(CardDets[2]);
        setNbOfGuests(CardDets[3]);
        setDateArrival(CardDets[4]);
        setNbOfNights(CardDets[5]);
        setUniqueNumber(CardDets[6]);
        double total=bill.calculateCost(CardDets[1],CardDets[2],CardDets[3],CardDets[4],Integer.parseInt(CardDets[5]),Integer.parseInt(CardDets[6]));
        FormatReceipt(total);
        }  
        public void FormatReceipt(double total){
            
            
            String Receipt = ("Hello " + getUsername() + "\n" +
                               "This is your receipt for reservation number " + getUniqueNumber() +"\n" 
                              + "Hotel : " + getHotel() + "\n" + 
                                "Roomtype : "+ getRoomType() + "\n" +
                                "Number of Guests : " + getNbOfGuests() + "\n" +
                                "Date of Arrival : " + getDateArrival() + "\n" +
                                "Number of Nights : " + getNbOfNights() + "\n" +  
                                "Your total cost is " + total
                    );
            System.out.println(Receipt);
            
        }
}
