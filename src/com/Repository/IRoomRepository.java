package com.Repository;

import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IRoomRepository {

    JSONObject viewSingleRoom(JSONObject room) throws SQLException, ClassNotFoundException;
    JSONArray viewAvailableRooms(JSONObject parameters) throws SQLException, ClassNotFoundException;

}
