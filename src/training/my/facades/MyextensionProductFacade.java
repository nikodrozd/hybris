package training.my.facades;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.Collection;

public interface MyextensionProductFacade {

    ProductData getProductForCodeAndOptions(final String code);

}
