package training.my.process;

import de.hybris.platform.processengine.BusinessProcessEvent;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.enums.ProcessState;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

public class MyextProcessIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private BusinessProcessService businessProcessService;

    @Test
    public void checkMyextProcessAddRemove() {
        //given
        BusinessProcessModel testProcess = businessProcessService.createProcess("myextTestProcess", "myextProcessDefinition");
        businessProcessService.startProcess(testProcess);


        //when
        businessProcessService.triggerEvent(BusinessProcessEvent.newEvent("AddMyextProductToCartEvent"));
        businessProcessService.triggerEvent(BusinessProcessEvent.newEvent("MyextProductRemoveFromCartEvent"));

        //then
        assertEquals("Wrong process state: expected - SUCCEEDED, actual - " + testProcess.getProcessState().getCode(),
                ProcessState.SUCCEEDED, testProcess.getProcessState());
        assertEquals("Everything was fine", testProcess.getEndMessage());
    }

    @Test
    public void checkMyextProcessAddPlace() {
        //given
        businessProcessService.startProcess("myextTestProcess", "myextProcessDefinition");
        BusinessProcessModel testProcess = businessProcessService.getProcess("myextTestProcess");

        //when
        businessProcessService.triggerEvent(BusinessProcessEvent.newEvent("AddMyextProductToCartEvent"));
        businessProcessService.triggerEvent(BusinessProcessEvent.newEvent("OrderPlacementEvent"));

        //then
        assertEquals(ProcessState.SUCCEEDED, testProcess.getProcessState());
        assertEquals("Everything was fine", testProcess.getEndMessage());
    }

}
