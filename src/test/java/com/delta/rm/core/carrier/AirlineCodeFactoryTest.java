package com.delta.rm.core.carrier;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class AirlineCodeFactoryTest
{
    @Test
    public void nullAirlineCodeThrowsIllegalArgument()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            AirlineCodeFactory.build( null );
        });

        assertEquals( "Airline code is required", throwable.getMessage() );
    }

    @Test
    public void iataCodeFromTwoCharacterCode()
    {
        AirlineCode airportCode = AirlineCodeFactory.build( "AF" );

        assertEquals( IATAAirlineDesignator.class, airportCode.getClass() );
    }

    @Test
    public void icaoCodeFromThreeCharacterCode()
    {
        AirlineCode airportCode = AirlineCodeFactory.build( "DAL" );

        assertEquals( ICAOAirlineDesignator.class, airportCode.getClass() );
    }
}