package com.example.layeredarchitecture.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T object) throws SQLException, ClassNotFoundException;
    boolean update(T object) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    boolean isExists(String id) throws SQLException, ClassNotFoundException;
    String getLatestId() throws SQLException, ClassNotFoundException;
    T find(String id) throws SQLException, ClassNotFoundException;
}
