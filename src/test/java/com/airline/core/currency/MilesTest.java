package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class MilesTest
{
    @Test
    @SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
    public void basicConstructor()
    {
        Miles c = new Miles( "LOY", 5, 987654321 );        // NOPMD  (DU-anomaly)

        assertAll( "Money internal state"
                  ,() -> assertEquals( "LOY", c.getCurrencyCode().getCode()
                                      ,"Currency code not as expected" )
                  ,() -> assertEquals( 5, c.getAmount().getDecimalPrecision()
                                      ,"amount precision not correct" )
                  ,() -> assertEquals( 987654321, c.getAmount().getValue()
                                      ,"Value of fixed precision number is incorrect" )
                 );
    }
}

    
