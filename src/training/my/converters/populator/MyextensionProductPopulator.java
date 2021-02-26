package training.my.converters.populator;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class MyextensionProductPopulator<SOURCE extends ProductModel, TARGET extends ProductData> implements Populator<ProductModel, ProductData> {
    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException {
        productData.setCustomTestField(productModel.getCode() + "_" + productModel.getPk());
    }
}
