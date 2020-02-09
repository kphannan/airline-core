package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CashTest
{
    @Test
    public void basicConstructor()
    {
        Cash c = new Cash( "cur", 3, 1234567890 );

        assertEquals( "cur", c.getCurrencyCode() );
        assertEquals( 3, c.getAmount().getDecimalPrecision() );
        assertEquals( 1234567890, c.getAmount().getAmount() );
    }

}

