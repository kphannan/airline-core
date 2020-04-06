package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class MilesPriceTest
{
    @Test
    public void basicConstructor()
    {
        MilesPrice c = new MilesPrice( "loy", 5, 987654321 );       // NOPMD

        assertAll( "Money internal state"
                  ,() -> assertEquals( "loy", c.getPriceCode()
                                      ,"Currency code not as expected" )
                  ,() -> assertEquals( 5, c.getAmount().getDecimalPrecision()
                                      ,"amount precision not correct" )
                  ,() -> assertEquals( 987654321, c.getAmount().getValue()
                                      ,"Value of fixed precision number is incorrect" )
                 );
    }
}

    
