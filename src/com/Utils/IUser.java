package com.Utils;

import com.Entity.User;
import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IUser{
     boolean addUser(JSONObject user) throws SQLException, ClassNotFoundException;
     boolean resetUserPassword(JSONObject user) throws SQLException, ClassNotFoundException;
     boolean deleteUser(JSONObject user);
     User login(JSONObject user);
     boolean logout();

}
