
import Customer.Customer;


/**
 *
 * @author bprieto
 */
public class CustomerModel {
    
    private Customer customer= new Customer("salut","salut");

    private int uniqueReservationNumber;

    public int getUniqueReservationNumber() {
        return uniqueReservationNumber;
    }

    public void setUniqueReservationNumber(int uniqueReservationNumber) {
        this.uniqueReservationNumber = uniqueReservationNumber;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    

}
