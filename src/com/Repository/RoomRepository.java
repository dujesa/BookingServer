package com.Repository;

import com.Entity.Room;
import com.Utils.DBConnecter;
import vendor.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomRepository implements IRoomRepository {

    ArrayList<Room> rooms;
    JSONObject response = new JSONObject();
//    JSONArray response = new JSONArray();

    @Override
    public JSONObject viewSingleRoom(JSONObject room) {



        return null;
    }

    @Override

    public JSONObject viewAvailableRooms(JSONObject parameters) throws SQLException, ClassNotFoundException {
            JSONObject room;

//            LocalDateTime startDate = LocalDateTime.parse(
//                    parameters.getString("dateAndTime")
//            );
//            LocalDateTime endDate = startDate.plusHours(
//                    parameters.getInt("duration")
//            );


        System.out.println(parameters.getString("dateAndTimeStart") );
        System.out.println(parameters.getString("dateAndTimeEnd") );

            String sql =
                    "SELECT room.room_number" +
                            " FROM room" +
                            " LEFT JOIN bookings on room.room_number=bookings.room_number" +
                            " WHERE datetime_start is NULL" +
                            " OR (('" + parameters.getString("dateAndTimeStart") + "' < datetime_start" +
                            " AND '" + parameters.getString("dateAndTimeEnd") + "' < datetime_start)" +
                            " OR ('" + parameters.getString("dateAndTimeStart")  + "' > datetime_end " +
                            " AND '" + parameters.getString("dateAndTimeEnd")  + "' > datetime_end));"
                    ;
            ResultSet resultRows = DBConnecter.searchDB(sql);

            if(resultRows.isBeforeFirst()) {
                while (resultRows.next()) {

                    room = new JSONObject();

                    room.put("roomNumber", resultRows.getString("room_number"));

                    response.put("room",room);


                }
            } else {

                room = new JSONObject();

                response.put("room", "404 - NotFound");
//                response.put(room);

            }

            return response;
        }
    }


