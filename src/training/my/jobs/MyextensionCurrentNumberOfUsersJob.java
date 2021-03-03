package training.my.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import training.my.service.MyextensionUserService;

import java.util.logging.Logger;

public class MyextensionCurrentNumberOfUsersJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(MyextensionCurrentNumberOfUsersJob.class.getName());

    private MyextensionUserService myextensionUserService;

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        LOG.info("Current number of users is " + myextensionUserService.getTotalNumberOfUsers());
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public MyextensionUserService getMyextensionUserService() {
        return myextensionUserService;
    }

    public void setMyextensionUserService(MyextensionUserService myextensionUserService) {
        this.myextensionUserService = myextensionUserService;
    }
}
