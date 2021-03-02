package training.my.event.listeners;

import de.hybris.eventtracking.model.events.AddToCartEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import org.apache.log4j.Logger;

public class MyextensionAddToCartEventListener extends AbstractEventListener<AddToCartEvent> {
    private static final Logger LOG = Logger.getLogger(MyextensionAddToCartEventListener.class);

    @Override
    protected void onEvent(AddToCartEvent addToCartEvent) {
        LOG.info(addToCartEvent.getUserId() + " added " + addToCartEvent.getProductName() + " to the cart " + addToCartEvent.getCartId());
    }
}
