package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl {

    public boolean saveOrderDetailSave(String orderId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getDbConnection().getConnection();
        String query = "INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);

        for (OrderDetailDTO detail : orderDetails) {
            ps.setString(1, orderId);
            ps.setString(2, detail.getItemCode());
            ps.setBigDecimal(3, detail.getUnitPrice());
            ps.setInt(4, detail.getQty());

            int rs = ps.executeUpdate();

            // Check Order Details save
            if (!(rs > 0)) {
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
