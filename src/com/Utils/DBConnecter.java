package com.Utils;

import java.sql.*;

public class DBConnecter {

        static final String JDBCDRIVER = "org.sqlite.JDBC";
        static final String DBURL = "jdbc:sqlite:E:\\projekti\\MiniProject.db";
        private static Connection conn =null;

        public static Connection connectToDatabase() throws ClassNotFoundException, SQLException {

//            Connection conn = null;
            Class.forName(JDBCDRIVER);
            conn = DriverManager.getConnection(DBURL);
            System.out.println("Connection to SQLite database has been established.");

            return conn;
        }
        public static void closeDatabaseConnection() throws SQLException {

            conn.close();

        }

        public static PreparedStatement queryDB (String sqlQuery) throws SQLException, ClassNotFoundException {

            Connection connection = DBConnecter.connectToDatabase();
            PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery);

            return preparedStmt;
        }
        public static ResultSet searchDB (String sqlQuery) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnecter.connectToDatabase();
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(sqlQuery);
        }

}


