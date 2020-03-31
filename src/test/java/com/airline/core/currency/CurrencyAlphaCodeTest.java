package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class CurrencyAlphaCodeTest
{
    @Test
    public void basicConstructor()
    {
        CurrencyAlphaCode c = new CurrencyAlphaCode( "ABC" );

        assertEquals( "ABC", c.getCode() );
    }


    @Test
    public void nullCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( NullPointerException.class
                                   ,() -> {
                                    new CurrencyAlphaCode( null );
                                   });

        assertEquals( "isoCountryCode is marked non-null but is null", t.getMessage());
    }

    @Test
    public void blankCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    new CurrencyAlphaCode( "" );
                                   });

        assertEquals( "Invalid ISO 4217 currency code ''", t.getMessage());
    }


}

    
