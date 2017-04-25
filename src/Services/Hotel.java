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
public class Hotel implements RoomDAOImpl {
    //Factory method for Hotel
    public static Hotel create(String location, Service service) {
        return new Hotel(location, service);
    }
    
    private String location;
    private ArrayList <Room> rooms=new ArrayList<Room>();
    Service service;

    private Hotel(String location, Service service) {
        this.location = location;
        this.service = service;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public Room getRoom(int index) {
        return this.rooms.get(index);
    }

    @Override
    public void deleteRoom(Room room) {
        this.rooms.remove(room);
       }
    

    


}
