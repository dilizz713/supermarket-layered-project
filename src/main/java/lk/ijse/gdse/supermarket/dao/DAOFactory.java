package lk.ijse.gdse.supermarket.dao;

import lk.ijse.gdse.supermarket.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse.supermarket.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse.supermarket.dao.custom.impl.OrderDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOType {
        CUSTOMER, ORDER, ITEM, ORDERDETAIL
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDERDETAIL:
                return new OrderDAOImpl();
            default:
                return null;
        }
    }
}
