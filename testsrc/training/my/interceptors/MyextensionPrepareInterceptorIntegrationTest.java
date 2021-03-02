package training.my.interceptors;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MyextensionPrepareInterceptorIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    ModelService modelService;

    @Test(expected = InterceptorException.class)
    public void checkUserCreationWithWrongAge() {
        //given
        UserModel userModel = modelService.create(UserModel.class);
        userModel.setName("test");
        userModel.setUid("test");
        userModel.setAge(-1);

        //when
        modelService.save(userModel);

        //then
    }

}
