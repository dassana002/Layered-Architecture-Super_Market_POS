package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO {

    CustomerDTO findCustomer(String newValue) throws SQLException,  ClassNotFoundException;

    ItemDTO findItem(String newItemCode) throws SQLException,  ClassNotFoundException;

    boolean isExistsItem(String code) throws SQLException,  ClassNotFoundException;

    boolean isExistsCustomer(String id) throws SQLException,  ClassNotFoundException;
    
    String getLatestOrderId() throws SQLException,  ClassNotFoundException;

    ArrayList<String> getAllOrderIds() throws SQLException,  ClassNotFoundException;

    ArrayList<ItemDTO> getAllItem() throws SQLException,  ClassNotFoundException;

    boolean isExistsOrder(String orderId) throws SQLException,  ClassNotFoundException;

    boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException,  ClassNotFoundException;
}
