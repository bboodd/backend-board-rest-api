package com.hh.board.common.connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTestTest {

    @Test
    void getConnection() {

        final String DB_URL = "jdbc:mysql://localhost:3308/ebrainsoft_study";
        final String USER = "ebsoft";
        final String PASS = "ebsoft";


        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("conn: "+conn);
    }

}