package training.my.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@UnitTest
public class OrderServiceImplUnitTest {

    private OrderServiceImpl myextensionOrderService;

    @Mock
    private FlexibleSearchService flexibleSearchService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        myextensionOrderService = new OrderServiceImpl();
        myextensionOrderService.setFlexibleSearchService(flexibleSearchService);
    }

    @Test
    public void testGetTotalNumberOfOrders () {

        //given
        final SearchResult<Object> searchResult = new SearchResult<>() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public int getTotalCount() {
                return 2;
            }

            @Override
            public List<Object> getResult() {
                return null;
            }

            @Override
            public int getRequestedStart() {
                return 0;
            }

            @Override
            public int getRequestedCount() {
                return 0;
            }
        };

        //when
        when(flexibleSearchService.search(any(FlexibleSearchQuery.class))).thenReturn(searchResult);

        //then
        final int result = myextensionOrderService.getTotalNumberOfOrders();

        assertEquals(2, result);
        verify(flexibleSearchService).search(any(FlexibleSearchQuery.class));

    }

    @Test
    public void testGetLatestOrder() {

        //given
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final OrderModel order1 = new OrderModel();
        order1.setCode("order1");
        final OrderModel order2 = new OrderModel();
        order2.setCode("order2");
        try {
            order1.setDate(sdf.parse("2021-02-20"));
            order2.setDate(sdf.parse("2021-02-19"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final SearchResult<Object> searchResult = new SearchResult<>() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public int getTotalCount() {
                return 2;
            }

            @Override
            public List<Object> getResult() {
                return Arrays.asList(order1, order2);
            }

            @Override
            public int getRequestedStart() {
                return 0;
            }

            @Override
            public int getRequestedCount() {
                return 0;
            }
        };

        //when
        when(flexibleSearchService.search(any(FlexibleSearchQuery.class))).thenReturn(searchResult);

        //then
        OrderModel resultOrder = myextensionOrderService.getLatestOrder();

        assertEquals(order1, resultOrder);
        verify(flexibleSearchService).search(any(FlexibleSearchQuery.class));
    }

    @Test
    public void testGetUserWithMostOrders() {

        //given
        UserModel user1 = new UserModel();
        user1.setName("user1");
        UserModel user2 = new UserModel();
        user2.setName("user2");
        final SearchResult<Object> searchResult = new SearchResult<>() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public int getTotalCount() {
                return 2;
            }

            @Override
            public List<Object> getResult() {
                return Arrays.asList(user1, user2);
            }

            @Override
            public int getRequestedStart() {
                return 0;
            }

            @Override
            public int getRequestedCount() {
                return 0;
            }
        };

        //when
        when(flexibleSearchService.search(any(FlexibleSearchQuery.class))).thenReturn(searchResult);

        //then
        UserModel resultUser = myextensionOrderService.getUserWithMostOrders();

        assertEquals(user1, resultUser);
        verify(flexibleSearchService).search(any(FlexibleSearchQuery.class));
    }

}
