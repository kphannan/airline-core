package com.airline.core.location;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


        assertThat( throwable.getMessage()
                   ,both(containsString("Invalid IATA airport code"))
                   .and( containsString( "'atl'")) );
    }


    @Test
    public void testIATAAirportCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new IATAAirportCode( null );
        });

        assertEquals( "Airport code is required", throwable.getMessage() );
    }


}
