package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
class MilesPriceTest
{
    @Test
    void basicConstructor()
    {
        MilesPrice c = new MilesPrice( 987_654_321, 5 );       // NOPMD

        assertAll( "Money internal state"
                  ,() -> assertEquals(  "ZZZ", c.getPriceCode()
                                      , "Currency code not as expected" )
                  ,() -> assertEquals(  5, c.getAmount().getDecimalPrecision()
                                      , "amount precision not correct" )
                  ,() -> assertEquals(  987_654_321, c.getAmount().getValue()
                                      , "Value of fixed precision number is incorrect" )
                 );
    }
}


