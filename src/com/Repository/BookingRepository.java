package com.Repository;

import com.Utils.DBConnecter;
import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BookingRepository implements IBookingRepository {

    JSONObject response;

    public boolean bookRoom(JSONObject params) throws SQLException, ClassNotFoundException {

        String sql =
                "INSERT INTO bookings " +
                        "(users.id_user,room.room_number,event_name,ordered_by,name,last_name,mail,attenders,reccuring,datetime_start,datetime_end) " +
                        "VALUES (?,?,?,?,?,?,?,?,?)"
                ;
        JSONObject booking=null;

        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);


        preparedStatement.setInt(1, booking.getInt("users.id_user"));
        preparedStatement.setString(2, booking.getString("room.room_number"));
        preparedStatement.setString(3, booking.getString("event_name"));
        preparedStatement.setString(4, booking.getString("attenders"));
        preparedStatement.setString(5, booking.getString("ordered_by"));
        preparedStatement.setString(6, booking.getString("datetime_start"));
        preparedStatement.setString(7, booking.getString("datetime_end"));
        preparedStatement.setString(8, booking.getString("mail"));
        preparedStatement.setInt(8, booking.getInt("reccuring"));

        boolean returnValue = (preparedStatement.executeUpdate() == 0) ? false : true;

        return returnValue;

    };
    public JSONArray viewBookings() throws SQLException, ClassNotFoundException {

        JSONArray response = new JSONArray();
        JSONObject booking;

        String sql =
                "SELECT id_booking, users.username, event_name, room.room_number, attenders, room.capacity, ordered_by, room.phone, datetime_start, datetime_end, reccuring" +
                        " FROM bookings,room,users" +
                        " WHERE bookings.room_number = room.room_number " +
                        " AND bookings.id_user = users.id_user;"
                ;
        ResultSet resultRows = DBConnecter.searchDB(sql);

        if(resultRows.isBeforeFirst()) {
            while (resultRows.next()) {

                booking = new JSONObject();

                booking.put("idBooking", resultRows.getString("id_booking"));
                booking.put("username", resultRows.getString("username"));
                booking.put("eventName", resultRows.getString("event_name"));
//                booking.put("officeNumber", resultRows.getString("office_number"));
                booking.put("roomNumber", resultRows.getString("room_number"));
//                booking.put("capacity", resultRows.getString("capacity"));
                booking.put("orderedBy", resultRows.getString("ordered_by"));
//                booking.put("phoneNumber", resultRows.getString("phone"));
                booking.put("dateAndTimeStart", resultRows.getString("datetime_start"));
                booking.put("dateAndTimeEnd", resultRows.getString("datetime_end"));
                booking.put("recurring", resultRows.getString("reccuring"));

                response.put(booking);

            }
        } else {

            booking = new JSONObject();

            booking.put("username", "404 - NotFound");
            response.put(booking);

        }

        return response;
    }



    public JSONObject viewSingleBooking(JSONObject booking) throws SQLException, ClassNotFoundException {

        String sql =
                "SELECT bookings.id_booking, room.room_number, event_name, attenders, reccuring, datetime_start, datetime_end, name, last_name, user_phone, mail  FROM bookings, room, users" +
                        "WHERE bookings.room_number = room.room_number" +
                        "AND bookings.id_user = users.id_user" +
                        "AND bookings.event_name = '" + booking.getInt("idBooking") + "';"
                ;
        ResultSet resultRows = DBConnecter.searchDB(sql);

        if(resultRows.isBeforeFirst()) {

            LocalDateTime startDate = LocalDateTime.parse(
                    resultRows.getString("datetime_start")
            );

            LocalDateTime endDate = LocalDateTime.parse(
                    resultRows.getString("datetime_end")
            );

//            Period duration = Period.between(startDate, endDate);
//            Date


            response.put("idBooking", resultRows.getString("id_booking"));
            response.put("roomNumber", resultRows.getString("room_number"));
            response.put("eventName", resultRows.getString("event_name"));
            response.put("attenders", resultRows.getString("attenders"));
            response.put("reccuring", resultRows.getString("recurring"));
            response.put("datetime_start", resultRows.getString("datetime_start"));
            //response.put("", resultRows.getString(""));
            response.put("name", resultRows.getString("name"));
            response.put("last_name", resultRows.getString("firstName"));
            response.put("user_phone", resultRows.getString("userPhone"));
            response.put("mail", resultRows.getString("email"));
        } else {

            response.put("booking", "404 - NotFound");

        }

        return response;

    }
    public boolean updateBooking(JSONObject booking){return false;}

    public boolean deleteBooking(JSONObject booking) throws SQLException, ClassNotFoundException {

        String sql =
                "DELETE FROM bookings " +
                        "WHERE id_booking = ?";

        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);
        preparedStatement.setString(1, booking.getString("idBooking"));

        boolean returnValue = (preparedStatement.executeUpdate() == 0) ? false : true;
        return returnValue;
    }

}
