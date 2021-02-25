package training.my.service.impl;

import de.hybris.platform.converters.data.TestProductData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.product.daos.ProductDao;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.UserService;
import training.my.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private FlexibleSearchService flexibleSearchService;

    private SearchRestrictionService searchRestrictionService;

    private UserService userService;

    @Override
    public int getTotalNumberOfOrders() {
        String queryString = "select {" + OrderModel.PK + "} " +
                "from {" + OrderModel._TYPECODE + "}";
        FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        SearchResult<OrderModel> result = flexibleSearchService.search(query);
        return result.getTotalCount();
    }

    @Override
    public OrderModel getLatestOrder() {
        String queryString = "select {" + OrderModel.PK + "} " +
                "from {" + OrderModel._TYPECODE + "} " +
                "order by {" + OrderModel.DATE + "} desc";
        FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        SearchResult<OrderModel> result = flexibleSearchService.search(query);
        if (result.getTotalCount() <= 0) {
            throw new RuntimeException("There is no data in search result");
        }
        List<OrderModel> orderModelList = result.getResult();
        return orderModelList.get(0);
    }

    @Override
    public UserModel getUserWithMostOrders() {
        String queryString = "select {u." + UserModel.PK + "} " +
                "from {" + UserModel._TYPECODE + " as u join " + OrderModel._TYPECODE + " as o " +
                "on {u." + UserModel.PK + "} = {o." + OrderModel.USER + "}} " +
                "group by {u." + UserModel.PK + "} " +
                "order by count({o." + OrderModel.PK + "}) desc";
        FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        searchRestrictionService.disableSearchRestrictions();
        userService.setCurrentUser(userService.getUserForUID("admin"));
        SearchResult<UserModel> result = flexibleSearchService.search(query);
        if (result.getTotalCount() <= 0) {
            throw new RuntimeException("There is no data in search result");
        }
        List<UserModel> userModelList = result.getResult();
        return userModelList.get(0);
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    public void setSearchRestrictionService(SearchRestrictionService searchRestrictionService) {
        this.searchRestrictionService = searchRestrictionService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
