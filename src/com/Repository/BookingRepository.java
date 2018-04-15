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

    public boolean bookRoom(JSONObject booking) throws SQLException, ClassNotFoundException {

        String sql =
                "INSERT INTO bookings " +
                        "(id_user, room_number, event_name, ordered_by, attenders, reccuring, datetime_start, datetime_end) " +
                        "VALUES (?,?,?,?,?,?,?,?)"
                ;

        System.out.println(booking.toString());

        int recurring = (booking.getString("recurring").equalsIgnoreCase("no")) ? 0 : 1;

        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);


        preparedStatement.setString(1, booking.getString("userID"));
        preparedStatement.setString(2, booking.getString("roomNumber"));
        preparedStatement.setString(3, booking.getString("eventName"));
        preparedStatement.setString(4, booking.getString("orderedBy"));
        preparedStatement.setString(5, booking.getString("numberAtt"));
        preparedStatement.setInt(6, recurring);
        preparedStatement.setString(7, booking.getString("dateAndTimeStart"));
        preparedStatement.setString(8, booking.getString("dateAndTimeEnd"));

        boolean returnValue = (preparedStatement.executeUpdate() == 0) ? false : true;

        return returnValue;

    };
    public JSONArray viewBookings() throws SQLException, ClassNotFoundException {

        JSONArray response = new JSONArray();
        JSONObject booking;
        int index = 0;

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

                String recurring = (resultRows.getInt("reccuring")==0) ? "Yes" : "No";

                booking.put("idBooking", resultRows.getString("id_booking"));
                booking.put("username", resultRows.getString("username"));
                booking.put("attendees", resultRows.getString("attenders"));
                booking.put("eventName", resultRows.getString("event_name"));
                booking.put("roomNumber", resultRows.getString("room_number"));
                booking.put("orderedBy", resultRows.getString("ordered_by"));
                booking.put("dateAndTimeStart", resultRows.getString("datetime_start"));
                booking.put("dateAndTimeEnd", resultRows.getString("datetime_end"));
                booking.put("recurring", recurring);

                response.put(index++, booking);

            }
        } else {

            booking = new JSONObject();

            booking.put("idBooking", "404 - NotFound");
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




            response.put("idBooking", resultRows.getString("id_booking"));
            response.put("roomNumber", resultRows.getString("room_number"));
            response.put("eventName", resultRows.getString("event_name"));
            response.put("attendees", resultRows.getString("attenders"));
            response.put("recurring", resultRows.getString("reccuring"));
            response.put("datetime_start", resultRows.getString("datetime_start"));
            //response.put("", resultRows.getString(""));
            response.put("name", resultRows.getString("name"));
            response.put("last_name", resultRows.getString("firstName"));
            response.put("user_phone", resultRows.getString("userPhone"));
            response.put("mail", resultRows.getString("email"));
        } else {

            response.put("Booking", "404 - NotFound");

        }

        return response;

    }
    public boolean updateBooking(JSONObject booking) throws SQLException, ClassNotFoundException {
        String sql =
                " UPDATE bookings " +
                    "SET room_number = ?, event_name = ?, ordered_by = ?, datetime_start = ?, datetime_end = ?, reccuring = ?, attenders = ? " +
                    "WHERE id_booking = ?"
                ;

        int recurring = (booking.getString("recurring").equalsIgnoreCase("no")) ? 0 : 1;

        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);

        preparedStatement.setString(1, booking.getString("roomNumber"));
        preparedStatement.setString(2, booking.getString("eventName"));
        preparedStatement.setString(3, booking.getString("orderedBy"));
        preparedStatement.setString(4, booking.getString("dateAndTimeStart"));
        preparedStatement.setString(5, booking.getString("dateAndTimeEnd"));
        preparedStatement.setInt(6, recurring);
        preparedStatement.setString(7, booking.getString("attendees"));
        preparedStatement.setString(8, booking.getString("idBooking"));

        boolean returnValue = (preparedStatement.executeUpdate() == 0) ? false : true;
        return returnValue;
    }

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
