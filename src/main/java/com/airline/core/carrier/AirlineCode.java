package com.airline.core.carrier;

// AirlineDesignator      // https://en.wikipedia.org/wiki/Airline_codes
// https://en.wikipedia.org/wiki/List_of_airline_codes

/**
 * Generic representation of a character sequence that uniquely identifies
 * an air carrier.
 */
public interface AirlineCode
{
    /**
     * A non-empty string that uniquely identifies an air carrier.
     * @return Retrieve the carrier code.
     */
    public String getAirlineCode();
}


