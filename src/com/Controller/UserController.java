package com.Controller;

import com.Repository.IUserRepository;
import com.Repository.UserRepository;
import vendor.json.JSONObject;

import java.sql.SQLException;

/**
 * Kontroler klasa vezana za sva usmjeravanja prema UserRepositoryju
 */
public class UserController implements IUserRepository {

    UserRepository userRepo = new UserRepository();

    public boolean addUser(JSONObject user) throws SQLException, ClassNotFoundException {

        return userRepo.addUser(user);
    }

    @Override
    public boolean resetUserPassword(JSONObject user) throws SQLException, ClassNotFoundException {

        return userRepo.resetUserPassword(user);

    }

    @Override
    public boolean deleteUser(JSONObject user) throws SQLException, ClassNotFoundException {

        return userRepo.deleteUser(user);

    }

    @Override
    public JSONObject login(JSONObject user) throws SQLException, ClassNotFoundException {

        return userRepo.login(user);

    }

    public boolean logout() {

        return true;

    }
}
