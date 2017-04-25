/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class CreditCardPay {
    //Factory method one for CreditCardPay
    public static CreditCardPay create() {
        return new CreditCardPay();
    }
    //Factory method two for CreditCardPay
    public static CreditCardPay create(String NameOnCard, String expiryDate, String creditCardNum, String CVNum) {
        return new CreditCardPay(NameOnCard, expiryDate, creditCardNum, CVNum);
    }


        private String NameOnCard;
	private String creditCardNum;
        private String expiryDate;
        private String CVNum;
	
	public CreditCardPay(){
		this.NameOnCard="No card name";
                this.creditCardNum="No Card Number";
		this.expiryDate="No expire date";
                this.CVNum="No cv number";
	}


	private CreditCardPay(String NameOnCard, String expiryDate, String creditCardNum,String CVNum){
		this.NameOnCard=NameOnCard;
		this.expiryDate=expiryDate;
		this.creditCardNum=creditCardNum;
                this.CVNum=CVNum;
	}

	public void setcardName(String NameOnCard){
		this.NameOnCard=NameOnCard;
	}

	public void setexpireDate(String expiryDate){
		this.expiryDate=expiryDate;
	}

	public void setcreditCardNum(String creditCardNum){
		this.creditCardNum=creditCardNum;
	}
        
        public void setCVNum(String CVNum){
		this.CVNum=CVNum;
	}
        public String getCardName(){
		return NameOnCard;	
	}
      
         public String encryptCard(String StringtoEncrpyt){
            try {
             byte[] EncodedString = StringtoEncrpyt.getBytes("UTF-8");
             String encrypt = DatatypeConverter.printBase64Binary(EncodedString);
             return encrypt;
            } 
            
            catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CreditCardPay.class.getName()).log(Level.SEVERE, null, ex);
            }
         return "Not encrypted";
}
       
          public String decryptCard(String StringtoDecrpyt){  
            try {
                byte[] decrypt = DatatypeConverter.parseBase64Binary(StringtoDecrpyt);
                String decrypted = new String(decrypt, "UTF-8");
                return decrypted;
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CreditCardPay.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Not decrypted";
        }
        //Checks if credit card supplied has a legitimate card number
        public boolean isCardLegit(){
            int lastDidgit = 0,modofnum = 0, totalOfNumbers =0, tempDidgit = 0;
            int length = creditCardNum.length();
            //split string into int array in reverse order
            int [] CardNum = new int [length];
            for(int i=0;i<length;i++) {
                 CardNum[(length-1)-i ] = Integer.parseInt(String.valueOf(creditCardNum.charAt(i)));
              }
            //get the last didgit of cardnumber
             lastDidgit=CardNum[0];
             //get every odd number element in array * 2, if greater then 9 - 9 from it
          for(int j = 1;j<length-1;j+=2){
             tempDidgit = CardNum[j];
             tempDidgit = tempDidgit *2;
                if(tempDidgit > 10){
                tempDidgit = tempDidgit - 9;
             }
                totalOfNumbers += tempDidgit;
          }
          //modules by 10 total numbers and check if eqauls last number
             modofnum = totalOfNumbers % 10;
          if(modofnum != lastDidgit){
              return false;
              
          }
          else{
        return true;
        
        }
    }
}
