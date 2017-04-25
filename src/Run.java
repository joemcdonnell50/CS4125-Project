import DatabaseManager.FileManager;
import GUI.CancelReservation;
import GUI.CustomerRegisterUI;
import GUI.CustomerUI;
import GUI.MainMenu;
import GUI.MakePaymentUI;
import GUI.ReservationEditorUI;

import GUI.ServicesUI;

import Reservation.ReservationSystem;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author bprieto
 */
public class Run {
    public static void callCUI(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustomerUI cui = new CustomerUI();
                CustomerRegisterUI crui=new CustomerRegisterUI();
                ReservationEditorUI reui = new ReservationEditorUI();
                MakePaymentUI mui = new MakePaymentUI();
                MainMenu mainM = new MainMenu();
                
                ServicesUI sui = new ServicesUI();
                
                CancelReservation cr= new CancelReservation();
                FileManager mg=FileManager.create();
                mg.initalizeHotelRoom(ReservationSystem.getInstance());
                mg.initializeReservations(ReservationSystem.getInstance());
                //ReservationSystem.getInstance().displayAllReservations();
               
                //ReservationSystem.getInstance().displayAllRooms();
                
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(cui);
                frame.pack();
                frame.setVisible(true);
                
               //cui.getUsernameTextField().setText("coco");
               // cui.getPasswordField().setText("coco");
               
                CustomerModel model= new CustomerModel();
                Controller c = Controller.create(cr,mainM,model,cui,crui,reui, mui, sui);
                
                cui.getLoginButton().addActionListener(c);
                cui.getRegisterButton().addActionListener(c);
                crui.getLoginButton().addActionListener(c);
                crui.getBackButton().addActionListener(c);
                reui.getMakeReservButton().addActionListener(c);
                reui.getCheckAvailButton().addActionListener(c);
                
                sui.getAddServicesButton().addActionListener(c);
                sui.getBackButton().addActionListener(c);
                
                mui.getPayNowButton().addActionListener(c);
                mui.getPayLaterButton().addActionListener(c);
                mainM.getMakeReserv().addActionListener(c);
                mainM.getCancelReserv().addActionListener(c);
                cr.getBack().addActionListener(c);
                cr.getCancel().addActionListener(c);
                mainM.getCheckMyReservations().addActionListener(c);
                mainM.getDisconnect().addActionListener(c);
            }
        });
    }
    
}