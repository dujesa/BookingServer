package com.Controller;

import com.Repository.RoomRepository;
import com.Utils.IRoom;
import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.SQLException;

public class RoomController implements IRoom {

    RoomRepository roomRepo = new RoomRepository();

    @Override
    public JSONObject viewSingleRoom(JSONObject data) throws SQLException, ClassNotFoundException {

        return roomRepo.viewSingleRoom(data);

    }

    @Override
    public JSONArray viewAvailableRooms(JSONObject data) throws SQLException, ClassNotFoundException {
        return roomRepo.viewAvailableRooms(data);
    }
}
