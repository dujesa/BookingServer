package com.Controller;

import com.Entity.Booking;
import com.Utils.*;
public class BookingController implements IBook {

    @Override
    public boolean roomBooking() {
        return false;
    }

    @Override
    public Booking[] viewBookings() {
        return new Booking[0];
    }

    @Override
    public Booking viewSingleBooking() {
        return null;
    }

    @Override
    public boolean updateBooking() {
        return false;
    }

    @Override
    public boolean deleteBooking() {
        return false;
    }
}
