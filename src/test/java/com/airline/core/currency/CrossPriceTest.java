package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
class CrossPriceTest
{
    @Test
    void verifyCashAndMilesCannotBeCompared()
    {
        final MilesPrice miles = new MilesPrice(       1_234_567_890, 3 );
        final CashPrice  cash  = new CashPrice( "GBP", 1_234_567_890, 3 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { miles.compareTo( cash ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Incompatible price codes ZZZ and GBP" , t.getMessage()
                     , "Incorrect exception message");
    }


    @Test
    void verifyCashAndMilesCanNotBeAdded()
    {
        final MilesPrice miles = new MilesPrice(       1_234_567_890, 3 );
        final CashPrice  cash  = new CashPrice( "GBP", 1_234_567_890, 3 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { miles.add( cash ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Can not add incompatible prices" , t.getMessage()
                     , "Incorrect exception message");
    }

    @Test
    void price_addCash_shouldReturnFalse()
    {
        final CashPrice  cash  = new CashPrice(  "USD", 987_654_321, 5 );        // NOPMD  (DU-anomaly)
        final Price      price = new Price(      "USD", 987_654_321, 5 );        // NOPMD  (DU-anomaly)

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { price.add( cash ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertEquals(  "Can not add incompatible prices" , t.getMessage()
                     , "Incorrect exception message");
    }
}

