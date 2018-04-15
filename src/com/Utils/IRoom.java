package com.Utils;

import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IRoom {
     JSONObject viewSingleRoom(JSONObject params) throws SQLException, ClassNotFoundException;
     JSONArray viewAvailableRooms(JSONObject params) throws SQLException, ClassNotFoundException;
}
