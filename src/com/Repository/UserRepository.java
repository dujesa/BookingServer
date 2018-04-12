package com.Repository;

import com.Entity.User;
import com.Utils.DBConnecter;
import vendor.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    User user;
    boolean returnValue;

    @Override
    public boolean addUser(/*User*/ JSONObject user) throws SQLException, ClassNotFoundException {

//        Connection connection = DBConnecter.connectToDatabase();
        String sql = 
                "INSERT INTO users " +
                "(name,last_name,username,password,office_number,role,mail,user_phone,status) " +
                "VALUES (?,?,?,?,?,0,?,?,1)"
                ;
        
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

        returnValue = (preparedStatement.executeUpdate()==0) ? false : true;

        return returnValue;
    }

    @Override
    public boolean resetUserPassword(JSONObject params) throws SQLException, ClassNotFoundException {

        System.out.println(params);
        String sql =
                "UPDATE users " +
                "SET password   = ? " +
                "WHERE username = ?;"
                ;
        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);

        preparedStatement.setString(1, params.getString("newPassword"));
        preparedStatement.setString(2, params.getString("username"));

        returnValue = (preparedStatement.executeUpdate()==0) ? false : true;
        System.out.println("ZA IVUUUUUU"+returnValue);
        return returnValue;
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


    //Util user
    public User getUser(String username) throws SQLException, ClassNotFoundException {
        String sql = 
                "SELECT * FROM users" +
                "WHERE username = ?;";
        
        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);
        preparedStatement.setString(1, username);

        user = new User();
        ResultSet queryResult = preparedStatement.executeQuery();

        while(queryResult.next()) {
            user.setName(queryResult.getString("id_user"));
            user.setName(queryResult.getString("name"));
            user.setName(queryResult.getString("last_name"));
            user.setName(queryResult.getString("username"));
            user.setName(queryResult.getString("password"));
            user.setName(queryResult.getString("office_number"));
            user.setName(queryResult.getString("role"));
            user.setName(queryResult.getString("mail"));
            user.setName(queryResult.getString("user_phone"));
            user.setName(queryResult.getString("status"));

        }
        System.out.println();

        return user;
    }
}
