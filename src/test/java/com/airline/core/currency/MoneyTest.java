package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


@SuppressWarnings( {"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"} )
public class MoneyTest
{
    @Test
    public void basicConstructorWithStringCurrencyCode()
    {
        Money c = new Money( "CUR", 3, 1234567890 );            // NOPMD  (DU-anomaly)

        assertAll( "Money internal state"
                  ,() -> assertEquals( "CUR", c.getCurrencyCode().getCode()
                                      ,"Currency code not as expected" )
                  ,() -> assertEquals( 3, c.getAmount().getDecimalPrecision()
                                      ,"amount precision not correct" )
                  ,() -> assertEquals( 1234567890, c.getAmount().getValue()
                                      ,"Value of fixed precision number is incorrect" )
        );
    }

    @Test
    public void basicConstructorAlternate()
    {
        Money c = new Money( "FOO", 2, 4321 );          // NOPMD  (DU-anomaly)

        assertAll( "Money internal state"
                  ,() -> assertEquals( "FOO", c.getCurrencyCode().getCode()
                                      ,"Currency code not as expected" )
                  ,() -> assertEquals( 2, c.getAmount().getDecimalPrecision()
                                      ,"amount precision not correct" )
                  ,() -> assertEquals( 4321, c.getAmount().getValue()
                                      ,"Value of fixed precision number is incorrect" )
        );
    }

    @Test
    public void basicConstructorWithCurrencyCodeInstance()
    {
        var currencyCode = new CurrencyAlphaCode( "GBP" );
        Money c = new Money( currencyCode, 3, 1234567890 );         // NOPMD  (DU-anomaly)

        assertAll( "Money internal state"
                  ,() -> assertEquals( currencyCode, c.getCurrencyCode()
                                      ,"Currency code not as expected" )
                  ,() -> assertEquals( 3, c.getAmount().getDecimalPrecision()
                                      ,"amount precision not correct" )
                  ,() -> assertEquals( 1234567890, c.getAmount().getValue()
                                      ,"Value of fixed precision number is incorrect" )
        );
    }

