
import DatabaseManager.CustomerManager;

import GUI.CustomerRegisterUI;
import GUI.CustomerUI;
import GUI.MakePaymentUI;
import GUI.ReceiptUI;
import GUI.ReservationEditorUI;
import Payment.Bill;
import Reservation.ReservationSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bprieto
 */
public class Controller implements ActionListener {

    CustomerModel m;
    CustomerUI cui;
    CustomerRegisterUI crui;
    ReservationEditorUI reui;
    MakePaymentUI mui;
    ReceiptUI rui; 
    CustomerManager dataManager = new CustomerManager();
    String username=null;

    public Controller(CustomerModel m, CustomerUI cui, CustomerRegisterUI crui, ReservationEditorUI reui, MakePaymentUI mui) {
        this.m = m;
        this.cui = cui;
        this.crui = crui;
        this.reui = reui;

        this.mui = mui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cui.getLoginButton()) {
            m.setUsername(cui.getUsernameTextField().getText());
            m.setPassword(cui.getPasswordField().getText());
                
            if (dataManager.isCustomer(m.getUsername(), m.getPassword()) == true) {
                cui.setVisible(false);

                reui.setVisible(true);
                reui.getMakeReservButton().setEnabled(false);
                username=cui.getUsernameTextField().getText();
            }

        }else if (e.getSource() == cui.getRegisterButton()) {
            cui.setVisible(false);
            crui.setVisible(true);

        } else if (e.getSource() == crui.getLoginButton()) {
            String firstname = crui.getFirstNameText().getText();
            String surname = crui.getSurnameText().getText();
            String phone = crui.getPhoneText().getText();
            String email = crui.getEmailText().getText();
            String username = crui.getUsernameText().getText();
            String password = crui.getPasswordText().getText();

            if (username.length() <= 20 && username.length() > 0 && password.length() <= 15 && password.length() > 0 && firstname.length() <= 25 && surname.length() <= 25 && phone.length() <= 10 && email.length() <= 40) {
                dataManager.addCustomer(username, password, firstname, surname, email, phone);
                crui.setVisible(false);
                cui.setVisible(true);
            } else {
                System.out.println("request cant be reached");
            }

        } else if (e.getSource() == crui.getBackButton()) {
            crui.setVisible(false);
            cui.setVisible(true);

        } else if (e.getSource() == reui.getCheckAvailButton()) {
            
           
            String arrivalDate = reui.getDateOfArrivalField().getText();
            LocalDate checkIn = LocalDate.parse(arrivalDate, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
                    
            String departureDate = reui.getDateOfDepartureField().getText();
            LocalDate checkOut = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));

            String Hotel = reui.getSelectHotelCombo().getSelectedItem().toString();
            String Room = reui.getSelectRoomCombo().getSelectedItem().toString();
            String nbGuests = reui.getSelectNoOfGuestsCombo().getSelectedItem().toString();
            if (checkIn.isAfter(LocalDate.now())) {
                if (checkOut.isAfter(checkIn)) {
                    if (ReservationSystem.getInstance().testRoom(Hotel, Room, checkIn, checkOut)) {
                           //System.out.println( checkIn.getDayOfWeek());
                        //JOptionPane.showMessageDialog(null, "Reservation is available!");  
                        reui.getMakeReservButton().setEnabled(true);
                        
                        
                            
                    } else {
                        
                        //"if Room not available cause of reservation");
                        reui.getMakeReservButton().setEnabled(false);
                    }

                } else {
                    //System.out.println("if checkout is not after checkIn");
                    reui.getMakeReservButton().setEnabled(false);
                }

            } else {
                //System.out.println("if checkin not after now");
                reui.getMakeReservButton().setEnabled(false);

            }

        } else if (e.getSource() == reui.getMakeReservButton()) {
            int random = (int) (Math.random() * (100000 - 0));
            
            String arrivalDate = reui.getDateOfArrivalField().getText();
            LocalDate checkIn = LocalDate.parse(arrivalDate, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));

            String departureDate = reui.getDateOfDepartureField().getText();
            LocalDate checkOut = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
              
            Period days=checkIn.until(checkOut);
            int numberOfNights=days.getDays();
            String Hotel = reui.getSelectHotelCombo().getSelectedItem().toString();
            String Room = reui.getSelectRoomCombo().getSelectedItem().toString();
            String nbGuests = reui.getSelectNoOfGuestsCombo().getSelectedItem().toString();
            
            ReservationSystem.getInstance().makeReservation(username,checkIn,checkOut, Hotel, Room,nbGuests,random);
            
            dataManager.writeReservation(username,Hotel, Room, nbGuests, checkIn, numberOfNights, random);
            Bill bill = new Bill(); 
            double totalCost = bill.calculateCost(Hotel, Room, nbGuests, arrivalDate, numberOfNights, random);
            //String cost = totalCost.toString(); 
            JOptionPane.showMessageDialog(null, "Hotel: " + "\t" + Hotel + "\n" + "\n" +
                                                "Room Type: " + "\t" + Room + "\n" + "\n" +
                                                "Number of Guests: " + "\t" + nbGuests +"\n" + "\n" +
                                                "ArrivalDate: " + "\t" + arrivalDate + "\n" + "\n" +
                                                "Departure Date: " + "\t" + departureDate + "\n" + "\n" +
                                                "Total Cost: " + "\t" + totalCost
                                                 );
            //System.out.println("joe");
            mui.setVisible(true);
            reui.setVisible(false);

            

        }
         else if(e.getSource() == mui.getPayNowButton()) {
            String nameOnCard    = mui.getNameCardText().getText(); 
            String cardNumber    = mui.getCardNumberText().getText();
            //int CardNumber       = Integer.parseInt(cardNumber); 
            String expiryDate    = mui.getExpiryDateText().getText();
            //LocalDate ExpiryDate = LocalDate.parse(expiryDate,DateTimeFormatter.ofPattern("DD/MM/YYYY").withResolverStyle(ResolverStyle.STRICT));
            String CV2Num        = mui.getCV2Text().getText(); 
            //int CV2Number        = Integer.parseInt(CV2Num); 
            String saveDetails   = mui.getFuturePaymentsBox().getText(); 
            dataManager.addCreditCardDetails(nameOnCard, cardNumber, expiryDate, CV2Num, saveDetails);
            JOptionPane.showMessageDialog(null, "Reservation Made!");
            mui.setVisible(false);
            reui.setVisible(true);
        }

    }

}

