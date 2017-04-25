
import Customer.ConnectedState;
import Customer.Customer;
import Customer.DisconnectedState;
import DatabaseManager.FileManager;
import GUI.CancelReservation;

import GUI.CustomerRegisterUI;
import GUI.CustomerUI;
import GUI.MainMenu;
import GUI.MakePaymentUI;
import GUI.ReceiptUI;
import GUI.ReservationEditorUI;

import GUI.ServicesUI;

import Payment.*;
import Services.*;
import Reservation.ReservationSystem;
import Services.Room;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author bprieto
 */
public class Controller implements ActionListener {

    //Factory method for Controller
    public static Controller create(CancelReservation cr, MainMenu mmn, CustomerModel m, CustomerUI cui, CustomerRegisterUI crui, ReservationEditorUI reui, MakePaymentUI mui, ServicesUI sui) {
        return new Controller(cr, mmn, m, cui, crui, reui, mui, sui);
    }
    CancelReservation cr;
    CustomerModel m;
    CustomerUI cui;
    CustomerRegisterUI crui;
    ReservationEditorUI reui;
    MakePaymentUI mui;
    ReceiptUI rui;
    ServicesUI sui;
    MainMenu mmn;
    FileManager dataManager = FileManager.create();

    private Controller(CancelReservation cr, MainMenu mmn, CustomerModel m, CustomerUI cui, CustomerRegisterUI crui, ReservationEditorUI reui, MakePaymentUI mui, ServicesUI sui) {
        this.m = m;
        this.cui = cui;
        this.crui = crui;
        this.reui = reui;
        this.mui = mui;
        this.mmn = mmn;
        this.cr = cr;
        this.sui = sui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LocalDate todaysDate = LocalDate.now();
        //log in button pressed
        if (e.getSource() == cui.getLoginButton()) {
            m.getCustomer().setUsername(cui.getUsernameTextField().getText());
            m.getCustomer().setPassword(cui.getPasswordField().getText());

            if (dataManager.isCustomer(m.getCustomer().getUsername(), m.getCustomer().getPassword()) == true) {

                m.getCustomer().setUsername(cui.getUsernameTextField().getText());
                 ConnectedState cs = new ConnectedState();
                cs.doAction(m.getCustomer());
                cui.setVisible(false);
                mmn.setVisible(true);
                mmn.getHelloCustomer().setText("Hello " + m.getCustomer().getUsername());
                if (dataManager.hasDiscountAlert(m.getCustomer().getUsername())) {
                    Subject subject = new Subject();
                    new DateDiscountObserver(subject);
                    subject.setState(todaysDate);
                }
            }
                                //Register Button pressed
        } else if (e.getSource() == cui.getRegisterButton()) {
            cui.setVisible(false);
            crui.setVisible(true);

                               //if its add services button
        } else if (e.getSource() == sui.getAddServicesButton()) {
            int random = (int) (Math.random() * (10000000 - 0));

            String arrivalDate = reui.getDateOfArrivalField().getText();
            LocalDate checkIn = LocalDate.parse(arrivalDate, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));

            String departureDate = reui.getDateOfDepartureField().getText();
            LocalDate checkOut = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));

            Period days = checkIn.until(checkOut);
            int numberOfNights = days.getDays() - 1;
            int numberOfDays = days.getDays();

            double servicePrice = 0;

            boolean addAllServices = sui.getAllInBox().isSelected();
            boolean addAllHotel = sui.getAllHotelBox().isSelected();
            boolean addAllTours = sui.getAllTourBox().isSelected();

            boolean addWaw = sui.getWawBox().isSelected();
            boolean addGiants = sui.getGiantsBox().isSelected();
            boolean addCliffs = sui.getCliffsBox().isSelected();

            boolean addWifi = sui.getWiFiChckBox().isSelected();
            boolean addPool = sui.getPoolBox().isSelected();
            boolean addGym = sui.getGymBox().isSelected();
            boolean addHammam = sui.getHammamBox().isSelected();

            //composite design pattern implementation
            //leafs - individual services
            ServiceComponent wifi = new ServiceLeaf(1, "Wifi");
            ServiceComponent pool = new ServiceLeaf(2, "Pool");
            ServiceComponent hammam = new ServiceLeaf(3, "Hammam");
            ServiceComponent gym = new ServiceLeaf(5, "Gym");

            ServiceComponent wawTour = new ServiceLeaf(50, "Wild Atlantic Way Day Tour");
            ServiceComponent giantsTour = new ServiceLeaf(60, "Giants Causeway Day Tour");
            ServiceComponent cliffsTour = new ServiceLeaf(50, "Cliffs of Moher Day Tour");

            //composites - Groups of services
            ServiceComposite tours = new ServiceComposite("Tours");
            ServiceComposite basicServices = new ServiceComposite("Basic Hotel Services");

            //composite - groups of services that hold other groups of servcies
            ServiceComposite allServices = new ServiceComposite("All Services");

            //add components
            allServices.addComponent(tours);
            allServices.addComponent(basicServices);

            basicServices.addComponent(gym);
            basicServices.addComponent(pool);
            basicServices.addComponent(hammam);
            basicServices.addComponent(wifi);

            tours.addComponent(wawTour);
            tours.addComponent(giantsTour);
            tours.addComponent(cliffsTour);

            //work out price
            if (addAllServices) {
                servicePrice = servicePrice + allServices.calculatePrice();
                servicePrice = servicePrice - 21;
                
            }
            if (addAllHotel) {
                servicePrice = servicePrice + basicServices.calculatePrice();
                
            }
            if (addAllTours) {
                servicePrice = servicePrice + tours.calculatePrice();
                
            }
            if (addWaw) {
                servicePrice = servicePrice + wawTour.calculatePrice();
                
            }
            if (addGiants) {
                servicePrice = servicePrice + giantsTour.calculatePrice();
                
            }
            if (addCliffs) {
                servicePrice = servicePrice + cliffsTour.calculatePrice();
                
            }
            if (addWifi) {
                servicePrice = servicePrice + (wifi.calculatePrice() * numberOfDays);
                
            }
            if (addPool) {
                servicePrice = servicePrice + (pool.calculatePrice() * numberOfDays);
                
            }
            if (addGym) {
                servicePrice = servicePrice + (gym.calculatePrice() * numberOfDays);
                
            }
            if (addHammam) {
                servicePrice = servicePrice + (hammam.calculatePrice() * numberOfDays);
                
            }

            

            String Hotel = reui.getSelectHotelCombo().getSelectedItem().toString();
            String Room = reui.getSelectRoomCombo().getSelectedItem().toString();
            String nbGuests = reui.getSelectNoOfGuestsCombo().getSelectedItem().toString();
            ReservationSystem.getInstance().makeReservation(m.getCustomer().getUsername(), checkIn, checkOut, Hotel, Room, nbGuests, random);

            Bill bills = Bill.create();
            bills.calculateCost(Hotel, Room, nbGuests, arrivalDate, numberOfNights, random, servicePrice);

            Receipt receipt = new Receipt();
            receipt.setUsername(m.getCustomer().getUsername());
            receipt.setHotel(Hotel);
            receipt.setRoomType(Room);
            receipt.setNbOfGuests(nbGuests);
            receipt.setDateArrival(arrivalDate);
            receipt.setNbOfNights(numberOfNights);
            receipt.setUniqueNumber(random);
            receipt.setServicePrice(servicePrice);

            LocalDate discountDate = LocalDate.parse("19/04/2017", DateTimeFormatter.ofPattern("dd/MM/uuuu")
                                                        .withResolverStyle(ResolverStyle.STRICT));

            dataManager.addServices(receipt.getUniqueNumber(), m.getCustomer().getUsername(), addAllServices, addAllHotel, 
                                                    addAllTours, addWaw, addGiants, addCliffs, addWifi, addPool, addGym, addHammam);
            
            dataManager.writeReservation(m.getCustomer().getUsername(), Hotel, Room, nbGuests, checkIn, numberOfNights, random);
            {
                if (todaysDate.isEqual(discountDate)) {
                    BillInterface discountedBill = new mothersdayBillDecorator(bills);
                    ReceiptInterface discountedReceipt = new discountReceiptDecorator(receipt);
                    discountedReceipt.setOriginalCst(bills.getTotal());
                    discountedBill.setTotal(bills.getTotal());
                    double totalCost = discountedBill.getTotal();
                    receipt.setTotal(totalCost);
                    JOptionPane.showMessageDialog(null, discountedReceipt.FormatReceipt(), "Happy Mothers Day", JOptionPane.PLAIN_MESSAGE);
                } else if (Integer.parseInt(nbGuests) > 3) {
                    BillInterface discountedBill = new discountBillDecorator(bills);
                    ReceiptInterface discountedReceipt = new discountReceiptDecorator(receipt);
                    discountedReceipt.setOriginalCst(bills.getTotal());
                    discountedBill.setTotal(bills.getTotal());
                    double totalCost = discountedBill.getTotal();
                    receipt.setTotal(totalCost);
                    JOptionPane.showMessageDialog(null, discountedReceipt.FormatReceipt(), "Your Receipt", JOptionPane.PLAIN_MESSAGE);

                } else {
                    double totalCost = bills.getTotal();
                    receipt.setTotal(totalCost);
                    JOptionPane.showMessageDialog(null, receipt.FormatReceipt(), "Your Receipt", JOptionPane.PLAIN_MESSAGE);
                }
                sui.setVisible(false);
                mui.setVisible(true);
                
                if (dataManager.isDetailsSaved(m.getCustomer().getUsername())) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "Your details have already been saved, do you want to autofill info?", null, JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        ArrayList<String> CardDetails = new ArrayList<String>();
                        CreditCardPay creditcard = new CreditCardPay();
                        CardDetails = dataManager.getCardDetails();
                        int index = 0;
                        
                        for(int i=0;i<CardDetails.size();i=i+5){
                            if(CardDetails.get(i).equals(m.getCustomer().getUsername())){
                                index =i;
                                break;
                            }
                        }
                        
                        mui.getNameCardText().setText(CardDetails.get(index +1));
                        mui.getCardNumberText().setText(creditcard.decryptCard(CardDetails.get(index +2)));
                        mui.getExpiryDateText().setText(CardDetails.get(index +3));
                        mui.getFuturePaymentsBox().setVisible(false);
                    }
                }
            }
            //Services Back button
        } else if (e.getSource() == sui.getBackButton()) {
            sui.setVisible(false);
            reui.setVisible(true);

                            //Login Button pressed
        } else if (e.getSource() == crui.getLoginButton()) {
            String firstname = crui.getFirstNameText().getText();
            String surname = crui.getSurnameText().getText();
            String phone = crui.getPhoneText().getText();
            String email = crui.getEmailText().getText();
            String username = crui.getUsernameText().getText();
            String password = crui.getPasswordText().getText();
            boolean alertDiscount = crui.getjCheckBox1().isSelected();
            if (username.length() <= 20 && username.length() > 0 && password.length() <= 15 && password.length() > 0 && firstname.length() <= 25 && surname.length() <= 25 && phone.length() <= 10 && email.length() <= 40) {
                dataManager.addCustomer(username, password, firstname, surname, email, phone, alertDiscount);
                crui.setVisible(false);
                cui.setVisible(true);
            } else {
                System.out.println("request cant be reached");
            }

        } else if (e.getSource() == crui.getBackButton()) {
            crui.setVisible(false);
            cui.setVisible(true);
            //make reservation button check avaialablity
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

                                   //Make Reservation Button pressed
        } else if (e.getSource() == reui.getMakeReservButton()) {
            
            sui.setVisible(true);
            reui.setVisible(false);
                                 //Pay now Button pressed
        } else if (e.getSource() == mui.getPayNowButton()) {
            String nameOnCard = mui.getNameCardText().getText();
            String cardNumber = mui.getCardNumberText().getText();
            String expiryDate = mui.getExpiryDateText().getText();
            String CV2Num = mui.getCV2Text().getText();
            boolean saveDetails = mui.getFuturePaymentsBox().isSelected();
            CreditCardPay creditcard = CreditCardPay.create();
            creditcard.setCVNum(CV2Num);
            creditcard.setcardName(nameOnCard);
            creditcard.setcreditCardNum(cardNumber);
            creditcard.setexpireDate(expiryDate);
            boolean verified = creditcard.isCardLegit();
            if (verified == false) {
                JOptionPane.showMessageDialog(null, "Card Is Not Valid Please Try Again!");
            } else if (verified) {
                String EncCardNum, EncCV2;
                EncCardNum = creditcard.encryptCard(cardNumber);
                EncCV2 = creditcard.encryptCard(CV2Num);
                if (saveDetails == false) {
                    JOptionPane.showMessageDialog(null, "Reservation Made!");
                } else if (saveDetails) {
                    dataManager.addCreditCardDetails(m.getCustomer().getUsername(), nameOnCard, EncCardNum, expiryDate, EncCV2);
                    JOptionPane.showMessageDialog(null, "Reservation Made!");
                }
                mui.setVisible(false);
                mmn.setVisible(true);

            }
                                //Pay later button
        } else if (e.getSource() == mui.getPayLaterButton()) {
            JOptionPane.showMessageDialog(null, "Reservation Made!");
            mui.setVisible(false);
            mmn.setVisible(true);
                                //Make reservation button
        } else if (e.getSource() == mmn.getMakeReserv()) {
            mmn.setVisible(false);
            reui.setVisible(true);
            reui.getMakeReservButton().setEnabled(false);
            
                                //Cancel reservation button
        } else if (e.getSource() == mmn.getCancelReserv()) {
            mmn.setVisible(false);
            cr.setVisible(true);

        } else if (e.getSource() == mmn.getCheckMyReservations()) {
            mmn.setVisible(true);
            ReservationSystem.getInstance().displayAllMyReservations(m.getCustomer().getUsername());

        } 
        else if(e.getSource() ==mmn.getDisconnect()){
            DisconnectedState ds = new DisconnectedState();
             Customer customer;
             ds.doAction(m.getCustomer());
             mmn.setVisible(false);
             cui.setVisible(true);
        }
        else if (e.getSource() == cr.getBack()) {
            cr.setVisible(false);
            mmn.setVisible(true);

        } else if (e.getSource() == cr.getCancel()) {
            boolean findReserv = false;
            boolean findRoom = false;

            int reservationNumber = Integer.parseInt(cr.getReservationNumber().getText());
            for (int i = 0; i < ReservationSystem.getInstance().getReservations().size(); i++) {
                if (reservationNumber == ReservationSystem.getInstance().getReservation(i).getUniqueReservationCode() && m.getCustomer().getUsername().equals(ReservationSystem.getInstance().getReservation(i).getUsername())) {
                    ReservationSystem.getInstance().deleteReservation(ReservationSystem.getInstance().getReservation(i));
                    //ReservationSystem.getInstance().displayAllReservations();
                    findReserv = true;
                    break;
                }
            }
            for (int indexHotel = 0; indexHotel < ReservationSystem.getInstance().getHotels().size(); indexHotel++) {
                for (Room r : ReservationSystem.getInstance().getHotels().get(indexHotel).getRooms()) {
                    for (int j = 0; j < r.getResInfo().size(); j++) {
                        if (reservationNumber == r.getResInfo().get(j).getUniqueReservationCode()) {
                            r.getResInfo().remove(j);
                            findRoom = true;
                            break;
                        }
                    }
                }
            }

            if (findReserv && findRoom) {
                dataManager.cancelReservation(cr.getReservationNumber().getText());
                dataManager.cancelServices(cr.getReservationNumber().getText());
                JOptionPane.showMessageDialog(null, "Reservation Cancelled with success.");

            } else {
                JOptionPane.showMessageDialog(null, "Sorry, we can not find your reservation or you are not the owner of this reservation!");
            }

        }
    }

}
