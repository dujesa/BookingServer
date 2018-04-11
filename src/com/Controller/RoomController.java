package com.Controller;

import com.Entity.Room;
import com.Utils.IRoom;

public class RoomController implements IRoom {

    @Override
    public Room viewSingleRoom(String s) {
        return null;
    }

    @Override
    public Room[] viewAvailableRooms() {
        return new Room[0];
    }
}
