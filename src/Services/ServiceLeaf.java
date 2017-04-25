/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Liam
 */
public class ServiceLeaf implements ServiceComponent {
    
    int price;
    String name;

    public ServiceLeaf(int price, String name) {
        super();
        this.price = price;
        this.name = name;
    }
    
    
    @Override
    public String showName(){
        return name;
    }
    
    @Override
    public int calculatePrice(){
        return price;
    }
    
}
