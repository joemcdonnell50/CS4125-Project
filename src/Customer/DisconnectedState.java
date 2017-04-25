/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import javax.swing.JOptionPane;

/**
 *
 * @author bprieto
 */
public class DisconnectedState implements State {

    @Override
    public void doAction(Customer customer) {
    JOptionPane.showMessageDialog(null, "Happy to see you "+ customer.getUsername() + "! See you bro!");
        customer.setState(this);    
    }
    
}
