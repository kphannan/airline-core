package com.delta.rm.core.location;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertThat( throwable.getMessage()
                   ,both(containsString("Invalid ICAO airport code"))
                   .and( containsString( "'katl'")) );
    }


    @Test
    public void testICAOAirportCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new ICAOAirportCode( null );
        });

        assertEquals( "Airport code is required", throwable.getMessage() );
    }


}
