
package Payment;

import java.text.DecimalFormat;

/**
 *
 * @author Owner
 */
public class discountReceiptDecorator extends receiptDecorator {
    private double OriginalCst;
    private String DiscountedReceipt;

    public discountReceiptDecorator(Receipt decoratedReceipt) {
        super(decoratedReceipt);
    }

    @Override
    public String FormatReceipt() {
        decoratedReceipt.FormatReceipt();
        return DiscountedReceipt = setReceiptFormat(decoratedReceipt);
    }
    public void setOriginalCst(double OriginalCst){
    this.OriginalCst = OriginalCst;
   }
    private String setReceiptFormat(Receipt decoratedReceipt) {
        double OriginalCost,savings;
        String origninalCostS,SavingsS;
        DecimalFormat df = new DecimalFormat("#.00"); 
        OriginalCost = OriginalCst;
        savings = OriginalCost - decoratedReceipt.getTotal();
        origninalCostS=df.format(OriginalCost);
        SavingsS=df.format(savings);
        String Receipt = String.format("Hello " + decoratedReceipt.getUsername() + "\n" +
                               "Receipt for reservation number " + decoratedReceipt.getUniqueNumber() +"\n" 
                               + "Hotel : " +"\t"+ decoratedReceipt.getHotel() + "\n" + 
                                "Roomtype : "+"\t"+ decoratedReceipt.getRoomType() + "\n" +
                                "Number of Guests : "+"\t" + decoratedReceipt.getNbOfGuests() + "\n" +
                                "Date of Arrival : " +"\t"+ decoratedReceipt.getDateArrival() + "\n" +
                                "Number of Nights : " +"\t"+ decoratedReceipt.getNbOfNights() + "\n" +  
                                "Your original cost was "+"\t" + origninalCostS   + "\n" + 
                                "Cost of Services :" + "\t" + decoratedReceipt.getServicePrice() + "\n" +
                                "Your discounted price is :" + "\t" + decoratedReceipt.getTotal() + "\n" +
                                "You save " + SavingsS + " euro!"
                    );
            
        return Receipt;
    }
}
