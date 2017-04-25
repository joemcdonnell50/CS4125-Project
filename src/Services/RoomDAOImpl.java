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
public interface RoomDAOImpl {
    
public ArrayList<Room> getRooms();
public Room getRoom(int index);
public void deleteRoom(Room room);
    
}
