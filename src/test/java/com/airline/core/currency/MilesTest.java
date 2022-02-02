package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class MilesTest
{
    @Test
    @SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
    void basicConstructor()
    {
        Miles c = new Miles( 987_654_321, 5 );        // NOPMD  (DU-anomaly)

        assertAll( "Money internal state"
                  ,() -> assertEquals( "ZZZ", c.getCurrencyCode().getCode()
                                      ,"Currency code not as expected" )
                  ,() -> assertEquals( 5, c.getAmount().getDecimalPrecision()
                                      ,"amount precision not correct" )
                  ,() -> assertEquals( 987654321, c.getAmount().getValue()
                                      ,"Value of fixed precision number is incorrect" )
                 );
    }

}


