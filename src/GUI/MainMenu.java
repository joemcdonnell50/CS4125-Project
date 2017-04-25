package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;


/**
 *
 * @author bprieto
 */
public class MainMenu extends javax.swing.JFrame {

    public JButton getDisconnect() {
        return Disconnect;
    }

    public void setDisconnect(JButton Disconnect) {
        this.Disconnect = Disconnect;
    }
   
    public JButton getCancelReserv() {
        return cancelReserv;
    }

    public void setCancelReserv(JButton cancelReserv) {
        this.cancelReserv = cancelReserv;
    }

    public JLabel getHelloCustomer() {
        return helloCustomer;
    }

    public void setHelloCustomer(JLabel helloCustomer) {
        this.helloCustomer = helloCustomer;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JButton getMakeReserv() {
        return makeReserv;
    }

    public void setMakeReserv(JButton makeReserv) {
        this.makeReserv = makeReserv;
    }

    public JButton getCheckMyReservations() {
        return CheckMyReservations;
    }

    public void setCheckMyReservations(JButton CheckMyReservations) {
        this.CheckMyReservations = CheckMyReservations;
    }

  

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        helloCustomer = new javax.swing.JLabel();
        makeReserv = new javax.swing.JButton();
        cancelReserv = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        CheckMyReservations = new javax.swing.JButton();
        Disconnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        helloCustomer.setText("Hello");

        makeReserv.setText("Make a reservation");

        cancelReserv.setText("Cancel a reservation");
        cancelReserv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelReservActionPerformed(evt);
            }
        });

        jLabel2.setText("What do you want to do ? ");

        CheckMyReservations.setText("Check My Reservations");

        Disconnect.setText("Disconnect");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(113, 113, 113))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(makeReserv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelReserv)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(helloCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Disconnect))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(CheckMyReservations)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(helloCustomer))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Disconnect)))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(makeReserv)
                    .addComponent(cancelReserv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckMyReservations)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelReservActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelReservActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelReservActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CheckMyReservations;
    private javax.swing.JButton Disconnect;
    private javax.swing.JButton cancelReserv;
    private javax.swing.JLabel helloCustomer;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton makeReserv;
    // End of variables declaration//GEN-END:variables
}
