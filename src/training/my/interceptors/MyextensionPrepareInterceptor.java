package training.my.interceptors;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

public class MyextensionPrepareInterceptor implements PrepareInterceptor<UserModel> {
    @Override
    public void onPrepare(UserModel userModel, InterceptorContext interceptorContext) throws InterceptorException {
        if (userModel.getAge() <= 0) {
            throw new InterceptorException("User age cannot be 0 or less");
        }
    }
}
