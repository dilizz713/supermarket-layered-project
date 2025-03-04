package lk.ijse.gdse.supermarket.bo;

import lk.ijse.gdse.supermarket.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOType {
        CUSTOMER, ORDER, ITEM, PLACEORDER, ORDERDETAIL
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACEORDER:
                return new PlaceOrderBOImpl();
            case ORDERDETAIL:
                return new OrderDetailsBOImpl();
            default:
                return null;
        }
    }
}
