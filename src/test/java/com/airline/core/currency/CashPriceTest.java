package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class CashPriceTest
{
    @Test
    public void basicConstructor()
    {
        CashPrice c = new CashPrice( "cur", 3, 1234567890 );

        assertEquals( "cur", c.getPriceCode() );
        assertEquals( 3, c.getDecimalPrecision() );
        assertEquals( 1234567890, c.getAmount() );
    }

}

