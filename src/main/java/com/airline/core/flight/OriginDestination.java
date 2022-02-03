package com.airline.core.flight;

import com.airline.core.location.AirportCode;
import com.airline.core.location.AirportCodeFactory;
import lombok.Value;


/**
 * Specifies the starting (take-off) location and the ending (landing) airport.
 * The origin and destination could represent start/end of a flight segment, or
 * the entire trip.
 */
@Value
public class OriginDestination implements Comparable<OriginDestination>
{
    private final AirportCode origin;
    private final AirportCode destination;

    /**
     * Create new instance containing IATA or ICAO airport code instances.
     * @param origin origin airport code
     * @param destination destination airport code
     */
    public OriginDestination( final AirportCode origin, final AirportCode destination )
    {
        if ( origin == null || destination == null )
        {
            throw new IllegalArgumentException( "Both origin and destination must be specified");
        }

        this.origin      = origin;
        this.destination = destination;
    }

    /**
     * Create new instance containing IATA or ICAO airport code instances
     * based on the format of the input airport code strings.
     * @param origin string form of the origin airport code
     * @param destination string form of the destination airport code
     */
    public OriginDestination( final String origin, final String destination )
    {
        if ( origin == null || destination == null )
        {
            throw new IllegalArgumentException( "Both origin and destination must be specified");
        }

        this.origin      = AirportCodeFactory.build( origin );
        this.destination = AirportCodeFactory.build( destination );
    }


    // ----- Comparable
    @Override
    public int compareTo( final OriginDestination rhs )
    {
        if ( rhs == null )
        {
            return -1;      // ? Is this appropriate
        }

        int result = origin.getAirportCode().compareTo( rhs.origin.getAirportCode() );
        if ( result == 0 )
        {
            result = destination.getAirportCode().compareTo( rhs.destination.getAirportCode() );
        }

        return result;
    }

}
