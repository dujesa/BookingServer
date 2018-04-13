package com.Repository;

import com.Entity.Room;
import com.Utils.DBConnecter;
import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RoomRepository implements IRoomRepository {

    ArrayList<Room> rooms;
    JSONArray response;

    @Override
    public Room viewSingleRoom(JSONObject room) {



        return null;
    }

    @Override

        public JSONArray viewAvailableRooms(JSONObject parameters) throws SQLException, ClassNotFoundException {
            JSONObject room;

            LocalDateTime startDate = LocalDateTime.parse(
                    parameters.getString("dateAndTime")
            );
            LocalDateTime endDate = startDate.plusHours(
                    parameters.getInt("duration")
            );

            String sql =
                    "SELECT room_number, type, phone, capacity" +
                            " FROM room" +
                            " LEFT JOIN bookings on room.room_number=bookings.room_number" +
                            " WHERE datetime_start is NULL" +
                            " OR (('" + startDate + "' < datetime_start" +
                            " AND '" + endDate + "'<datetime_start)" +
                            " OR ('" + startDate + "'>datetime_end " +
                            " AND '" + endDate + "'>datetime_end));"
                    ;
            ResultSet resultRows = DBConnecter.searchDB(sql);

            if(resultRows.isBeforeFirst()) {
                while (resultRows.next()) {

                    room = new JSONObject();

                    room.put("dateAndTime", resultRows.getString("date_time"));
                    room.put("username", resultRows.getString("datetime_end"));
                    room.put("eventName", resultRows.getString("room_number"));
                    room.put("officeNumber", resultRows.getString("type"));
                    room.put("roomNumber", resultRows.getString("phone"));
                    room.put("capacity", resultRows.getString("capacity"));

                    response.put(room);


                }
            } else {

                room = new JSONObject();

                room.put("room", "404 - NotFound");
                response.put(room);

            }

            return response;
        }
    }


