package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO object) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",
                object.getOrderId(),
                object.getOrderDate(),
                object.getCustomerId()
        );
    }

    @Override
    public boolean update(OrderDTO object) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean isExists(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",  id);
        return rst.next();
    }

    @Override
    public String getLatestId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        if (rst.next()) {
            return String.format("OID-%03d", (
                    Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1));
        } else {
            return "OID-001";
        }
    }

    @Override
    public OrderDTO find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
