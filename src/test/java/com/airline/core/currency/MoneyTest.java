package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


@SuppressWarnings( {"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"} )
class MoneyTest
{
    @Test
    void basicConstructorWithStringCurrencyCode()
    {
        final Money c = new Money( "CUR", 1_234_567_890, 3 );            // NOPMD  (DU-anomaly)

        assertAll( "Money internal state"
                  ,() -> assertEquals(  "CUR", c.getCurrencyCode().getCode()
                                      , "Currency code not as expected" )
                  ,() -> assertEquals(  3, c.getAmount().getDecimalPrecision()
                                      , "amount precision not correct" )
                  ,() -> assertEquals(  1234567890, c.getAmount().getValue()
                                      , "Value of fixed precision number is incorrect" )
        );
    }

    @Test
    void basicConstructorAlternate()
    {
        final Money c = new Money( "FOO", 4_321, 2 );          // NOPMD  (DU-anomaly)

        assertAll(  "Money internal state"
                  , () -> assertEquals(  "FOO", c.getCurrencyCode().getCode()
                                       , "Currency code not as expected" )
                  , () -> assertEquals(  2, c.getAmount().getDecimalPrecision()
                                       , "amount precision not correct" )
                  , () -> assertEquals(  4321, c.getAmount().getValue()
                                       , "Value of fixed precision number is incorrect" )
        );
    }

    @Test
    void basicConstructorWithCurrencyCodeInstance()
    {
        final var currencyCode = new CurrencyAlphaCode( "GBP" );
        final Money c = new Money( currencyCode, 1_234_567_890, 3 );         // NOPMD  (DU-anomaly)

        assertAll(  "Money internal state"
                  , () -> assertEquals(  currencyCode, c.getCurrencyCode()
                                       , "Currency code not as expected" )
                  , () -> assertEquals(  3, c.getAmount().getDecimalPrecision()
                                       , "amount precision not correct" )
                  , () -> assertEquals(  1234567890, c.getAmount().getValue()
                                       , "Value of fixed precision number is incorrect" )
        );
    }

    @Test
    void nullCurrencyCodeNotAllowed()
    {
        final Throwable t = assertThrows(  IllegalArgumentException.class
                                         , () -> { new Money( (String)null, 1_234_567_890, 3 ); }
                                         , "IllegalArgumentException not thrown when expected"
                                        );

        assertEquals(  "Currency code is required", t.getMessage()
                     , "Exception message text is incorrect" );
    }

    @Test
    void blankCurrencyCodeNotAllowed()
    {
        final Throwable t = assertThrows(  IllegalArgumentException.class
                                         , () -> { new Money( "", 1_234_567_890, 3 ); }
                                         , "IllegalArgumentException not thrown when expected"
                                        );

        assertEquals(  "Invalid ISO 4217 currency code ''", t.getMessage()
                     , "Exception message text is incorrect" );
    }

    @Test
    void nullCurrencyAlphaCodeNotAllowed()
    {
        final Throwable t = assertThrows(  IllegalArgumentException.class
                                         , () -> { new Money( (CurrencyAlphaCode)null, 1_234_567_890, 3 ); }
                                         , "IllegalArgumentException not thrown when expected"
                                        );

        assertEquals(  "Currency code is required", t.getMessage()
                     , "Exception message text is incorrect" );
    }

    // ----- Addition -----
    @Test
    void addPositiveValue()
    {
        final Money             total        = new Money( "CUR", 1_234_567_890, 3 );
        final Money             addend       = new Money( "CUR", 5, 3 );
        final CurrencyAlphaCode currencyCode = new CurrencyAlphaCode( "CUR" );          // NOPMD  (DU-anomaly)

        final Money result = total.add( addend );

        assertAll(  "Money internal state"
                  , () -> assertEquals(  currencyCode, total.getCurrencyCode()
                                       , "curency code is wrong" )
                  , () -> assertEquals(  "CUR", total.getCurrencyCode().getCode()
                                       , "curency code string is wrong" )
                  , () -> assertEquals(  3, total.getAmount().getDecimalPrecision()
                                       , "precision incorrectly modified" )
                  , () -> assertEquals(  1234567895, total.getAmount().getValue()
                                       , "result of addition is incorrect" )
                  , () -> assertSame( result, total )
        );
    }

    @Test
    void addNullValue()
    {
        final Money total  = new Money( "CUR", 1_234_567_890, 3 );

        final Throwable t = assertThrows(  IllegalArgumentException.class
                                         , () -> { total.add( null ); }
                                         , "IllegalArgumentException not thrown when expected"
                                        );

        assertEquals(  "Incompatible currency (null)", t.getMessage()
                     , "Exception message text is incorrect" );
    }

    @Test
    void addAddIncompatibleValue()
    {
        final Money total   = new Money( "CUR", 1_234_567_890, 3 );
        final Money addend  = new Money( "CUR", 5, 2 );


        final Throwable t = assertThrows(  ArithmeticException.class
                                         , () -> { total.add( addend ); }
                                         , "ArithmeticException not thrown when expected"
                                        );

        assertEquals(  "Precision mismatch 3 != 2", t.getMessage()
                     , "Exception message text is incorrect" );
    }


