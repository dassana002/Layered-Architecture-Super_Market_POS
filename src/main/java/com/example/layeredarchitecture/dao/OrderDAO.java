package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.OrderDTO;
import java.sql.SQLException;

public interface OrderDAO {
    public String generateNewOrderId() throws SQLException, ClassNotFoundException ;
    public boolean existsOrderId(String orderId) throws SQLException, ClassNotFoundException ;
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException ;
}
