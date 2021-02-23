package training.my.service;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;

public interface OrderService {

    int getTotalNumberOfOrders();

    OrderModel getLatestOrder();

    UserModel getUserWithMostOrders();

}
