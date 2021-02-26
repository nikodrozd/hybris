package training.my.facades.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.enums.CustomerReviewApprovalType;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Test;
import training.my.facades.MyextensionProductFacade;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class DefaultMyextensionProductFacadeIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private MyextensionProductFacade myextensionProductFacade;

    @Resource
    private ModelService modelService;

    @Test
    public void checkGetProductForCodeAndOptions () {
        //given
        CatalogModel catalogModel = modelService.create(CatalogModel.class);
        catalogModel.setName("Test");
        catalogModel.setId("testId");
        CatalogVersionModel catalogVersionModel = modelService.create(CatalogVersionModel.class);
        catalogVersionModel.setCatalog(catalogModel);
        catalogVersionModel.setVersion("testVersion");
        ProductModel productModel = modelService.create(ProductModel.class);
        productModel.setCode("testProduct");
        productModel.setCatalogVersion(catalogVersionModel);
        productModel.setApprovalStatus(ArticleApprovalStatus.APPROVED);
        modelService.saveAll();

        //when
        ProductData productData = myextensionProductFacade.getProductForCodeAndOptions("testProduct");

        //then
        assertNotNull(productData);
        assertEquals(productModel.getCode() + "_" + productModel.getPk(), productData.getCustomTestField());
    }


}
