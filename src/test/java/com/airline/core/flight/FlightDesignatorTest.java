package com.airline.core.flight;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertTrue;

import com.airline.core.carrier.IATAAirlineDesignator;

import org.junit.jupiter.api.Test;

public class FlightDesignatorTest
{

    @Test
    public void testDefaultConstructorWithAirlineAsString()
    {
        FlightDesignator flightDesignator = new FlightDesignator( "DL", 1234 );

        assertNotNull( flightDesignator );
        assertEquals( "IATAAirlineDesignator(airlineCode=DL)", flightDesignator.getAirlineCode().toString() );
        assertEquals( 1234, flightDesignator.getFlightNumber() );
    }

    @Test
    public void testDefaultConstructorWithAirlineAsIATA()
    {
        FlightDesignator flightDesignator = new FlightDesignator( new IATAAirlineDesignator( "DL" ), 1234 );

        assertNotNull( flightDesignator );
        assertEquals( "IATAAirlineDesignator(airlineCode=DL)", flightDesignator.getAirlineCode().toString() );
        assertEquals( 1234, flightDesignator.getFlightNumber() );
    }

}

