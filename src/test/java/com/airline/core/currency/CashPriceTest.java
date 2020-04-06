package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class CashPriceTest
{
    @Test
    public void basicConstructor()
    {
        CashPrice c = new CashPrice( "cur", 3, 1234567890 );        // NOPMD

        assertAll( "CashPrice internal state"
                  ,() -> assertEquals( "cur", c.getPriceCode()
                                      ,"Currency code not as expected" )
                  ,() -> assertEquals( 3, c.getAmount().getDecimalPrecision()
                                      ,"amount precision not correct" )
                  ,() -> assertEquals( 1234567890, c.getAmount().getValue()
                                      ,"Value of fixed precision number is incorrect" )
                );
    }

}

