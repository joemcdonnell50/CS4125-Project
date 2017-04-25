package Services;

import java.util.ArrayList;

/**
 *
 * @author bprieto
 */
public class Room {
    //Factory method for Room 
    public static Room create(String type, int maxOccupancy, double pricePerNight) {
        return new Room(type, maxOccupancy, pricePerNight);
    }
    
    private String type;
    private int maxOccupancy;
    private double pricePerNight;
    private ArrayList<ReservationInfo> resInfo = new ArrayList<ReservationInfo>();

    private Room(String type, int maxOccupancy, double pricePerNight) {
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

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    
    
    
}
