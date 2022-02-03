package com.airline.core.flight;

import com.airline.core.carrier.AirlineCode;
import com.airline.core.carrier.AirlineCodeFactory;
import lombok.Data;

/**
 * Identifier of a flight.  A designator may span multiple segments (takeoff/landing).
 */
@Data
public class FlightDesignator
{
    public static final int MIN_FLIGHT_NUMBER =    1;
    public static final int MAX_FLIGHT_NUMBER = 9999;

    private final AirlineCode airlineCode;
    private final int         flightNumber;


    /**
     * Instantiate a flight designator from primative types.
     *
     * @param carrier the carrier code.
     * @param flightNumber the flight number.
     */
    public FlightDesignator( final String carrier, final int flightNumber )
    {
        this( AirlineCodeFactory.build( carrier ), flightNumber );
    }

    /**
     * Instantiate a flight designator with an AirlineCode object and flight number.
     *
     * @param airline the airline.
     * @param flightNumber the flight number.
     */
    public FlightDesignator( final AirlineCode airline, final int flightNumber )
    {
        if ( flightNumber < MIN_FLIGHT_NUMBER || flightNumber > MAX_FLIGHT_NUMBER )
        {
            throw new IllegalArgumentException( String.format( "Invalid flight number %d <= '%d' <= %d", MIN_FLIGHT_NUMBER, flightNumber, MAX_FLIGHT_NUMBER ) );
        }

        this.airlineCode  = airline;
        this.flightNumber = flightNumber;
    }
}
