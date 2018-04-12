package com.Repository;

import com.Entity.User;
import com.Utils.DBConnecter;
import vendor.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    boolean returnValue;
    @Override
    public boolean addUser(/*User*/ JSONObject user) throws SQLException, ClassNotFoundException {

//        Connection connection = DBConnecter.connectToDatabase();
        String sql = "INSERT INTO users " +
                "(name,last_name,username,password,office_number,role,mail,user_phone,status) " +
                "VALUES (?,?,?,?,?,0,?,?,1)";
        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);
//        preparedStatement.setString(1, user.getName());
//        preparedStatement.setString(2, user.getLastName());
//        preparedStatement.setString(3, user.getUsername());
//        preparedStatement.setString(4, user.getPassword());
//        preparedStatement.setString(5, user.getOfficeNumber());
//        preparedStatement.setString(6, user.getMail());
//        preparedStatement.setString(7, user.getUserPhone());
        preparedStatement.setString(1, user.getString("name"));
        preparedStatement.setString(2, user.getString("lastName"));
        preparedStatement.setString(3, user.getString("username"));
        preparedStatement.setString(4, user.getString("password"));
        preparedStatement.setString(5, user.getString("officeNumber"));
        preparedStatement.setString(6, user.getString("userPhone"));
        preparedStatement.setString(7, user.getString("mail"));
//        preparedStatement.setString(1, user.getString("role"));

        //returnValue = (preparedStatement.executeUpdate()==0)?false:true;
        if(preparedStatement.executeUpdate() != 0)
            return true;
        else
            return false;

        //return returnValue;
    }

    @Override
    public boolean resetUserPassword() {
        return false;
    }

    @Override
    public boolean deleteUser() {
        return false;
    }

    @Override
    public User login() {
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }
}
