package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class CurrencyNumericCodeTest
{
    @Test
    public void basicConstructor()
    {
        var c = new CurrencyNumericCode( "036" );

        assertEquals( "036", c.getCode() );
    }


    @Test
    public void nullCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( NullPointerException.class
                                   ,() -> {
                                    new CurrencyNumericCode( null );
                                   });

        assertEquals( "isoCountryCode is marked non-null but is null", t.getMessage());
    }

    @Test
    public void blankCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    new CurrencyNumericCode( "" );
                                   });

        assertEquals( "Invalid ISO 4217 currency code ''", t.getMessage());
    }


}

    
