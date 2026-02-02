package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public boolean saveOrderDetailSave(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {

        for (OrderDetailDTO detail : orderDetails) {
            boolean isSaved = CrudUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",
                    orderId,
                    detail.getItemCode(),
                    detail.getUnitPrice(),
                    detail.getQty()
                    );

            // Check Order Details save
            if (!isSaved) {
                return false;
            }

            ItemDAOImpl itemDAOImpl = new ItemDAOImpl();

            // Find Item
            ItemDTO item = itemDAOImpl.find(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            boolean isItemDecrement = itemDAOImpl.update(item);
            if (!isItemDecrement) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetailDTO object) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetailDTO object) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean isExists(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLatestId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public OrderDetailDTO find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
