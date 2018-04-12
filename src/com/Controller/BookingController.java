package com.Controller;

import com.Entity.Booking;
import com.Utils.*;
import vendor.json.JSONObject;

public class BookingController implements IBook {

    public boolean roomBooking(JSONObject data) {
        System.out.println("AAA mali ti bi doda "+data);
        return false;
    }

    public Booking[] viewBookings() {
        return new Booking[0];
    }

    public Booking viewSingleBooking(JSONObject data) { return new Booking(); }

    public boolean updateBooking(JSONObject data) {
        return false;
    }

    public boolean deleteBooking(JSONObject data) {
        return false;
    }
}
