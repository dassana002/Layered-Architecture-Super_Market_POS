package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        while (rs.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("address")
            );
            customers.add(customerDTO);
        }
        return customers;
    }

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress()
                );
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getId()
        );
    }

    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?", customerId);
    }

    public boolean existsCustomer(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT id FROM Customer WHERE id=?", customerId);
        return rs.next();
    }

    public String getLatestId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        return rs.getString("id");
    }

    public CustomerDTO getCustomer(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer WHERE id=?", newValue);
        if (rs.next()) {
            return new CustomerDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("address")
            );
        }
        return null;
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<String> customerIds = new ArrayList<>();
        while (rs.next()) {
            customerIds.add(rs.getString("id"));
        }
        return customerIds;
    }
}
