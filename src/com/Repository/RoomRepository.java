package com.Repository;

import com.Entity.Room;
import vendor.json.JSONObject;

import java.util.ArrayList;

public class RoomRepository implements IRoomRepository {

    ArrayList<Room> rooms;

    @Override
    public Room viewSingleRoom(JSONObject room) {

//        String sql =
//                "SELECT datetime_start,datetime_end,room_number,type,phone,capacity" +
//                        " FROM room" +
//                        " LEFT JOIN bookings ON room.room_number = bookings.room_number"
//                        " WHERE (" +
//                                "datetime_start IS NULL OR '" + room.getString("")
//                                = '" + room.getString("username") + "'"+
//                        " AND password = '" + room.getString("password") + "';"
//                ;
//        "SELECT datetime_start,datetime_end,room_number,type,phone,capacity"
//        "From room"
//        "Left join bookings on room.room_number=bookings.room_number"
//        "where (datetime_start is NULL or  '2016-01-28 15:30' not between datetime_start and datetime_end) and capacity>=100;""

        return null;
    }

    @Override
    public ArrayList<Room> viewAvailableRooms() {
        return null;
    }
}
