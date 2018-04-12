package com.Utils;

import com.Entity.User;
import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IUser{
     User addUser(JSONObject user);
     boolean resetUserPassword(JSONObject user) throws SQLException, ClassNotFoundException;
     boolean deleteUser(JSONObject user);
     User login(JSONObject user);
     boolean logout();

}
