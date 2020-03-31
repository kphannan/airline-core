package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class MoneyTest
{
    @Test
    public void basicConstructorWithStringCurrencyCode()
    {
        Money c = new Money( "CUR", 3, 1234567890 );

        assertEquals( "CUR", c.getCurrencyCode().getCode() );
        assertEquals( 3, c.getAmount().getDecimalPrecision() );
        assertEquals( 1234567890, c.getAmount().getAmount() );
    }

    @Test
    public void basicConstructorAlternate()
    {
        Money c = new Money( "FOO", 2, 4321 );

        assertEquals( "FOO", c.getCurrencyCode().getCode() );
        assertEquals( 2, c.getAmount().getDecimalPrecision() );
        assertEquals( 4321, c.getAmount().getAmount() );
    }

    @Test
    public void basicConstructorWithCurrencyCodeInstance()
    {
        var currencyCode = new CurrencyAlphaCode( "GBP" );
        Money c = new Money( currencyCode, 3, 1234567890 );

        assertEquals( currencyCode, c.getCurrencyCode() );
        assertEquals( 3, c.getAmount().getDecimalPrecision() );
        assertEquals( 1234567890, c.getAmount().getAmount() );
    }

    @Test
    public void nullCurrencyCodeNotAllowed()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    new Money( (String)null, 3, 1234567890 );
                                   });

        assertEquals( "Currency code is required", t.getMessage());
    }

    @Test
    public void blankCurrencyCodeNotAllowed()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    new Money( "", 3, 1234567890 );
                                   });

        assertEquals( "Currency code is required", t.getMessage());
    }

    @Test
    public void nullCurrencyAlphaCodeNotAllowed()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    new Money( (CurrencyAlphaCode)null, 3, 1234567890 );
                                   });

        assertEquals( "Currency code is required", t.getMessage());
    }

    // ----- Addition -----
    @Test
    public void addPositiveValue()
    {
        Money             total        = new Money( "CUR", 3, 1234567890 );
        Money             addend       = new Money( "CUR", 3, 5 );
        CurrencyAlphaCode currencyCode = new CurrencyAlphaCode("CUR");

        total.add( addend );

        assertEquals( currencyCode, total.getCurrencyCode() );
        assertEquals( "CUR", total.getCurrencyCode().getCode() );
        assertEquals( 3, total.getAmount().getDecimalPrecision() );
        assertEquals( 1234567895, total.getAmount().getAmount() );
    }

    @Test
    public void addNullValue()
    {
        Money total  = new Money( "CUR", 3, 1234567890 );
        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.add( null );
                                   });

        assertEquals( "Incompatible currency (null)", t.getMessage());
    }

    @Test
    public void addAddIncompatibleValue()
    {
        Money total   = new Money( "CUR", 3, 1234567890 );
        Money addend  = new Money( "CUR", 2, 5 );

        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.add( addend );
                                   });

        assertTrue( t.getMessage().contains("Can not add incompatible currencies") );
    }



    // ----- Subtraction -----
    @Test
    public void subtractPositiveValue()
    {
        Money total  = new Money( "CUR", 3, 1234567890 );
        Money addend = new Money( "CUR", 3, 5 );

        total.subtract( addend );

        assertEquals( "CUR", total.getCurrencyCode().getCode() );
        assertEquals( 3, total.getAmount().getDecimalPrecision() );
        assertEquals( 1234567885, total.getAmount().getAmount() );
    }

    @Test
    public void addSubtractIncompatibleValue()
    {
        Money total   = new Money( "CUR", 3, 1234567890 );
        Money subtend = new Money( "CUR", 2, 5 );

        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.subtract( subtend );
                                   });

        assertEquals( "Can not subtract incompatible currencies", t.getMessage() );
    }


    // ----- Comparison -----
    @Test
    public void compareEquivalentValues()
    {
        Money valueA  = new Money( "CUR", 3, 1234567890 );
        Money valueB  = new Money( "CUR", 3, 1234567890 );

        assertEquals( 0, valueA.compareTo( valueB ));
    }

    @Test
    public void compareSameInstance()
    {
        Money total  = new Money( "CUR", 3, 1234567890 );

        assertEquals( 0, total.compareTo( total ));
    }

    @Test
    public void compareAgainstNullFails()
    {
        Money total  = new Money( "CUR", 3, 1234567890 );

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.compareTo( null );
                                   });

        assertTrue( t.getMessage().contains("Can not compare against (null) currency") );
    }

    @Test
    public void compareDissimilarPrecision()
    {
        Money valueA  = new Money( "CUR", 4, 1234567890 );
        Money valueB  = new Money( "CUR", 3, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows( ArithmeticException.class
                                   ,() -> {
                                      valueA.compareTo( valueB );
                                   });

        assertEquals( "Precision mismatch 4 != 3", t.getMessage() );
    }

    @Test
    public void compareDissimilarCurrencies()
    {
        Money valueA  = new Money( "CUR", 3, 1234567890 );
        Money valueB  = new Money( "RUC", 3, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      valueA.compareTo( valueB );
                                   });

        assertEquals( "Incompatible currencies CurrencyCode(code=CUR) and CurrencyCode(code=RUC)", t.getMessage() );
    }

    

    @Test
    public void compareAgainstSmallerValue()
    {
        Money valueA  = new Money( "CUR", 3, 1234567890 );
        Money valueB  = new Money( "CUR", 3, 1111111111 );

        assertTrue( valueA.compareTo( valueB ) > 0 );
    }


    @Test
    public void compareAgainstLargerValue()
    {
        Money valueA  = new Money( "CUR", 3, 123456789 );
        Money valueB  = new Money( "CUR", 3, 222222222 );

        assertTrue( valueA.compareTo( valueB ) < 0 );
    }

}