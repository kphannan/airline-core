package com.airline.core.flight;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

// import com.airline.core.carrier.IATAAirlineDesignator;
import com.airline.core.location.IATAAirportCode;

import org.junit.jupiter.api.Test;

@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class FlightSegmentTest
{

    @Test
    public void properlyConstructInstanceFromOriginAndDestination()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 ); // NOPMD
        IATAAirportCode   origin = new IATAAirportCode( "AVB" );
        IATAAirportCode   destination = new IATAAirportCode( "DFW" );

        var segment = new FlightSegment(flightDesignator, 1, origin, destination);      // NOPMD

        assertAll( "Ensure constructor sets values properly"
                  ,() -> assertEquals( flightDesignator, segment.getFlightDesignator(), "Flight not properly set")
                  ,() -> assertEquals( "AVB", segment.getOrigin().getAirportCode(), "Origin not properly set")
                  ,() -> assertEquals( "DFW", segment.getDestination().getAirportCode(), "Destination not properly set")
                  ,() -> assertEquals( 1, segment.getSegmentNumber(), "Segment number not set properly")
                 );
    }



    @Test
    public void properlyConstructInstanceFromOD()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "KL", 7654 ); // NOPMD
        IATAAirportCode   origin = new IATAAirportCode( "ORL" );
        IATAAirportCode   destination = new IATAAirportCode( "ISL" );
        OriginDestination od  = new OriginDestination( origin, destination );

        var segment = new FlightSegment(flightDesignator, 12, od );             // NOPMD

        assertAll( "Ensure constructor sets values properly"
                  ,() -> assertEquals( flightDesignator, segment.getFlightDesignator(), "Flight not properly set")
                  ,() -> assertEquals( "ORL", segment.getOrigin().getAirportCode(), "Origin not properly set")
                  ,() -> assertEquals( "ISL", segment.getDestination().getAirportCode(), "Destination not properly set")
                  ,() -> assertEquals( 12, segment.getSegmentNumber(), "Segment number not set properly")
                 );
    }
}

