package training.my.facades.impl;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.ConfigurablePopulator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import training.my.facades.MyextensionProductFacade;

import java.util.Collection;

public class DefaultMyextensionProductFacade implements MyextensionProductFacade {

    private Converter<ProductModel, ProductData> productConverter;
    private ProductService productService;
    private ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator;

    @Override
    public ProductData getProductForCodeAndOptions(String code, Collection<ProductOption> options) {
        ProductModel productModel = getProductService().getProductForCode(code);
        ProductData productData = getProductConverter().convert(productModel);

        if (options != null)
        {
            getProductConfiguredPopulator().populate(productModel, productData, options);
        }

        return productData;
    }

    public Converter<ProductModel, ProductData> getProductConverter() {
        return productConverter;
    }

    public void setProductConverter(Converter<ProductModel, ProductData> productConverter) {
        this.productConverter = productConverter;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ConfigurablePopulator<ProductModel, ProductData, ProductOption> getProductConfiguredPopulator() {
        return productConfiguredPopulator;
    }

    public void setProductConfiguredPopulator(ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator) {
        this.productConfiguredPopulator = productConfiguredPopulator;
    }
}
