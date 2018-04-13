package com.Repository;

import com.Entity.Room;
import vendor.json.JSONObject;

import java.util.ArrayList;

public interface IRoomRepository {

    Room viewSingleRoom(JSONObject room);
    ArrayList<Room> viewAvailableRooms();

}
