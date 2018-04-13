package com.Repository;

import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IBookingRepository {

    /**
     * first required to check availability of rooms, then requests to make booking on  single available room
     */
    boolean bookRoom(JSONObject params) throws SQLException, ClassNotFoundException;
    JSONArray viewBookings() throws SQLException, ClassNotFoundException;
    JSONObject viewSingleBooking(JSONObject booking) throws SQLException, ClassNotFoundException;
    boolean updateBooking(JSONObject booking);
    boolean deleteBooking(JSONObject booking) throws SQLException, ClassNotFoundException;

}
