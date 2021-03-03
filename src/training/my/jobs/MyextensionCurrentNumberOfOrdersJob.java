package training.my.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import training.my.service.MyextensionOrderService;

import java.util.logging.Logger;

public class MyextensionCurrentNumberOfOrdersJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(MyextensionCurrentNumberOfOrdersJob.class.getName());

    private MyextensionOrderService myextensionOrderService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        LOG.info("Current number of orders is " + myextensionOrderService.getTotalNumberOfOrders());
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public MyextensionOrderService getMyextensionOrderService() {
        return myextensionOrderService;
    }

    public void setMyextensionOrderService(MyextensionOrderService myextensionOrderService) {
        this.myextensionOrderService = myextensionOrderService;
    }
}
