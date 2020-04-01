package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class CashTest
{
    @Test
    public void basicConstructor()
    {
        Cash c = new Cash( "XYZ", 3, 1234567890 );          // NOPMD

        assertAll( "Cash internal state"
                  ,() -> assertEquals( "XYZ", c.getCurrencyCode().getCode()
                                      ,"Currency code not as expected" )
                  ,() -> assertEquals( 3, c.getAmount().getDecimalPrecision()
                                      ,"amount precision not correct" )
                  ,() -> assertEquals( 1234567890, c.getAmount().getValue()
                                      ,"Value of fixed precision number is incorrect" )
                );
    }

}

