package com.Repository;

import com.Entity.User;
import com.Utils.DBConnecter;
import vendor.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    User user;
    JSONObject response = new JSONObject();
    boolean returnValue;

    @Override
    public boolean addUser(/*User*/ JSONObject user) throws SQLException, ClassNotFoundException {

//        Connection connection = DBConnecter.connectToDatabase();
        String sql =
                "INSERT INTO users " +
                        "(name,last_name,username,password,office_number,role,mail,user_phone,status) " +
                        "VALUES (?,?,?,?,?,0,?,?,1)";

        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);

        preparedStatement.setString(1, user.getString("name"));
        preparedStatement.setString(2, user.getString("lastName"));
        preparedStatement.setString(3, user.getString("username"));
        preparedStatement.setString(4, user.getString("password"));
        preparedStatement.setString(5, user.getString("officeNumber"));
        preparedStatement.setString(6, user.getString("userPhone"));
        preparedStatement.setString(7, user.getString("mail"));

        returnValue = (preparedStatement.executeUpdate() == 0) ? false : true;

        return returnValue;
    }

    @Override
    public boolean resetUserPassword(JSONObject user) throws SQLException, ClassNotFoundException {

        //System.out.println(user);
        String sql =
                "UPDATE users " +
                        "SET password   = ? " +
                        "WHERE username = ?;";
        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);

        preparedStatement.setString(1, user.getString("newPassword"));
        preparedStatement.setString(2, user.getString("username"));

        returnValue = (preparedStatement.executeUpdate() == 0) ? false : true;
        return returnValue;
    }

    @Override
    public boolean deleteUser(JSONObject user) throws SQLException, ClassNotFoundException {

        String sql =
                "UPDATE users " +
                        "SET status  = 0 " +
                        "WHERE username = ?;";

        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);
        preparedStatement.setString(1, user.getString("username"));

        returnValue = (preparedStatement.executeUpdate() == 0) ? false : true;
        return returnValue;

    }

    @Override
    public JSONObject login(JSONObject user) throws SQLException, ClassNotFoundException {
        //this.user = new User();
        String sql =
                "SELECT * FROM users" +
                        " WHERE username = '" + user.getString("username") + "'" +
                        " AND password = '" + user.getString("password") + "';";


        ResultSet resultRows = DBConnecter.searchDB(sql);

        // loop through the result set
        if (resultRows.isBeforeFirst()) {
            while (resultRows.next()) {

                response.put("userID", resultRows.getString("id_user"));
                response.put("name", resultRows.getString("name"));
                response.put("lastName", resultRows.getString("last_name"));
                response.put("username", resultRows.getString("username"));
                response.put("officeNumber", resultRows.getString("office_number"));
                response.put("userType", resultRows.getString("role"));
                response.put("email", resultRows.getString("mail"));
                response.put("phoneNumber", resultRows.getString("user_phone"));

            }
        } else {

            response.put("username", "404 - NotFound");

        }

        //DatabaseLayer.login();
        return response;
    }

    @Override
    public boolean logout() {

        return false;
    }
}



