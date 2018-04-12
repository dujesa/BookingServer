package com.Utils;


import com.Entity.Booking;
import vendor.json.JSONObject;

public interface IBook {
     boolean roomBooking(JSONObject booking);
     Booking[] viewBookings();
     Booking viewSingleBooking(JSONObject booking);
     boolean updateBooking(JSONObject booking);
     boolean deleteBooking(JSONObject booking);
}
