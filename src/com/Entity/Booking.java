package com.Entity;

public class Booking {
    /**
     * primary key of Booking
     */
    private int idBooking;
    /**
     * foreign key on Room
     */
    private int roomNumber;
    /**
     * foreign key on User
     */
    private String eventName;

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getAttenders() {
        return attenders;
    }

    public void setAttenders(int attenders) {
        this.attenders = attenders;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public int getReccuring() {
        return reccuring;
    }

    public void setReccuring(int reccuring) {
        this.reccuring = reccuring;
    }

    public String getDatetimeStart() {
        return datetimeStart;
    }

    public void setDatetimeStart(String datetimeStart) {
        this.datetimeStart = datetimeStart;
    }

    public String getDatetimeEnd() {
        return datetimeEnd;
    }

    public void setDatetimeEnd(String datetimeEnd) {
        this.datetimeEnd = datetimeEnd;
    }

    private int idUser;
    private int attenders;
    private String orderedBy;
    private int reccuring;
    private String datetimeStart;
    private String datetimeEnd;
}
