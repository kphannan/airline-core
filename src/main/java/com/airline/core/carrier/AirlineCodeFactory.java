package com.airline.core.carrier;


public class AirlineCodeFactory
{
    /**
     * Create an airline designator of the appropriate type:
     * IATA - 2 characters
     * ICAO - 3 characters
     * The factory does not check if the code is a valid code against
     * the list managed by either organization.  Only that it meets the
     * structural requirements of number and type of characters.
     * @param airlineCode the target airline code
     * @return an IATA Airline Code or ICAO airline code depending on the
     *     number of characters in the target.
     */
    public static AirlineCode build( final String airlineCode )
    {
        if ( airlineCode != null && airlineCode.length() < 3 )
        {
            return new IATAAirlineDesignator( airlineCode );
        }

        return new ICAOAirlineDesignator( airlineCode );
    }

    // Hide the default constructor to ensure this is used as a static
    // factory class.
    private AirlineCodeFactory()
    {
        // Empty constructor
    }
}
