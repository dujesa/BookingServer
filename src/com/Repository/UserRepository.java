package com.Repository;

import com.Entity.User;
import com.Utils.DBConnecter;
import vendor.json.JSONObject;

import java.sql.*;

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
                        "VALUES (?,?,?,?,?,0,?,?,1)"
                ;
        
        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);

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
    public boolean resetUserPassword(JSONObject user) throws SQLException, ClassNotFoundException {

        //System.out.println(user);
        String sql =
                "UPDATE users " +
                        "SET password   = ? " +
                        "WHERE username = ?;"
                ;
        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);

        preparedStatement.setString(1, user.getString("newPassword"));
        preparedStatement.setString(2, user.getString("username"));

        returnValue = (preparedStatement.executeUpdate()==0) ? false : true;
        return returnValue;
    }

    @Override
    public boolean deleteUser(JSONObject user) throws SQLException, ClassNotFoundException {

        String sql =
                "UPDATE users " +
                        "SET status  = 0 " +
                        "WHERE username = ?;"
                ;

        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);
        preparedStatement.setString(1, user.getString("username"));

        returnValue = (preparedStatement.executeUpdate()==0) ? false : true;
        return returnValue;

    }

    @Override
    public JSONObject login(JSONObject user) throws SQLException, ClassNotFoundException {
        //this.user = new User();
        String sql =
                "SELECT * FROM users" +
                        " WHERE username = '" + user.getString("username") + "'"+
                        " AND password = '" + user.getString("password") + "';"
                ;


        ResultSet resultRows = DBConnecter.searchDB(sql);

        // loop through the result set
        if(resultRows.isBeforeFirst()) {
            while (resultRows.next()) {

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


        return response;
    }

    @Override
    public boolean logout() {

        return false;
    }


//    //Util user
//    public User getUser(JSONObject user) throws SQLException, ClassNotFoundException {
//        this.user = new User();
//        String sql =
//                "SELECT * FROM users" +
//                        "WHERE username = ?" +
//                        "AND password = ?;"
//                ;
//
//
//
//        PreparedStatement preparedStatement = DBConnecter.queryDB(sql);
//
//        preparedStatement.setString(1, "%"+user.getString("username")+"%");
//        preparedStatement.setString(2, "%"+user.getString("password")+"%");
//
//        ResultSet resultRows = preparedStatement.executeQuery(sql);
//
//        // loop through the result set
//         if(!resultRows.isBeforeFirst()) {
//            while (resultRows.next()) {
//
//                this.user.setName(resultRows.getString("name"));
//                this.user.setLastName(resultRows.getString("last_name"));
//                this.user.setUsername(resultRows.getString("username"));
//                this.user.setPassword(resultRows.getString("password"));
//                this.user.setOfficeNumber(resultRows.getString("office_number"));
//                this.user.setRole(resultRows.getString("role"));
//                this.user.setMail(resultRows.getString("mail"));
//                this.user.setUserPhone(resultRows.getString("user_phone"));
//                //user.setName(resultRows.getString("status"));
//
//            }
//         } else {
//
//             this.user = null;
//
//         }
//
//        return this.user;
//        user = new User();
//        ResultSet queryResult = preparedStatement.executeQuery();
//
//        while(queryResult.next()) {
//            user.setName(queryResult.getString("id_user"));
//            user.setName(queryResult.getString("name"));
//            user.setName(queryResult.getString("last_name"));
//            user.setName(queryResult.getString("username"));
//            user.setName(queryResult.getString("password"));
//            user.setName(queryResult.getString("office_number"));
//            user.setName(queryResult.getString("role"));
//            user.setName(queryResult.getString("mail"));
//            user.setName(queryResult.getString("user_phone"));
//            user.setName(queryResult.getString("status"));
//
//
//        }
//        System.out.println();

//        return user;

    }
//}

/*
select by type
//                if (message.equals("view room by type")) {
//
//                    String sql = "Select *\n" +
//                            "from room\n" +
//                            "where type='Predavaonica';\n";
//                    //samo jedan set result ide s jednim statementom sa svakim novim
//                    // seet resultom ide novi statemtn
//                    try (Connection conn = this.konekcija();
//                         Statement stmt = conn.createStatement();
//                         ResultSet rs = stmt.executeQuery(sql)) {
//
//                        // loop through the result set
//                        while (rs.next()) {
//                            System.out.println(rs.getInt("room_number") + "\t" +
//                                    rs.getString("type") + "\t" +
//                                    rs.getInt("capacity") + "\t" +
//                                    rs.getString("phone"));
//                        }
//                    } catch (SQLException e) {
//                        System.out.println(e.getMessage());
//                    }
                    //pregled soba po kapacitetu
                /*view room by capacity*/
//                 else if (message.equals("view room by capacity")) {
//
//                    message = "Enter room capacity to view";
//
//                    dout.flush();
//
//                    dout.writeUTF(message);
//
//                    message = din.readUTF();
//
//                    String sql = "Select *\n" +
//                            "from room\n" +
//                            "where capacity='" + message + "';\n";
//
//                    //samo jedan set result ide s jednim statementom sa svakim novim
//                    // seet resultom ide novi statemtn
//                    try (Connection conn = this.konekcija();
//                         Statement stmt = conn.createStatement();
//                         ResultSet rs = stmt.executeQuery(sql)) {
//
//                        // loop through the result set
//                        while (rs.next()) {
//                            System.out.println(rs.getInt("room_number") + "\t" +
//                                    rs.getString("type") + "\t" +
//                                    rs.getInt("capacity") + "\t" +
//                                    rs.getString("phone"));
//                        }
//                    }
//                }

