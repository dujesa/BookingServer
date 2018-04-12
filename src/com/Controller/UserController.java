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

    UserRepository userRepo = new UserRepository();

    public boolean addUser(JSONObject user) throws SQLException, ClassNotFoundException {

//        userRepo = new UserRepository();

        return userRepo.addUser(user);
    }

    @Override
    public boolean resetUserPassword(JSONObject user) throws SQLException, ClassNotFoundException {

        return userRepo.resetUserPassword(user);

    }

    @Override
    public boolean deleteUser(JSONObject user) {

        return userRepo.deleteUser(user);

    }

    @Override
    public User login(JSONObject user) {
        return null;
    }

    public boolean logout() {
        return false;
    }
}
