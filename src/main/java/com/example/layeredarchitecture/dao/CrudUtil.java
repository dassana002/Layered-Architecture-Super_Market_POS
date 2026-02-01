package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

    public static <T>T execute(String query, Object...objets) throws SQLException, ClassNotFoundException {
        // Get Connection
        Connection conn = DBConnection.getDbConnection().getConnection();
        // Create PreparedStatement
        PreparedStatement ps = conn.prepareStatement(query);
        // Set values to Parameters
        for (int i = 0; i < objets.length; i++) {
            ps.setObject(i + 1, objets[i]);
        }

        if (query.startsWith("select") || query.startsWith("SELECT")) {
            return (T) ps.executeQuery();
        }else {
            return (T)(Boolean) (ps.executeUpdate() > 0);
        }
    }
}
