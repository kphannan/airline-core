package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class CurrencyNumericCodeTest
{
    @Test
    public void basicConstructor()
    {
        var c = new CurrencyNumericCode( "036" );

        assertEquals( "036", c.getCode(), "Currency code not set by constructor");
    }


    @Test
    public void nullCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( NullPointerException.class
                                   ,() -> {new CurrencyNumericCode( null );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "isoCountryCode is marked non-null but is null", t.getMessage()
                     ,"Incorrect exception message" );
    }

    @Test
    public void blankCurrencyCodeThrowsException()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {new CurrencyNumericCode( "" );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Invalid ISO 4217 currency code ''", t.getMessage()
                     ,"Incorrect exception message" );
    }


}

    
