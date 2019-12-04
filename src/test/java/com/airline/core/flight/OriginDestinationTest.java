package com.airline.core.flight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.airline.core.location.AirportCode;
import com.airline.core.location.IATAAirportCode;

import org.junit.jupiter.api.Test;

public class OriginDestinationTest
{
    @Test
    public void testConstructorWithBothOriginAndDestinationAsStrings()
    {
        OriginDestination od = new OriginDestination( "ATL", "JFK" );

        assertNotNull( od );
        assertEquals( new IATAAirportCode("ATL"), od.getOrigin(), "Origin does not match" );
        assertEquals( new IATAAirportCode("JFK"), od.getDestination(), "Destination does not match" );

        assertEquals( "ATL", od.getOrigin().getAirportCode() );
        assertEquals( "JFK", od.getDestination().getAirportCode() );
    }

    @Test
    public void testConstructorWithOriginStringNullThrows()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    new OriginDestination( null, "JFK" );
                                   });

        assertTrue( t.getMessage().contains("Both origin and destination must be specified") );
    }

    @Test
    public void testConstructorWithDestinationStringNullThrows()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    new OriginDestination( "MSP", null );
                                   });

        assertTrue( t.getMessage().contains("Both origin and destination must be specified") );
    }

    @Test
    public void testConstructorWithBothOriginDestinationStringNullThrows()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    // Cast to dis-ambiguate the constructor
                                    new OriginDestination( (String)null, (String)null );
                                   });

        assertTrue( t.getMessage().contains("Both origin and destination must be specified") );
    }


    @Test
    public void testConstructorWithBothOriginAndDestinationAsIATACodes()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertEquals( atl, od.getOrigin(), "Origin does not match" );
        assertEquals( jfk, od.getDestination(), "Destination does not match" );

        assertEquals( "ATL", od.getOrigin().getAirportCode() );
        assertEquals( "JFK", od.getDestination().getAirportCode() );
    }


    @Test
    public void testConstructorWithBothOriginDestinationIATANullThrows()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    // Cast to dis-ambiguate the constructor
                                    new OriginDestination( (AirportCode)null, (AirportCode)null );
                                   });

        assertTrue( t.getMessage().contains("Both origin and destination must be specified") );
    }

}