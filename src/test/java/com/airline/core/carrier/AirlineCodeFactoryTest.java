package com.airline.core.carrier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class AirlineCodeFactoryTest
{
    @Test
    public void nullAirlineCodeThrowsIllegalArgument()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class
                                           ,() -> {
                                               AirlineCodeFactory.build( null );
                                            });

        assertEquals( "Airline code is required", throwable.getMessage() );
    }

    @Test
    public void iataCodeFromTwoCharacterCode()
    {
        AirlineCode airlineCode = AirlineCodeFactory.build( "AF" );

        assertEquals( IATAAirlineDesignator.class, airlineCode.getClass() );
    }

    @Test
    public void icaoCodeFromThreeCharacterCode()
    {
        AirlineCode airlineCode = AirlineCodeFactory.build( "DAL" );

        assertEquals( ICAOAirlineDesignator.class, airlineCode.getClass() );
    }
}