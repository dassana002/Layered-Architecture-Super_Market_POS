package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.PlaceOrderBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.dao.custom.impl.CustomerDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDAOImpl;
import com.example.layeredarchitecture.dao.custom.impl.OrderDetailDAOImpl;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    // Property Injection
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAIL);

    @Override
    public CustomerDTO findCustomer(String newValue) throws SQLException, ClassNotFoundException {
        return customerDAO.find(newValue);
    }

    @Override
    public ItemDTO findItem(String newItemCode) throws SQLException, ClassNotFoundException  {
        return itemDAO.find(newItemCode);
    }

    @Override
    public boolean isExistsItem(String code) throws SQLException, ClassNotFoundException  {
        return itemDAO.isExists(code);
    }

    @Override
    public boolean isExistsCustomer(String id) throws SQLException, ClassNotFoundException  {
        return customerDAO.isExists(id);
    }

    @Override
    public String getLatestOrderId() throws SQLException, ClassNotFoundException  {
        return orderDAO.getLatestId();
    }

    @Override
    public ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException  {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException  {
        return itemDAO.getAll();
    }

    @Override
    public boolean isExistsOrder(String orderId) throws SQLException, ClassNotFoundException  {
        return orderDAO.isExists(orderId);
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException  {
        /*Transaction*/
        Connection connection = DBConnection.getDbConnection().getConnection();

        try {
            connection.setAutoCommit(false);

            // Check Exist Order
            boolean isExists = orderDAO.isExists(orderId);

            if (!isExists) {
                // set orderDTO
                OrderDTO orderDTO = new OrderDTO(
                        orderId,
                        orderDate,
                        customerId
                );

                // Order save in DB
                boolean isOrderSave = orderDAO.save(orderDTO);

                // Check save order
                if (!isOrderSave) {
                    connection.rollback();
                    return false;
                }
            }

            // Order Detail save
            boolean isSaveOrderDetail = orderDetailDAO.saveOrderDetailSave(orderId, orderDetails);

            // check save order Details
            if (!isSaveOrderDetail) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();

        } finally {
            connection.setAutoCommit(true);
        }

        return false;
    }
}
