package com.Repository;

import vendor.json.JSONObject;

import java.sql.SQLException;

public interface IUserRepository {
    boolean addUser(/*User*/JSONObject user) throws SQLException, ClassNotFoundException;
    boolean resetUserPassword(JSONObject params) throws SQLException, ClassNotFoundException;
    boolean deleteUser(JSONObject user) throws SQLException, ClassNotFoundException;
    JSONObject login(JSONObject user) throws SQLException, ClassNotFoundException;
//    User getUser(JSONObject user) throws SQLException, ClassNotFoundException;
    boolean logout();

}