    @Test
    void addAddIncompatibleCurrencies()
    {
        final Money total   = new Money( "CUR", 1_234_567_890, 3 );
        final Money addend  = new Money( "RUC", 5, 3 );


        Throwable t = assertThrows(  CurrencyException.class
                                   , () -> { total.add( addend ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertTrue(  t.getMessage().contains( "Can not add incompatible currencies" )
                   , "Exception message text is incorrect" );
    }



    // ----- Subtraction -----
    @Test
    void subtractPositiveValue()
    {
        final Money total  = new Money( "CUR", 1_234_567_890, 3 );
        final Money addend = new Money( "CUR", 5, 3 );

        final Money result = total.subtract( addend );

        assertAll(  "Money internal state - post subtraction"
                  , () -> assertEquals(  "CUR", total.getCurrencyCode().getCode()
                                       , "currency code altered" )
                  , () -> assertEquals(  3, total.getAmount().getDecimalPrecision()
                                       , "precision not preserved through addition" )
                  , () -> assertEquals(  1234567885, total.getAmount().getValue()
                                       , "value from addition is incorrect" )
                  , () -> assertSame( result, total )
                                       );
    }

    @Test
    void addSubtractIncompatibleValue()
    {
        final Money total   = new Money( "CUR", 1_234_567_890, 3 );
        final Money subtend = new Money( "CUR", 5, 2 );


        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { total.subtract( subtend ); }
                                   , "ArithmeticException not thrown when expected"
                                  );

        assertEquals(  "Precision mismatch 3 != 2", t.getMessage()
                     , "Exception message text is incorrect" );
    }


    @Test
    void addSubtractIncompatibleCurrencies()
    {
        final Money total   = new Money( "CUR", 2, 1234567890 );
        final Money subtend = new Money( "RUC", 5, 2 );


        final Throwable t = assertThrows(  CurrencyException.class
                                         , () -> { total.subtract( subtend ); }
                                         , "CurrencyException not thrown when expected"
                                        );

        assertEquals(  "Can not subtract incompatible currencies", t.getMessage()
                     , "Exception message text is incorrect" );
    }


    // ----- Comparison -----
    @Test
    void compareEquivalentValues()
    {
        final Money valueA  = new Money( "CUR", 1_234_567_890, 3 );
        final Money valueB  = new Money( "CUR", 1_234_567_890, 3 );

        assertEquals( 0, valueA.compareTo( valueB ), "numerically equal instances should compare as equivalent" );
    }

    @Test
    void compareSameInstance()
    {
        final Money total  = new Money( "CUR", 1_234_567_890, 3 );

        assertEquals( 0, total.compareTo( total ), "object compared to self should be zero");
    }

    @Test
    void compareAgainstNullFails()
    {
        final Money total  = new Money( "CUR", 1_234_567_890, 3 );

        final Throwable t = assertThrows(  IllegalArgumentException.class
                                         , () -> { total.compareTo( null ); }
                                         , "IllegalArgumentException not thrown when expected"
                                        );

        assertTrue(  t.getMessage().contains( "Can not compare against (null) currency" )
                   , "Exception message text is incorrect" );
    }

    @Test
    void compareDissimilarPrecision()
    {
        final Money valueA  = new Money( "CUR", 1_234_567_890, 4 );
        final Money valueB  = new Money( "CUR", 1_234_567_890, 3 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { valueA.compareTo( valueB ); }
                                   , "ArithmeticException not thrown when expected"
                                  );

        assertEquals(  "Precision mismatch 4 != 3", t.getMessage()
                     , "Exception message text is incorrect" );
    }

    @Test
    void compareDissimilarCurrencies()
    {
        final Money valueA  = new Money( "CUR", 1_234_567_890, 3 );
        final Money valueB  = new Money( "RUC", 1_234_567_890, 3 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows(  CurrencyException.class
                                   , () -> { valueA.compareTo( valueB ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertEquals(  "Incompatible currencies CUR and RUC", t.getMessage()
                     , "Exception message text is incorrect" );
    }

    @Test
    void compareAgainstSmallerValue()
    {
        final Money valueA  = new Money( "CUR", 1_234_567_890, 3 );
        final Money valueB  = new Money( "CUR", 1_111_111_111, 3 );

        assertTrue( 0 < valueA.compareTo( valueB ), "A should be greater than B" );
    }

    @Test
    void compareAgainstLargerValue()
    {
        Money valueA  = new Money( "CUR", 123_456_789, 3 );
        Money valueB  = new Money( "CUR", 222_222_222, 3 );

        assertTrue( 0 > valueA.compareTo( valueB ), "A should be less than B" );
    }

    @Test
    void money_equalsSimlarValue_returnTrue()
    {
        final Money valueA  = new Money( "CUR", 1_234_567_890, 3 );
        final Money valueB  = new Money( "CUR", 1_234_567_890, 3 );

        assertTrue( valueA.equals( valueB ), "numerically equal instances should compare as equivalent" );
    }


}