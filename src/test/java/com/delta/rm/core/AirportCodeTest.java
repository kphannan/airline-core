package com.delta.rm.core;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AirportCodeTest
{

    @Test
    public void testAltantaAirportCode()
    {
        AirportCode atlantaAirportCode = new AirportCode( "ATL" );

        assertNotNull( atlantaAirportCode );
        assertEquals( "ATL", atlantaAirportCode.getAirportCode() );
    }

    @Test
    public void testNaritaAirportCode()
    {
        AirportCode naritaAirportCode = new AirportCode( "NRT" );

        assertNotNull( naritaAirportCode );
        assertEquals( "NRT", naritaAirportCode.getAirportCode() );
    }

    @Test
    public void testAltantaAirportCodeLowercase()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new AirportCode( "atl" );
        });

        assertTrue( throwable.getMessage().contains("Invalid IATA airport code")
                   ,"Exception message contains Invalid IATA airport code" );

        assertTrue( throwable.getMessage().contains("Invalid IATA airport code")
                   ,"'atl'" );

    }


    @Test
    public void testAirportCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new AirportCode( null );
        });

        assertEquals( "Airport Code can not be null", throwable.getMessage() );
    }


}
