package training.my.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.order.Order;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import training.my.service.MyextensionOrderService;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class DefaultMyextensionOrderServiceIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private MyextensionOrderService myextensionOrderService;

    @Resource
    private ModelService modelService;

    private UserModel user;
    private CurrencyModel currency;

    @Before
    public void setUp() {
        createTestData();
    }

    @Test
    public void checkTotalOrderCountDelta() {
        //given
        OrderModel order1 = new OrderModel();
        order1.setCode("order1");
        order1.setUser(user);
        order1.setDate(new Date());
        order1.setCurrency(currency);

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
        OrderModel order2 = new OrderModel();
        order2.setCode("order2");
        order2.setUser(user);
        order2.setDate(new Date());
        order2.setCurrency(currency);
        modelService.save(order2);

        //when
        OrderModel latestOrder = myextensionOrderService.getLatestOrder();

        //then
        assertNotNull(latestOrder);
        assertEquals(order2.getCode(), latestOrder.getCode());
    }

    @Test
    public void checkUserWithMostOrders() {
        //given
        OrderModel order3 = modelService.create(OrderModel.class);
        order3.setCode("order3");
        order3.setUser(user);
        order3.setDate(new Date());
        order3.setCurrency(currency);
        modelService.save(order3);

        //when
        UserModel resultUser = myextensionOrderService.getUserWithMostOrders();

        //then
        assertNotNull(resultUser);
        assertEquals(user.getName(), resultUser.getName());
    }

    private void createTestData() {
        user = modelService.create(UserModel.class);
        user.setName("testUser");
        user.setUid("testUser");
        currency = modelService.create(CurrencyModel.class);
        currency.setIsocode("UAH");
        currency.setSymbol("₴");
        modelService.saveAll();
    }
}
