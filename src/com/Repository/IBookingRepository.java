package com.Repository;

import com.Entity.Booking;
import com.google.gson.JsonObject;
import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IBookingRepository {

    /**
     * first required to check availability of rooms, then requests to make booking on  single available room
     */
    boolean bookRoom(JSONObject params);
    JSONArray viewBookings() throws SQLException, ClassNotFoundException;
    Booking viewSingleBooking(JsonObject booking);
    boolean updateBooking(JSONObject booking);
    boolean delete(JSONObject booking);

}
