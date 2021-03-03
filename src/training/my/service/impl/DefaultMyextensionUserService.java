package training.my.service.impl;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import training.my.service.MyextensionUserService;

public class DefaultMyextensionUserService implements MyextensionUserService {

    private FlexibleSearchService flexibleSearchService;

    @Override
    public int getTotalNumberOfUsers() {
        String queryString = "select {" + UserModel.PK + "} " +
                "from {" + UserModel._TYPECODE + "}";
        FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        SearchResult<UserModel> result = flexibleSearchService.search(query);
        return result.getTotalCount();
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
