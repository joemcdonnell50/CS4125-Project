/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Liam
 */
public class ServiceComposite implements ServiceComponent {
    
    String name;
    List<ServiceComponent> serviceComponents = new ArrayList<>();

    public ServiceComposite(String name) {
        super();
        this.name = name;
    }
    
    public void addComponent(ServiceComponent com){
        serviceComponents.add(com);
    }
    
    @Override
    public String showName(){
        System.out.println(name);
        for(ServiceComponent c : serviceComponents){
            c.showName();
        }
        return name;
    }
    
    @Override
    public int calculatePrice(){
        int price = 0;
        for(ServiceComponent c : serviceComponents){
            price = price + c.calculatePrice();
        }
        return price;
    }
    
}
