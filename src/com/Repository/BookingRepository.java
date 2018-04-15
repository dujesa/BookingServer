package com.Repository;

import com.Utils.DBConnecter;
import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRepository implements IBookingRepository {

    JSONObject response;

    public boolean bookRoom(JSONObject booking) throws SQLException, ClassNotFoundException {

        String sql =
                "INSERT INTO bookings " +
                        "(id_user, room_number, event_name, ordered_by, attenders, reccuring, datetime_start, datetime_end) " +
                        "VALUES (?,?,?,?,?,?,?,?)"
                ;

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
                        " FROM bookings, room, users" +
                        " WHERE bookings.room_number = room.room_number " +
                        " AND bookings.id_user = users.id_user;"
                ;

        ResultSet resultRows = DBConnecter.searchDB(sql);

        if(resultRows.isBeforeFirst()) {
            while (resultRows.next()) {

                booking = new JSONObject();

                String recurring = (resultRows.getInt("reccuring")==0) ? "No" : "Yes";

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

        String recurring, fullName;
        response = new JSONObject();

        String sql =
                "SELECT room.room_number, event_name, attenders, reccuring, ordered_by, users.username, datetime_start, datetime_end, users.name, users.last_name, users.user_phone, users.mail, users.office_number " +
                        "FROM bookings, room, users " +
                        "WHERE bookings.id_booking = " + booking.getInt("idBooking") + " " +
                        "AND bookings.room_number = room.room_number " +
                        "AND bookings.id_user = users.id_user ;"
                ;
        ResultSet resultRows = DBConnecter.searchDB(sql);

        if(resultRows.isBeforeFirst()) {

            recurring = (resultRows.getInt("reccuring") == 0) ? "No" : "Yes";
            fullName = resultRows.getString("name") + " " + resultRows.getString("last_name");

            response.put("roomNumber", resultRows.getString("room_number"));
            response.put("eventName", resultRows.getString("event_name"));
            response.put("attendees", resultRows.getString("attenders"));
            response.put("orderedBy", resultRows.getString("ordered_by"));
            response.put("username", resultRows.getString("username"));
            response.put("recurring", recurring);
            response.put("dateAndTimeStart", resultRows.getString("datetime_start"));
            response.put("dateAndTimeEnd", resultRows.getString("datetime_end"));
            response.put("fullName", fullName);
            response.put("phoneNumber", resultRows.getString("user_phone"));
            response.put("mail", resultRows.getString("mail"));
            response.put("officeNumber", resultRows.getString("office_number"));

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
