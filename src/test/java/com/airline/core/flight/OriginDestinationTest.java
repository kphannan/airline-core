package com.airline.core.flight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    public void testConstructorWithOriginAndNullDestinationIATACodes()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      new OriginDestination( atl, null );
                                   });

        assertEquals( "Both origin and destination must be specified", t.getMessage() );
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


    // ----- equals() -----
    @Test
    public void equalsToNullIsAlwaysFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertFalse( od.equals(null));
        // assertEquals( atl, od.getOrigin(), "Origin does not match" );
        // assertEquals( jfk, od.getDestination(), "Destination does not match" );

        // assertEquals( "ATL", od.getOrigin().getAirportCode() );
        // assertEquals( "JFK", od.getDestination().getAirportCode() );
    }

    @Test
    public void equalsToSelfAlwaysTrue()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertTrue( od.equals( od ));
    }

    @Test
    public void equalsToSimilarIsTrue()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertTrue( od.equals( new OriginDestination(new IATAAirportCode("ATL"), new IATAAirportCode("JFK")) ));
    }

    @Test
    public void equalsToDissimilarOriginIsFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertFalse( od.equals( new OriginDestination(new IATAAirportCode("LAX"), new IATAAirportCode("JFK")) ));
    }

    @Test
    public void equalsToDissimilarDestinationIsFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertFalse( od.equals( new OriginDestination(new IATAAirportCode("ATL"), new IATAAirportCode("MCO")) ));
    }

    // ----- compareTo() -----
    @Test
    public void compareToNullFails()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertEquals( -1, od.compareTo( null ));
    }
    @Test
    public void compareToSelfAlwaysTrue()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertEquals( 0, od.compareTo( od ));
    }

    @Test
    public void compareToSimilarIsTrue()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertEquals( 0, od.compareTo( new OriginDestination(new IATAAirportCode("ATL"), new IATAAirportCode("JFK")) ));
    }


    @Test
    public void compareToDisSimilarOriginIsFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertTrue( 0 < od.compareTo( new OriginDestination(new IATAAirportCode("ABY"), new IATAAirportCode("ROC")) ));
    }


    @Test
    public void compareToDissimilarDestinationIsFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode("ATL");
        IATAAirportCode   jfk = new IATAAirportCode("JFK");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertNotNull( od );
        assertTrue( 0 < od.compareTo( new OriginDestination(new IATAAirportCode("ATL"), new IATAAirportCode("HCR")) ));
    }

}