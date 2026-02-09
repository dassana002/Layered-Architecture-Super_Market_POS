package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.entity.OrderDetail;

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

            ItemDAOImpl itemDAOImpl = (ItemDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

            // Find Item
            Item entity = itemDAOImpl.find(detail.getItemCode());
            entity.setQtyOnHand(entity.getQtyOnHand() - detail.getQty());

            boolean isItemDecrement = itemDAOImpl.update(entity);
            if (!isItemDecrement) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail object) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail object) throws SQLException, ClassNotFoundException {
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
    public OrderDetail find(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
