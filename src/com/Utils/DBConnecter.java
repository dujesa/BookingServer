package com.Utils;

import java.sql.*;

/**
 * Utility klasa za operacije vezane za bazu
 */
public class DBConnecter {

        static final String JDBCDRIVER = "org.sqlite.JDBC";
        static final String DBURL = "jdbc:sqlite:E:\\projekti\\MiniProject.db";
        private static Connection conn = null;

    /**
     * Metoda za konekciju na bazu
     */
        public static Connection connectToDatabase() throws ClassNotFoundException, SQLException {

//            Connection conn = null;
            Class.forName(JDBCDRIVER);
            conn = DriverManager.getConnection(DBURL);
            System.out.println("Connection to SQLite database has been established.");

            return conn;
        }

    /**
     * Metoda za zatvaranje konekcije na bazu
     */
        public static void closeDatabaseConnection() throws SQLException {

            conn.close();

        }

    /**
     * Metoda koja omotaje funkcionalnosti konekcije na bazu i pripremanje upita(zamjenu ? sa stvarnim vrijednostima)
     */
        public static PreparedStatement queryDB (String sqlQuery) throws SQLException, ClassNotFoundException {

            Connection connection = DBConnecter.connectToDatabase();
            PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery);

            return preparedStmt;
        }

    /**
     * Metoda koja omotaje funkcionalnosti konekcije na bazu i izvršavanje upita
     */
        public static ResultSet searchDB (String sqlQuery) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnecter.connectToDatabase();
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(sqlQuery);
        }

}


