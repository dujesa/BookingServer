package com.Controller;

import com.Entity.User;
import com.Repository.UserRepository;
import com.Utils.IUser;
import vendor.json.JSONObject;

import java.sql.SQLException;

/**
 * Controller class for controlling requests related to user entity actions
 */
public class UserController implements IUser {

    UserRepository userRepo;

    public User addUser(JSONObject data) {
        return null;
    }

    @Override
    public boolean resetUserPassword(JSONObject user) throws SQLException, ClassNotFoundException {

        userRepo = new UserRepository();

        return userRepo.resetUserPassword(user);

    }

    @Override
    public boolean deleteUser(JSONObject user) {
        return false;
    }

    @Override
    public User login(JSONObject user) {
        return null;
    }

    public boolean resetUserPassword() {
        return false;
    }
    public boolean deleteUser()  {
        return false;
    }
    public User login() {
        return null;
    }
    public boolean logout() {
        return false;
    }
}
