package com.delta.rm.core.location;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ICAOAirportCodeTest
{

    @Test
    public void testAltantaICAOAirportCode()
    {
        ICAOAirportCode atlantaICAOAirportCode = new ICAOAirportCode( "KATL" );

        assertNotNull( atlantaICAOAirportCode );
        assertEquals( "KATL", atlantaICAOAirportCode.getAirportCode() );
    }

    @Test
    public void testEdmontonICAOAirportCode()
    {
        ICAOAirportCode edmontonICAOAirportCode = new ICAOAirportCode( "CYEG" );

        assertNotNull( edmontonICAOAirportCode );
        assertEquals( "CYEG", edmontonICAOAirportCode.getAirportCode() );
    }

    @Test
    public void testAltantaICAOAirportCodeLowercase()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new ICAOAirportCode( "katl" );
        });

        assertTrue( throwable.getMessage().contains("Invalid ICAO airport code")
                   ,"Exception message contains Invalid ICAO airport code" );

        assertTrue( throwable.getMessage().contains("Invalid ICAO airport code")
                   ,"'katl'" );

    }


    @Test
    public void testICAOAirportCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new ICAOAirportCode( null );
        });

        assertEquals( "Airport Code can not be null", throwable.getMessage() );
    }


}
