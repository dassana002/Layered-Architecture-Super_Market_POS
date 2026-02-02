package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String itemId)  throws SQLException, ClassNotFoundException;
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    public boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    public boolean existsItem(String itemCode) throws SQLException, ClassNotFoundException;
    public String latestItemCode() throws SQLException, ClassNotFoundException;
    public ItemDTO getItem(String newItemCode) throws SQLException, ClassNotFoundException;
    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException;
}
