package com.Repository;

import com.Entity.Room;
import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IRoomRepository {

    Room viewSingleRoom(JSONObject room);
    JSONArray viewAvailableRooms(JSONObject parameters) throws SQLException, ClassNotFoundException;

}
