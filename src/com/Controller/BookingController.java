package com.Controller;

import com.Repository.BookingRepository;
import com.Utils.*;
import vendor.json.JSONArray;
import vendor.json.JSONObject;

import java.sql.SQLException;

/**
 * Kontroler klasa vezana za sva usmjeravanja prema BookingRepositoryju
 */
public class BookingController implements IBook {

    BookingRepository bookingRepo = new BookingRepository();

    public boolean roomBooking(JSONObject data) throws SQLException, ClassNotFoundException {
        return bookingRepo.bookRoom(data);
    }

    public JSONArray viewBookings() throws SQLException, ClassNotFoundException {
        return bookingRepo.viewBookings();
    }

    public JSONObject viewSingleBooking(JSONObject data) throws SQLException, ClassNotFoundException {
        return bookingRepo.viewSingleBooking(data);
    }

    public boolean updateBooking(JSONObject data) throws SQLException, ClassNotFoundException {
        return bookingRepo.updateBooking(data);
    }

    public boolean deleteBooking(JSONObject data) throws SQLException, ClassNotFoundException {
        return bookingRepo.deleteBooking(data);
    }
}
