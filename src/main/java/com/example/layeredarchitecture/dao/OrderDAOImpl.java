package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {

    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        if (rst.next()) {
            return String.format("OID-%03d", (
                    Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1));
        } else {
            return "OID-001";
        }
    }

    public boolean existsOrderId(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",  orderId);
        return rst.next();
    }

    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",
                orderDTO.getOrderId(),
                orderDTO.getOrderDate(),
                orderDTO.getCustomerId()
        );
    }
}
