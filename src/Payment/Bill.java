/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;
import Services.Hotel;

import Reservation.ReservationSystem;
import Services.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

//import java.time.LocalDate;
/**
 *
 * @author Owner
 */
public class Bill{
    //ReservationSystem rs= new ReservationSystem();
  public double total;
        public Bill(){
            
        }
        public void checkBill(){
       
            System.out.println(total);
       
        }
        public double getTotal(){
            return total;
        }
         public void setTotal(double total){
           this.total = total;
        }
          public double calculateCost(String hotel,String Roomtype,String NbOfGuests,String DateofArrival,int NbOfNights,int UReservation){
               
              int i=0;
              int daysOnWeekend = 0;
              int indexHotel=0;
              for(Hotel h : ReservationSystem.getInstance().getHotels()){
                  if(h.getLocation().equals(hotel)){
                      indexHotel=i;
                      
                  }
                  i++;
              }
              int j = 0;
              int indexRoom =0;
            for(Room r : ReservationSystem.getInstance().getHotels().get(indexHotel).getRooms()){
                  if(r.getType().equals(Roomtype)){
                      indexRoom=j;
                      
                  }
                  j++;
            }
              double PricePerNight=ReservationSystem.getInstance().getHotels().get(indexHotel).getRooms().get(indexRoom).getPricePerNight();
               System.out.println(PricePerNight);
              int NumberofNight = NbOfNights;
              int NumberOfGuests = Integer.parseInt(NbOfGuests);
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
              LocalDate date = LocalDate.parse(DateofArrival,formatter);
        for(int k = 0; k <= NumberofNight; k++){
                        
                         
                        String day =date.getDayOfWeek().toString();
                        //System.out.println(day);
                        if(day.equalsIgnoreCase("Saturday") || day.equalsIgnoreCase("Sunday")){
                        daysOnWeekend++;            
             }
                        date=date.plusDays(1);
                        
        }    
        double WeekendCost = ((PricePerNight / 100.0) * 25);
        double Cost = ((NumberofNight*PricePerNight)*NumberOfGuests) + (WeekendCost * daysOnWeekend);
           return Cost;
            
            
        }
        public void addToBill(int addedvalue){
            this.total += addedvalue;
           
        }
	 public boolean makePayment(){
             
            
           return true;
        }
}