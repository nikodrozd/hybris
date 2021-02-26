package training.my.facades.impl;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import training.my.facades.MyextensionProductFacade;

import java.util.Collection;

public class DefaultMyextensionProductFacade implements MyextensionProductFacade {

    private Converter<ProductModel, ProductData> productConverter;
    private ProductService productService;

    @Override
    public ProductData getProductForCodeAndOptions(String code) {
        ProductModel productModel = getProductService().getProductForCode(code);
        ProductData productData = getProductConverter().convert(productModel);

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
}
