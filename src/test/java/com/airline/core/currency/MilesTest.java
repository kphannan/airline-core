package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class MilesTest
{
    @Test
    public void basicConstructor()
    {
        Miles c = new Miles( "loy", 3, 1234567890 );

        assertEquals( "loy", c.getCurrencyCode() );
        assertEquals( 3, c.getAmount().getDecimalPrecision() );
        assertEquals( 1234567890, c.getAmount().getAmount() );
    }
}

    
