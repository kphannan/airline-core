package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
class CurrencyAlphaCodeTest
{
    @Test
    void basicConstructor()
    {
        CurrencyAlphaCode c = new CurrencyAlphaCode( "ABC" );

        assertEquals( "ABC", c.getCode(), "Currency code not set via constructor");
    }


    @Test
    void nullCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> { new CurrencyAlphaCode( null ); }
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Currency code is required", t.getMessage()
                     ,"Incorrect exception message" );
    }

    @Test
    void blankCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {new CurrencyAlphaCode( "" );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Invalid ISO 4217 currency code ''", t.getMessage()
                     ,"Incorrect exception message");
    }


}


