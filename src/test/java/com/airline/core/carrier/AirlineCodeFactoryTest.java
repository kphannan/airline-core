package com.airline.core.carrier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class AirlineCodeFactoryTest
{
    @Test
    public void nullAirlineCodeThrowsIllegalArgument()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class
                                           ,() -> {
                                               AirlineCodeFactory.build( null );
                                            });

        assertEquals( "Airline code is required", throwable.getMessage(), "Message is not as expected" );
    }

    @Test
    public void iataCodeFromTwoCharacterCode()
    {
        AirlineCode airlineCode = AirlineCodeFactory.build( "AF" );

        assertEquals( IATAAirlineDesignator.class, airlineCode.getClass(), ".class mismatch" );
    }

    @Test
    public void icaoCodeFromThreeCharacterCode()
    {
        AirlineCode airlineCode = AirlineCodeFactory.build( "DAL" );

        assertEquals( ICAOAirlineDesignator.class, airlineCode.getClass(), "class mismatch" );
    }
}