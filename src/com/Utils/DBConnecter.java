package com.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnecter {

        static final String JDBCDRIVER = "org.sqlite.JDBC";
        static final String DBURL = "jdbc:sqlite:E:\\projekti\\MiniProject.db";
//        private Connection connection = null;
//        PreparedStatement preparedStmt = null;

        public static Connection connectToDatabase() throws ClassNotFoundException, SQLException {

            Connection conn = null;
            Class.forName(JDBCDRIVER);
            conn = DriverManager.getConnection(DBURL);
            System.out.println("Connection to SQLite database has been established.");

            return conn;
        }

        public static PreparedStatement queryDB (String sqlQuery) throws SQLException, ClassNotFoundException {

            Connection connection = DBConnecter.connectToDatabase();
            PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery);
//            connection.commit();

            return preparedStmt;
        }

//        public static void closeConnectionToDatabase(Connection) {
//
//        }

}


