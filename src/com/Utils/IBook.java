package com.Utils;


import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IBook {
     boolean roomBooking(JSONObject booking) throws SQLException, ClassNotFoundException;
     JSONArray viewBookings() throws SQLException, ClassNotFoundException;
     JSONObject viewSingleBooking(JSONObject booking) throws SQLException, ClassNotFoundException;
     boolean updateBooking(JSONObject booking) throws SQLException, ClassNotFoundException;
     boolean deleteBooking(JSONObject booking) throws SQLException, ClassNotFoundException;
}
