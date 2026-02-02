package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.ItemDTO;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<ItemDTO> items = new ArrayList<>();
        while (rst.next()) {
            ItemDTO item = new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
            items.add(item);
        }
        return items;
    }

    public boolean deleteItem(String itemId)  throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?",  itemId);
    }

    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand(),
                item.getCode()
        );
    }

    public boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()
        );
    }

    public boolean existsItem(String itemCode) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT code FROM Item WHERE code=?", itemCode);
    }

    public String latestItemCode() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            return rst.getString("code");
        }
        return null;
    }

    public ItemDTO getItem(String newItemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE code=?", newItemCode);
        ItemDTO item = new ItemDTO();
        if (rst.next()) {
            item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        }
        return item;
    }

    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE code=?", code);
        if (rst.next()) {
           return new ItemDTO(
                   code,
                   rst.getString("description"),
                   rst.getBigDecimal("unitPrice"),
                   rst.getInt("qtyOnHand")
           );
        }
        return null;
    }
}
