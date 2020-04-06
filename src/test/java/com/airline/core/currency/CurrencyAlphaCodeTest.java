package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class CurrencyAlphaCodeTest
{
    @Test
    public void basicConstructor()
    {
        CurrencyAlphaCode c = new CurrencyAlphaCode( "ABC" );

        assertEquals( "ABC", c.getCode(), "Currency code not set via constructor");
    }


    @Test
    public void nullCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( NullPointerException.class
                                   ,() -> {new CurrencyAlphaCode( null );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "isoCountryCode is marked non-null but is null", t.getMessage()
                     ,"Incorrect exception message" );
    }

    @Test
    public void blankCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {new CurrencyAlphaCode( "" );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Invalid ISO 4217 currency code ''", t.getMessage()
                     ,"Incorrect exception message");
    }


}

    
