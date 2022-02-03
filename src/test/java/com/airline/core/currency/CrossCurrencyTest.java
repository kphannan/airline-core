package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


@SuppressWarnings( { "PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals" } )
class CrossCurrencyTest
{
    @Test
    void verifyCashAndMilesCannotBeCompared()
    {
        final Miles miles = new Miles(       1_234_567_890, 3 );
        final Cash  cash  = new Cash( "GBP", 1_234_567_890, 3 );

        Throwable t = assertThrows(  CurrencyException.class
                                   , () -> { miles.compareTo( cash ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertEquals(  "Incompatible currencies ZZZ and GBP" , t.getMessage()
                     , "Incorrect exception message");
    }


    @Test
    void verifyCashAndMilesCanNotBeAdded()
    {
        final Miles miles = new Miles(       1_234_567_890, 3 );
        final Cash  cash  = new Cash( "GBP", 1_234_567_890, 3 );

        Throwable t = assertThrows(  CurrencyException.class
                                   , () -> { miles.add( cash ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertEquals(  "Can not add incompatible currencies" , t.getMessage()
                     , "Incorrect exception message");
    }


    @Test
    void miles_equalsWithMoney_shouldReturnFalse()
    {
        final Miles miles = new Miles(        987_654_321, 5 );        // NOPMD  (DU-anomaly)
        final Money money = new Money( "USD", 987_654_321, 5 );        // NOPMD  (DU-anomaly)

        assertFalse( miles.equals( money ) );
    }

    @Test
    void money_equalsWithMiles_shouldReturnFalse()
    {
        final Miles miles = new Miles(        987_654_321, 5 );        // NOPMD  (DU-anomaly)
        final Money money = new Money( "USD", 987_654_321, 5 );        // NOPMD  (DU-anomaly)

        assertFalse( money.equals( miles ) );
    }

    @Test
    void money_addMiles_shouldReturnFalse()
    {
        final Miles miles = new Miles(        987_654_321, 5 );        // NOPMD  (DU-anomaly)
        final Money money = new Money( "USD", 987_654_321, 5 );        // NOPMD  (DU-anomaly)

        Throwable t = assertThrows(  CurrencyException.class
                                   , () -> { money.add( miles ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertEquals(  "Can not add incompatible currencies" , t.getMessage()
                     , "Incorrect exception message");
        // final Money result = money.add( miles );
        // assertFalse( result.hashCode() == 0 );
    }

    @Test
    void money_addCash_shouldReturnFalse()
    {
        final Cash  cash  = new Cash(  "USD", 987_654_321, 5 );        // NOPMD  (DU-anomaly)
        final Money money = new Money( "USD", 987_654_321, 5 );        // NOPMD  (DU-anomaly)

        Throwable t = assertThrows(  CurrencyException.class
                                   , () -> { money.add( cash ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertEquals(  "Can not add incompatible currencies" , t.getMessage()
                     , "Incorrect exception message");
    }

}

