package com.example.layeredarchitecture.dao;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (daoFactory==null){
            return new DAOFactory();
        }
        return daoFactory;
    }
}
