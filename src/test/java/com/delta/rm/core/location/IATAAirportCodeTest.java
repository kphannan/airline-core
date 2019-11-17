package com.delta.rm.core.location;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IATAAirportCodeTest
{

    @Test
    public void testAltantaIATAAirportCode()
    {
        IATAAirportCode atlantaIATAAirportCode = new IATAAirportCode( "ATL" );

        assertNotNull( atlantaIATAAirportCode );
        assertEquals( "ATL", atlantaIATAAirportCode.getAirportCode() );
    }

    @Test
    public void testNaritaIATAAirportCode()
    {
        IATAAirportCode naritaIATAAirportCode = new IATAAirportCode( "NRT" );

        assertNotNull( naritaIATAAirportCode );
        assertEquals( "NRT", naritaIATAAirportCode.getAirportCode() );
    }

    @Test
    public void testAltantaIATAAirportCodeLowercase()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new IATAAirportCode( "atl" );
        });

        assertTrue( throwable.getMessage().contains("Invalid IATA airport code")
                   ,"Exception message contains Invalid IATA airport code" );

        assertTrue( throwable.getMessage().contains("Invalid IATA airport code")
                   ,"'atl'" );

    }


    @Test
    public void testIATAAirportCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new IATAAirportCode( null );
        });

        assertEquals( "Airport Code can not be null", throwable.getMessage() );
    }


}
