package com.Utils;

import com.Entity.Room;

public interface IRoom {
     Room viewSingleRoom(String s);
     Room[] viewAvailableRooms();
}
