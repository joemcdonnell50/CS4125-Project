package Customer;

/**
 *
 * @author bprieto
 */
public class Customer {
    //Factory method for Customer
    public static Customer create(String username, String password) {
        return new Customer(username, password);
    }
    private String username;
    private String password;
    private State state;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setState(State state){
      this.state = state;		
   }
    public State getState(){
      return state;
   }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
