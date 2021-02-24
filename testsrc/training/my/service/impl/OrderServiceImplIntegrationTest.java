package training.my.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import training.my.service.OrderService;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class OrderServiceImplIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private OrderService myextensionOrderService;

    @Resource
    private ModelService modelService;

    private OrderModel order1;
    private OrderModel order2;

    @Before
    public void setUp() {
        createTestData();
    }

    @Test
    public void checkTotalOrderCountDelta() {
        //given

        //when
        int resultBefore = myextensionOrderService.getTotalNumberOfOrders();
        modelService.save(order1);
        int resultAfter = myextensionOrderService.getTotalNumberOfOrders();

        //then
        assertEquals(1, resultAfter - resultBefore);
    }

    @Test
    public void checkLatestOrder() {
        //given
        modelService.save(order2);

        //when
        OrderModel latestOrder = myextensionOrderService.getLatestOrder();

        //then
        assertNotNull(latestOrder);
        assertEquals(order2.getCode(), latestOrder.getCode());
    }

    @Test
    public void checkUserWithMostOrders() {
        //when
        UserModel resultUser = myextensionOrderService.getUserWithMostOrders();

        //then
        assertNotNull(resultUser);
    }

    private void createTestData() {
        UserModel user = new UserModel();
        user.setName("testUser");
        user.setUid("testUser");
        CurrencyModel currency = new CurrencyModel();
        currency.setIsocode("UAH");
        currency.setSymbol("₴");
        order1 = new OrderModel();
        order1.setCode("order1");
        order1.setUser(user);
        order1.setDate(new Date());
        order1.setCurrency(currency);
        order2 = new OrderModel();
        order2.setCode("order2");
        order2.setUser(user);
        order2.setDate(new Date());
        order2.setCurrency(currency);
    }
}
