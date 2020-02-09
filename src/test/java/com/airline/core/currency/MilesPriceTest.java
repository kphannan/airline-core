package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class MilesPriceTest
{
    @Test
    public void basicConstructor()
    {
        MilesPrice c = new MilesPrice( "loy", 3, 1234567890 );

        assertEquals( "loy", c.getPriceCode() );
        assertEquals( 3, c.getAmount().getDecimalPrecision() );
        assertEquals( 1234567890, c.getAmount().getAmount() );
    }
}

    
