package com.Controller;

import com.Entity.Room;
import com.Utils.IRoom;
import vendor.json.JSONObject;

public class RoomController implements IRoom {

    @Override
    public Room viewSingleRoom(JSONObject data) {
        return null;
    }

    @Override
    public Room[] viewAvailableRooms() {
        return new Room[0];
    }
}
