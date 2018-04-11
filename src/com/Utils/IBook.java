package com.Utils;


import com.Entity.Booking;

public interface IBook {
     boolean roomBooking();
     Booking[] viewBookings();
     Booking viewSingleBooking();
     boolean updateBooking();
     boolean deleteBooking();
}
