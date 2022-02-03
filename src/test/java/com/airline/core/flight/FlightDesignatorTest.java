package com.airline.core.flight;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.airline.core.carrier.IATAAirlineDesignator;
import org.junit.jupiter.api.Test;

/**
 * Unit test of FlightDesignator.
 */
@SuppressWarnings( { "PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals" } )
class FlightDesignatorTest
{

    @Test
    void testDefaultConstructorWithAirlineAsString()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 ); // NOPMD

        assertAll(  "FlightDesignator internal state"
                  , () -> assertEquals( "IATAAirlineDesignator(airlineCode=DL)", flightDesignator.getAirlineCode().toString()
                                       ,"Airline code not set in constructor" )
                  , () -> assertEquals( 1234, flightDesignator.getFlightNumber()
                                       ,"Flight number not set in constructor" )
                 );
    }

    @Test
    void testDefaultConstructorWithAirlineAsIATA()
    {
        IATAAirlineDesignator airline          = new IATAAirlineDesignator( "DL" );
        FlightDesignator      flightDesignator = new FlightDesignator( airline, 4433 );   // NOPMD

        assertAll(  "FlightDesignator internal state"
                  , () -> assertEquals( "IATAAirlineDesignator(airlineCode=DL)", flightDesignator.getAirlineCode().toString()
                                       ,"Airline code not set in constructor" )
                  , () -> assertEquals( 4433, flightDesignator.getFlightNumber()
                                       ,"Flight number not set in constructor" )
                 );
    }


    @Test
    void flightDesignator_FlightNumberNegative_throwsException()
    {
        final Throwable thrown = assertThrows( IllegalArgumentException.class
                                              , () -> new FlightDesignator( "AA", -5 )
                                             );

        assertEquals( "Invalid flight number 1 <= '-5' <= 9999", thrown.getMessage() );
    }

    @Test
    void flightDesignator_FlightNumberTooLarge_throwsException()
    {
        final Throwable thrown = assertThrows( IllegalArgumentException.class
                                              , () -> new FlightDesignator( "AA", 10000 )
                                             );

        assertEquals( "Invalid flight number 1 <= '10000' <= 9999", thrown.getMessage() );
    }

    @Test
    void flightDesignator_minFlightNumber_Instantiates()
    {
        assertNotNull( new FlightDesignator( "UA", FlightDesignator.MIN_FLIGHT_NUMBER ) );
    }

    @Test
    void flightDesignator_maxFlightNumber_Instantiates()
    {
        assertNotNull( new FlightDesignator( "UA", FlightDesignator.MAX_FLIGHT_NUMBER ) );
    }
}

