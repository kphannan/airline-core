package com.delta.rm.core.flight;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.delta.rm.core.location.IATAAirportCode;

// import com.delta.rm.core.carrier.IATAAirlineDesignator;

import org.junit.jupiter.api.Test;

public class FlightTest
{

    @Test
    public void testBuilderWithOnlyMarketingFlightDesignator()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 );

        assertNotNull( flightDesignator );

        Flight.Builder flightBuilder = new Flight.Builder( flightDesignator );

        assertNotNull( flightBuilder );

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                       flightBuilder.build();
                                   });

        assertTrue( t.getMessage().contains("Both origin and destination must be specified") );
    }

    @Test
    public void testBuilderWithMarketingFlightDesignatorAndOriginDestinationStrings()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 );

        assertNotNull( flightDesignator );

        Flight.Builder flightBuilder = new Flight.Builder( flightDesignator );

        assertNotNull( flightBuilder );

        flightBuilder.between("MSP", "LAX" );

        Flight flight = flightBuilder.build();
        assertNotNull( flight );

        assertEquals( flightDesignator, flight.getMarketingFlightDesignator() );
        assertEquals( new IATAAirportCode( "MSP"), flight.getOriginDestination().getOrigin() );
        assertEquals( new IATAAirportCode( "LAX"), flight.getOriginDestination().getDestination() );
    }

    @Test
    public void testBuilderWithOptionalOperatingDesignator()
    {
        FlightDesignator flightDesignator    = new FlightDesignator( "DL", 1234 );
        FlightDesignator operatingDesignator = new FlightDesignator( "AF", 4321 );

        assertNotNull( flightDesignator );

        Flight.Builder flightBuilder = new Flight.Builder( flightDesignator );

        assertNotNull( flightBuilder );

        flightBuilder.between("ATL", "ORL" )
                     .operatedAs( operatingDesignator );

        Flight flight = flightBuilder.build();
        assertNotNull( flight );

        assertEquals( flightDesignator,    flight.getMarketingFlightDesignator() );
        assertEquals( operatingDesignator, flight.getOperatingFlightDesignator() );
        assertEquals( new IATAAirportCode( "ATL"), flight.getOriginDestination().getOrigin() );
        assertEquals( new IATAAirportCode( "ORL"), flight.getOriginDestination().getDestination() );
    }

    @Test
    public void testBuilderWithMarketingFlightDesignatorAndOriginDestinationIATA()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 );

        assertNotNull( flightDesignator );

        Flight.Builder flightBuilder = new Flight.Builder( flightDesignator );

        assertNotNull( flightBuilder );

        flightBuilder.between( new IATAAirportCode("ATL"), new IATAAirportCode("ORL") );

        Flight flight = flightBuilder.build();
        assertNotNull( flight );

        assertEquals( flightDesignator, flight.getMarketingFlightDesignator() );
        assertEquals( new IATAAirportCode( "ATL"), flight.getOriginDestination().getOrigin() );
        assertEquals( new IATAAirportCode( "ORL"), flight.getOriginDestination().getDestination() );

        // assertNull( flight.getSegments() );
    }

}

