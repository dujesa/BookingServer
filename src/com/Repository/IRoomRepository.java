package com.Repository;

import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IRoomRepository {

    JSONObject viewSingleRoom(JSONObject room);
    JSONObject viewAvailableRooms(JSONObject parameters) throws SQLException, ClassNotFoundException;

}
