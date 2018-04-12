package com.Utils;

import com.Entity.Room;
import vendor.json.JSONObject;

public interface IRoom {
     Room viewSingleRoom(JSONObject s);
     Room[] viewAvailableRooms();
}
