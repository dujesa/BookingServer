package com.Repository;

import com.Entity.User;
import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IUserRepository {
    boolean addUser(/*User*/JSONObject user) throws SQLException, ClassNotFoundException;
    boolean resetUserPassword(JSONObject params) throws SQLException, ClassNotFoundException;
    boolean deleteUser();
    User login();
    boolean logout();
}
