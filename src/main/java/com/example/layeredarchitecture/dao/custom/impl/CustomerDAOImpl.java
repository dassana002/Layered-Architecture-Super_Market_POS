package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (rs.next()) {
            Customer entity = new Customer(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("address")
            );
            customers.add(entity);
        }
        return customers;
    }

    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                entity.getId(),
                entity.getName(),
                entity.getAddress()
        );
    }

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                entity.getName(),
                entity.getAddress(),
                entity.getId()
        );
    }

    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?", customerId);
    }

    public boolean isExists(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT id FROM Customer WHERE id=?", customerId);
        return rs.next();
    }

    public String getLatestId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        return rs.getString("id");
    }

    public Customer find(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer WHERE id=?", newValue);
        if (rs.next()) {
            return new Customer(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("address")
            );
        }
        return null;
    }

    public ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<String> customerIds = new ArrayList<>();
        while (rs.next()) {
            customerIds.add(rs.getString("id"));
        }
        return customerIds;
    }
}
