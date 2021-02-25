package training.my.facades;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.Collection;

public interface MyextensionProductFacade {

    public ProductData getProductForCodeAndOptions(final String code, final Collection<ProductOption> options);

}
