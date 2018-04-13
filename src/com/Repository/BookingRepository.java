package com.Repository;

import com.Entity.Booking;
import com.Utils.DBConnecter;
import com.google.gson.JsonObject;
import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRepository implements IBookingRepository {

    JSONObject response;

    public boolean bookRoom(JSONObject params){return false;};
    public JSONArray viewBookings() throws SQLException, ClassNotFoundException {

        JSONArray response = new JSONArray();
        JSONObject booking;

        String sql =
                "Select id_booking,users.username,event_name,room.room_number,attenders,room.capacity,ordered_by, room.phone,datetime_start,datetime_end,reccuring\n" +
                        " FROM bookings,room,users" +
                        " WHERE bookings.room_number=room.room_number " +
                        " AND bookings.id_user=users.id_user;"
                ;
        ResultSet resultRows = DBConnecter.searchDB(sql);

        if(resultRows.isBeforeFirst()) {
            while (resultRows.next()) {

                booking = new JSONObject();

                booking.put("idBooking", resultRows.getString("id_booking"));
                booking.put("username", resultRows.getString("users.username"));
                booking.put("eventName", resultRows.getString("event_name"));
                booking.put("officeNumber", resultRows.getString("office_number"));
                booking.put("roomNumber", resultRows.getString("room_number"));
                booking.put("capacity", resultRows.getString("capacity"));
                booking.put("orderedBy", resultRows.getString("ordered_by"));
                booking.put("phoneNumber", resultRows.getString("phone"));
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
    public Booking viewSingleBooking(JsonObject booking){return new Booking();}
    public boolean updateBooking(JSONObject booking){return false;}
    public boolean delete(JSONObject booking){return false;}

}