    @Test
    public void nullCurrencyCodeNotAllowed()
    {
        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> {new Money( (String)null, 3, 1234567890 );}
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Currency code is required", t.getMessage()
                     ,"Exception message text is incorrect" );
    }

    @Test
    public void blankCurrencyCodeNotAllowed()
    {
        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> {new Money( "", 3, 1234567890 );}
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Currency code is required", t.getMessage()
                     , "Exception message text is incorrect" );
    }

    @Test
    public void nullCurrencyAlphaCodeNotAllowed()
    {
        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> {new Money( (CurrencyAlphaCode)null, 3, 1234567890 );}
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Currency code is required", t.getMessage()
                     , "Exception message text is incorrect" );
    }

    // ----- Addition -----
    @Test
    public void addPositiveValue()
    {
        Money             total        = new Money( "CUR", 3, 1234567890 );
        Money             addend       = new Money( "CUR", 3, 5 );
        CurrencyAlphaCode currencyCode = new CurrencyAlphaCode("CUR");          // NOPMD  (DU-anomaly)

        total.add( addend );

        assertAll( "Money internal state"
                  ,() -> assertEquals(  currencyCode, total.getCurrencyCode()
                                      , "curency code is wrong" )
                  ,() -> assertEquals(  "CUR", total.getCurrencyCode().getCode()
                                      , "curency code string is wrong" )
                  ,() -> assertEquals(  3, total.getAmount().getDecimalPrecision()
                                      , "precision incorrectly modified" )
                  ,() -> assertEquals(  1234567895, total.getAmount().getValue()
                                        ,"result of addition is incorrect" )
        );
    }

    @Test
    public void addNullValue()
    {
        Money total  = new Money( "CUR", 3, 1234567890 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.add( null ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Incompatible currency (null)", t.getMessage()
                     ,"Exception message text is incorrect" );
    }

    @Test
    public void addAddIncompatibleValue()
    {
        Money total   = new Money( "CUR", 3, 1234567890 );
        Money addend  = new Money( "CUR", 2, 5 );


        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { total.add( addend ); }
                                   , "ArithmeticException not thrown when expected"
                                  );

        assertEquals( "Precision mismatch 3 != 2", t.getMessage()
                     ,"Exception message text is incorrect" );
    }


    @Test
    public void addAddIncompatibleCurrencies()
    {
        Money total   = new Money( "CUR", 3, 1234567890 );
        Money addend  = new Money( "RUC", 3, 5 );


        Throwable t = assertThrows(  CurrencyException.class
                                   , () -> { total.add( addend ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertTrue( t.getMessage().contains("Can not add incompatible currencies")
                   ,"Exception message text is incorrect" );
    }



    // ----- Subtraction -----
    @Test
    public void subtractPositiveValue()
    {
        Money total  = new Money( "CUR", 3, 1234567890 );
        Money addend = new Money( "CUR", 3, 5 );

        total.subtract( addend );

        assertAll(  "Money internal state - post subtraction"
                  , () -> assertEquals(  "CUR", total.getCurrencyCode().getCode()
                                       , "currency code altered" )
                  , () -> assertEquals(  3, total.getAmount().getDecimalPrecision()
                                       , "precision not preserved through addition" )
                  , () -> assertEquals(  1234567885, total.getAmount().getValue()
                                       , "value from addition is incorrect" )
                 );
    }

    @Test
    public void addSubtractIncompatibleValue()
    {
        Money total   = new Money( "CUR", 3, 1234567890 );
        Money subtend = new Money( "CUR", 2, 5 );


        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { total.subtract( subtend ); }
                                   , "ArithmeticException not thrown when expected"
                                  );

        assertEquals( "Precision mismatch 3 != 2", t.getMessage()
                     ,"Exception message text is incorrect" );
    }


    @Test
    public void addSubtractIncompatibleCurrencies()
    {
        Money total   = new Money( "CUR", 2, 1234567890 );
        Money subtend = new Money( "RUC", 2, 5 );


        Throwable t = assertThrows(  CurrencyException.class
                                   , () -> { total.subtract( subtend ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertEquals( "Can not subtract incompatible currencies", t.getMessage()
                     ,"Exception message text is incorrect" );
    }


    // ----- Comparison -----
    @Test
    public void compareEquivalentValues()
    {
        Money valueA  = new Money( "CUR", 3, 1234567890 );
        Money valueB  = new Money( "CUR", 3, 1234567890 );

        assertEquals( 0, valueA.compareTo( valueB ), "numerically equal instances should compare as equivalent");
    }

    @Test
    public void compareSameInstance()
    {
        Money total  = new Money( "CUR", 3, 1234567890 );

        assertEquals( 0, total.compareTo( total ), "object compared to self should be zero");
    }

    @Test
    public void compareAgainstNullFails()
    {
        Money total  = new Money( "CUR", 3, 1234567890 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.compareTo( null ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertTrue(  t.getMessage().contains( "Can not compare against (null) currency" )
                   , "Exception message text is incorrect" );
    }

    @Test
    public void compareDissimilarPrecision()
    {
        Money valueA  = new Money( "CUR", 4, 1234567890 );
        Money valueB  = new Money( "CUR", 3, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { valueA.compareTo( valueB ); }
                                   , "ArithmeticException not thrown when expected"
                                  );

        assertEquals( "Precision mismatch 4 != 3", t.getMessage()
                     ,"Exception message text is incorrect" );
    }

    @Test
    public void compareDissimilarCurrencies()
    {
        Money valueA  = new Money( "CUR", 3, 1234567890 );
        Money valueB  = new Money( "RUC", 3, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows(  CurrencyException.class
                                   , () -> { valueA.compareTo( valueB ); }
                                   , "CurrencyException not thrown when expected"
                                  );

        assertEquals( "Incompatible currencies CUR and RUC", t.getMessage()
                     ,"Exception message text is incorrect" );
    }



    @Test
    public void compareAgainstSmallerValue()
    {
        Money valueA  = new Money( "CUR", 3, 1234567890 );
        Money valueB  = new Money( "CUR", 3, 1111111111 );

        assertTrue( 0 < valueA.compareTo( valueB ), "A should be greater than B" );
    }


    @Test
    public void compareAgainstLargerValue()
    {
        Money valueA  = new Money( "CUR", 3, 123456789 );
        Money valueB  = new Money( "CUR", 3, 222222222 );

        assertTrue( 0 > valueA.compareTo( valueB ), "A should be less than B" );
    }

}