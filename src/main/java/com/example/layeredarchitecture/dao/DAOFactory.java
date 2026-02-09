package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (daoFactory==null){
            return new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType {
        CUSTOMER,
        ITEM,
        ORDER,
        ORDER_DETAIL,
        QUERY
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER -> {
                return new CustomerDAOImpl();
            }
            case ITEM -> {
                return new ItemDAOImpl();
            }
            case ORDER -> {
                return new OrderDAOImpl();
            }
            case ORDER_DETAIL -> {
                return new OrderDetailDAOImpl();
            }
            case QUERY -> {
                return new QueryDAOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
