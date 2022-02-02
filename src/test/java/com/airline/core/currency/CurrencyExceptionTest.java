package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
class CurrencyExceptionTest
{
    @Test
    void defaultConstructor()
    {
        CurrencyException cex = new CurrencyException();          // NOPMD

        assertAll( "CurrencyException internal state"
                  ,() -> assertNull( cex.getMessage()
                                    ,"Exception message not as expected" )
                  ,() -> assertNull( cex.getCause()
                                    ,"Exception message not as expected" )
                );
    }




    @Test
    void constructorWithCause()
    {
        Throwable           t = new Throwable( "Test message" );
        CurrencyException cex = new CurrencyException(t);          // NOPMD

        assertAll( "CurrencyException internal state"
                  ,() -> assertNull( cex.getMessage()
                                    ,"Exception message not as expected" )
                  ,() -> assertNotNull( cex.getCause()
                                       ,"Exception message not as expected" )
                  ,() -> assertEquals( "Test message", cex.getCause().getMessage()
                                      ,"Exception (throwable) message not as expected" )
                );
    }

}

