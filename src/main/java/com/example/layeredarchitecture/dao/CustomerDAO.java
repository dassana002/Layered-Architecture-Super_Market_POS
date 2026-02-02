package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;
    public boolean existsCustomer(String customerId) throws SQLException, ClassNotFoundException;
    public String getLatestId() throws SQLException, ClassNotFoundException;
    public CustomerDTO getCustomer(String newValue) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
}
