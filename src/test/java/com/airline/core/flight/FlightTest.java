package com.airline.core.flight;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.airline.core.location.IATAAirportCode;

import org.junit.jupiter.api.Test;

@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts"})
public class FlightTest
{

    @Test
    public void testBuilderWithOnlyMarketingFlightDesignator()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 );
        Flight.Builder flightBuilder = new Flight.Builder( flightDesignator );

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                       flightBuilder.build();
                                   });

        assertTrue( t.getMessage().contains("Both origin and destination must be specified")
                   ,"Expecting a specific message in the exception"
                  );
    }

    @Test
    public void testBuilderWithMarketingFlightDesignatorAndOriginDestinationStrings()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 );
        Flight.Builder flightBuilder = new Flight.Builder( flightDesignator );

        flightBuilder.between("MSP", "LAX" );

        Flight flight = flightBuilder.build();      // NOPMD  (DU - Anomaly)

        assertAll( "Origin and destination airport code check"
                  ,() -> assertEquals( flightDesignator, flight.getFlightDesignator()
                                      ,"incorrect flight designator" )
                  ,() -> assertEquals( new IATAAirportCode( "MSP"), flight.getOriginDestination().getOrigin()
                                      ,"Originating airport code not set properly" )
                  ,() -> assertEquals( new IATAAirportCode( "LAX"), flight.getOriginDestination().getDestination()
                                      ,"destination airport code not correct" )
                  ,() -> assertEquals( 1, flight.getNumberOfSegments(), "Wrong number of segments" )
                  ,() -> assertTrue( flight.hasSingleSegment(), "Flight has a single segment" )
                );
    }


    @Test
    public void testBuilderWithMarketingFlightDesignatorAndOriginDestinationIATA()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 );
        Flight.Builder flightBuilder = new Flight.Builder( flightDesignator );

        flightBuilder.between( new IATAAirportCode("ATL"), new IATAAirportCode("ORL") );

        Flight flight = flightBuilder.build();  // NOPMD  (DU - Anomaly)

        assertAll( "Origin and destination airport code check"
                  ,() -> assertEquals( flightDesignator, flight.getFlightDesignator()
                                      ,"incorrect flight designator" )
                  ,() -> assertEquals( new IATAAirportCode( "ATL"), flight.getOriginDestination().getOrigin()
                                      ,"Originating airport code not set properly" )
                  ,() -> assertEquals( new IATAAirportCode( "ORL"), flight.getOriginDestination().getDestination()
                                      ,"destination airport code not correct" )
                  ,() -> assertEquals( 1, flight.getNumberOfSegments(), "Wrong number of segments" )
                  ,() -> assertTrue( flight.hasSingleSegment(), "Flight has a single segment" )
                 );
    }


    @Test
    public void testBuildFlightWithMultipleSegments()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 );
        Flight.Builder   flightBuilder    = new Flight.Builder( flightDesignator );

        flightBuilder.between( new IATAAirportCode("ATO"), new IATAAirportCode("MCO") );

        flightBuilder.segment( flightDesignator
                              ,1, new IATAAirportCode( "ATO"), new IATAAirportCode( "ORD"));
        flightBuilder.segment( flightDesignator
                              ,2, new IATAAirportCode( "ORD"), new IATAAirportCode( "JFK"));
        OriginDestination od = new OriginDestination( new IATAAirportCode( "JFK")
                                                     ,new IATAAirportCode( "MCO"));
        flightBuilder.segment(flightDesignator, 3, od );

        Flight flight = flightBuilder.build(); // NOPMD  (DU - Anomaly)

        assertAll( "Origin and destination airport code check"
                  ,() -> assertEquals( flightDesignator, flight.getFlightDesignator()
                                      ,"incorrect flight designator" )
                  ,() -> assertEquals( new IATAAirportCode( "ATO"), flight.getOriginDestination().getOrigin()
                                      ,"Originating airport code not set properly" )
                  ,() -> assertEquals( new IATAAirportCode( "MCO"), flight.getOriginDestination().getDestination()
                                      ,"destination airport code not correct" )
                  ,() -> assertEquals( 3, flight.getNumberOfSegments(), "Wrong number of segments" )
                  ,() -> assertFalse( flight.hasSingleSegment(), "Flight has multiple segments" )
                );
    }
}

