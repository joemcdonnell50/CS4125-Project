/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

/**
 *
 * @author Owner
 */
public abstract class receiptDecorator implements ReceiptInterface{
    protected Receipt decoratedReceipt;

   public receiptDecorator(Receipt decoratedReceipt){
      this.decoratedReceipt = decoratedReceipt;
   }

   public String FormatReceipt(){
       String newReceipt;
       newReceipt= decoratedReceipt.FormatReceipt();
      return newReceipt;
   }	
}
