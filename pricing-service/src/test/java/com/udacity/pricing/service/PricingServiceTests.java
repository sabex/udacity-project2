package com.udacity.pricing.service;

import com.udacity.pricing.domain.price.Price;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PricingServiceTests {

    private PricingService service = new PricingService(); // not mocked because this is the service under test

    @Test
    public void priceIsGeneratedProperly() throws PriceException{
        Price result = service.getPrice(1L);
        assertNotNull(result);
        assertTrue(result.getCurrency().equalsIgnoreCase("USD"));
        assertNotNull(result.getPrice());
        assertTrue(result.getPrice().intValue() > 0);
    }

    @Test
    public void priceErrorThrownProperly() {
        assertThrows (PriceException.class,
                () -> service.getPrice(55L)); // should fail since only 1-20 initialized
    }
}
