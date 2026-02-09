package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.bo.custom.impl.CustomerBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemBOImpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance(){
        if (boFactory==null) return boFactory = new BOFactory();
        return boFactory;
    }

    public enum BOType {
        CUSTOMER,
        ORDER,
        ITEM
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER -> {
                return new CustomerBOImpl();
            }
            case ITEM -> {
                return new ItemBOImpl();
            }
            case ORDER -> {
                return new PlaceOrderBOImpl();
            }
            default -> {
                return null;
            }
        }
    }
}
