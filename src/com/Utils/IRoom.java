package com.Utils;

import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IRoom {
     JSONObject viewSingleRoom(JSONObject params);
     JSONObject viewAvailableRooms(JSONObject params) throws SQLException, ClassNotFoundException;
}
