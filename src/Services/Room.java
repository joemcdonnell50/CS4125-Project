/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.ArrayList;

/**
 *
 * @author bprieto
 */
public class Room {
    
    private String type;
    private int maxOccupancy;
    private boolean isCleaned=true;
    private double pricePerNight;
    private ArrayList<ReservationInfo> resInfo = new ArrayList<ReservationInfo>();

    public Room(String type, int maxOccupancy, double pricePerNight) {
        this.type = type;
        this.maxOccupancy = maxOccupancy;
        this.pricePerNight = pricePerNight;
    }

    public ArrayList<ReservationInfo> getResInfo() {
        return resInfo;
    }

    public void setResInfo(ArrayList<ReservationInfo> resInfo) {
        this.resInfo = resInfo;
    }
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public boolean isIsCleaned() {
        return isCleaned;
    }

    public void setIsCleaned(boolean isCleaned) {
        this.isCleaned = isCleaned;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    
    
    
}
