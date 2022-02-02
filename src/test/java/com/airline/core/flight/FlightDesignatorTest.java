package com.airline.core.flight;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.airline.core.carrier.IATAAirlineDesignator;

import org.junit.jupiter.api.Test;

@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
class FlightDesignatorTest
{

    @Test
    void testDefaultConstructorWithAirlineAsString()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 ); // NOPMD

        assertAll( "FlightDesignator internal state"
                  ,() -> assertEquals( "IATAAirlineDesignator(airlineCode=DL)", flightDesignator.getAirlineCode().toString()
                                      ,"Airline code not set in constructor" )
                  ,() -> assertEquals( 1234, flightDesignator.getFlightNumber()
                                      ,"Flight number not set in constructor" )
                 );
    }

    @Test
    void testDefaultConstructorWithAirlineAsIATA()
    {
        IATAAirlineDesignator airline = new IATAAirlineDesignator( "DL" );
        FlightDesignator flightDesignator = new FlightDesignator( airline, 4433 );   // NOPMD

        assertAll( "FlightDesignator internal state"
                  ,() -> assertEquals( "IATAAirlineDesignator(airlineCode=DL)", flightDesignator.getAirlineCode().toString()
                                      ,"Airline code not set in constructor" )
                  ,() -> assertEquals( 4433, flightDesignator.getFlightNumber()
                                      ,"Flight number not set in constructor" )
                 );
    }

}

