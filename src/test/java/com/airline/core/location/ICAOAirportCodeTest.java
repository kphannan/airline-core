package com.airline.core.location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts"})
public class ICAOAirportCodeTest
{

    @Test
    public void testAltantaICAOAirportCode()
    {
        ICAOAirportCode atlantaICAOAirportCode = new ICAOAirportCode( "KLAX" );

        assertEquals( "KLAX", atlantaICAOAirportCode.getAirportCode()
                     ,"wrong value for airport code" );
    }

    @Test
    public void testEdmontonICAOAirportCode()
    {
        ICAOAirportCode edmontonICAOAirportCode = new ICAOAirportCode( "KORL" );

        assertEquals( "KORL", edmontonICAOAirportCode.getAirportCode()
                     ,"wrong value for airport code" );
    }

    @Test
    public void testAltantaICAOAirportCodeLowercase()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class
                                           ,() -> { new ICAOAirportCode( "katl" ); }
                                           ,"Did not thow expected exception" );

        assertThat(  "Exception message contains the necessary detail"
                   ,throwable.getMessage()
                   ,both(containsString( "Invalid ICAO airport code" ) )
                    .and( containsString( "'katl'" ))
                  );
    }


    @Test
    public void testICAOAirportCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class
                                           ,() -> {new ICAOAirportCode( null );}
                                           ,"Did not thow expected exception" );

        assertEquals( "Airport code is required", throwable.getMessage()
                     ,"Incorrect exception message" );
    }


    // ----- Comparable

    @Test
    public void compareSimilarICAOAirportCode()
    {
        ICAOAirportCode edmonton1ICAOAirportCode = new ICAOAirportCode( "KYYZ" );
        ICAOAirportCode edmonton2ICAOAirportCode = new ICAOAirportCode( "KYYZ" );

        assertEquals( 0, edmonton1ICAOAirportCode.compareTo( edmonton2ICAOAirportCode )
                     ,"Different instances with same airport code are equivalent"
                    );
    }

    @Test
    public void collationOrderOfICAOAirportCode()
    {
        ICAOAirportCode edmontonICAOAirportCode  = new ICAOAirportCode( "CYEG" );       // NOPMD  (DU - Anomaly)
        ICAOAirportCode atlantaICAOAirportCode   = new ICAOAirportCode( "KATL" );       // NOPMD  (DU - Anomaly)

        assertAll( "Origin and destination airport code check"
                  ,() -> assertTrue( edmontonICAOAirportCode.compareTo( atlantaICAOAirportCode ) < 0
                                    ,"verify correct collation (sort) order"
                                   )
                  ,() -> assertTrue( atlantaICAOAirportCode.compareTo( edmontonICAOAirportCode ) > 0
                                    ,"verify correct collation (sort) order"
                                   )
                 );
    }
}
