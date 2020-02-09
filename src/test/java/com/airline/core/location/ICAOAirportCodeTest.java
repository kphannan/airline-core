package com.airline.core.location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
        } );

        assertThat( throwable.getMessage()
                   ,both(containsString( "Invalid ICAO airport code" ) )
                   .and( containsString( "'katl'" )) );
    }


    @Test
    public void testICAOAirportCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class
                                            , () -> {
                                                new ICAOAirportCode( null );
                                            }
                                          );

        assertEquals( "Airport code is required", throwable.getMessage() );
    }


    // ----- Comparable

    @Test
    public void compareSimilarICAOAirportCode()
    {
        ICAOAirportCode edmonton1ICAOAirportCode = new ICAOAirportCode( "CYEG" );
        ICAOAirportCode edmonton2ICAOAirportCode = new ICAOAirportCode( "CYEG" );

        assertEquals( 0, edmonton1ICAOAirportCode.compareTo( edmonton2ICAOAirportCode ) );
    }

    @Test
    public void collationOrderOfICAOAirportCode()
    {
        ICAOAirportCode edmontonICAOAirportCode  = new ICAOAirportCode( "CYEG" );
        ICAOAirportCode atlantaICAOAirportCode   = new ICAOAirportCode( "KATL" );

        assertTrue( edmontonICAOAirportCode.compareTo( atlantaICAOAirportCode ) < 0 );

        assertTrue( atlantaICAOAirportCode.compareTo( edmontonICAOAirportCode ) > 0 );
    }
}
