
import DatabaseManager.CustomerManager;
import DatabaseManager.DatabaseManager;
import GUI.CustomerRegisterUI;
import GUI.CustomerUI;
import GUI.ReservationEditorUI;
import GUI.MakePaymentUI;
import Reservation.Reservation;
import Reservation.ReservationSystem;
import Services.Hotel;
import Services.Room;
import Services.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void callCUI(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustomerUI cui = new CustomerUI();
                CustomerRegisterUI crui=new CustomerRegisterUI();
                ReservationEditorUI reui = new ReservationEditorUI();
                MakePaymentUI mui = new MakePaymentUI();
                CustomerManager mg=new CustomerManager();
                mg.initalizeHotelRoom(ReservationSystem.getInstance());
                mg.initializeReservations(ReservationSystem.getInstance());
                ReservationSystem.getInstance().displayAllReservations();
                ReservationSystem.getInstance().displayAllRooms();
                
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(cui);
                frame.pack();
                frame.setVisible(true);
               
                CustomerModel model= new CustomerModel();
                Controller c= new Controller(model,cui,crui,reui, mui);
                
                cui.getLoginButton().addActionListener(c);
                cui.getRegisterButton().addActionListener(c);
                crui.getLoginButton().addActionListener(c);
                crui.getBackButton().addActionListener(c);
                reui.getMakeReservButton().addActionListener(c);
                reui.getCheckAvailButton().addActionListener(c);
                mui.getPayNowButton().addActionListener(c);
                
                
               
            }
        });
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        callCUI();
        /*int choice= 0;
        Scanner in = new Scanner(System.in);
       ArrayList <Room> rooms=new ArrayList<>();
       Service service= new Service();
        
        ReservationSystem rs= new ReservationSystem();
        Hotel hotel;
        hotel = new Hotel("first hotel",5,"Castletroy",rooms,service);
        Room room1=new Room("Luxury room",5,90);
        Room room2=new Room("Luxury room",5,90);
        Room room3=new Room("Poor Room",1,10);
        Room room4=new Room("Poor room",1,10);
        
        
        
        // add an hotel
        rs.getHotels().add(hotel);
        
        //add 4 rooms
        rs.getHotels().get(0).getRooms().add(room1);
        rs.getHotels().get(0).getRooms().add(room2);
        rs.getHotels().get(0).getRooms().add(room3);
        rs.getHotels().get(0).getRooms().add(room4);
        
        // set hammam at true in our hotel
        rs.getHotels().get(0).getService().setIsHammam(true);
        */
       /* for(Hotel h : rs.getHotels()){
            for(Room r : h.getRooms()){
                System.out.println("Type : "+ r.getType());
                System.out.println ("Price  : "+r.getPricePerNight() );
            }
        }*/
       /* while(choice!=4){
            System.out.println("Hello and Welcome to Innocent Hotel's Reservation System.");
      System.out.println("Would you like to");
      System.out.println("  1. Make a reservation");
      System.out.println("  2. Delete a reservation");
      System.out.println("  3. View upcoming reservations");
      System.out.println("  4. Exit");
      System.out.print("Option: ");
      choice = in.nextInt();
            
      System.out.println(); //Empty line just for organization
      
      switch (choice) {
        case 1:
          System.out.println("What date will you arrive? format : dd/mm/yyyy");
          String InptDate = in.next();
          int index= 0;
           System.out.println("What is the room type? ");
          for(Room r : rs.getHotels().get(0).getRooms()){
              if(r.getUniqueReservationCode()==0){
              System.out.println(index+ ") "+r.getType() );
              }
               index++;
          }
          int type= in.nextInt();
          String roomType = rs.getHotels().get(0).getRooms().get(type).getType();
          Room theRoom = rs.getHotels().get(0).getRooms().get(type);

          System.out.println("How long will the stay be in nights? ");
          int InptNightStay = in.nextInt();
		  //Room rom = new Room(InptRMType); 
          System.out.println("What is your name for the reservation?");
          String name=in.nextLine();
           name=in.nextLine();
          
          double totalCost=theRoom.getPricePerNight()*InptNightStay;
          rs.makeReservation(name,InptDate,InptNightStay,totalCost,theRoom);
          break;

        case 2:
          System.out.println("What date does the reservation start? ");
          System.out.print("Enter date as MM/DD/YY : ");
          String QueryDate = in.next();
          for(int i = 0; i < rs.getReservations().size(); i++) {
            if(rs.getReservations().get(i).getCheckInDate().equals(QueryDate)) {
              rs.getReservations().remove(i);
              System.out.println("All reservations are deleted at this date");
              break;
            }
          }
          break;
        case 3:
          for (Reservation aResrv : rs.getReservations()) {
            if (aResrv != null) { //Prevents NullPointerException
              System.out.println("On " + aResrv.getCheckInDate() + " you have one reservation");
            }
          }
          break;
        case 4:
          System.out.println("Program is exiting");
          break;
        case 5:
            for(Room r : rs.getHotels().get(0).getRooms()){
                System.out.println(r.getUniqueReservationCode());
            }
            break;
        default:
          System.out.println("Please make a valid numerical option.");
          break;
      }
        }*/

    }
    
}
