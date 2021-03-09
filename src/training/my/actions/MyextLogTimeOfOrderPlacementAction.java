package training.my.actions;

import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.task.RetryLaterException;

import java.util.Date;
import java.util.logging.Logger;

public class MyextLogTimeOfOrderPlacementAction extends AbstractSimpleDecisionAction {

    private static final Logger LOG = Logger.getLogger(MyextLogTimeOfAddingAction.class.getName());

    @Override
    public Transition executeAction(BusinessProcessModel businessProcessModel) throws RetryLaterException, Exception {
        LOG.info("Order was successfully placed at " + new Date());
        return Transition.OK;
    }
}
