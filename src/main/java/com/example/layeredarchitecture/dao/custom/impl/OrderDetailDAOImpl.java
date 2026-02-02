package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import java.sql.SQLException;
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
            ItemDTO item = itemDAOImpl.findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            boolean isItemDecrement = itemDAOImpl.updateItem(item);
            if (!isItemDecrement) {
                return false;
            }
        }
        return true;
    }
}
