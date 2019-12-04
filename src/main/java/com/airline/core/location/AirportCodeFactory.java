package com.airline.core.location;


public class AirportCodeFactory
{
    /**
     * Create an airport Code of the appropriate type:
     * IATA - 3 characters
     * ICAO - 4 characters
     * The factory does not check if the code is a valid code against
     * the list managed by either organization.  Only that it meets the
     * structural requirements of number and type of characters.
     * @param airportCode the target airport code
     * @return an IATA airport Code or ICAO airport code depending on the
     *     number of characters in the target.
     */
    public static AirportCode build( final String airportCode )
    {
        if ( airportCode != null && airportCode.length() < 4 )
        {
            return new IATAAirportCode( airportCode );
        }

        return new ICAOAirportCode( airportCode );
    }

    // Hide the default constructor to ensure this is used as a static
    // factory class.
    private AirportCodeFactory()
    {
        // Empty constructor
    }
}
