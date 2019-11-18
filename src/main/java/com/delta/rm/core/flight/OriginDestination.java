package com.delta.rm.core.flight;

import com.delta.rm.core.location.AirportCode;
import com.delta.rm.core.location.IATAAirportCode;

// import lombok.AllArgsConstructor;
import lombok.Data;


@Data
// @AllArgsConstructor
public class OriginDestination
{
    private final AirportCode origin;
    private final AirportCode destination;

    public OriginDestination( final AirportCode origin, final AirportCode destination )
    {
        if ( origin == null || destination == null )
        {
            throw new IllegalArgumentException( "Both origin and destination must be specified");
        }

        this.origin      = origin;
        this.destination = destination;
    }

    public OriginDestination( final String origin, final String destination )
    {
        if ( origin == null || destination == null )
        {
            throw new IllegalArgumentException( "Both origin and destination must be specified");
        }

        this.origin      = new IATAAirportCode( origin );
        this.destination = new IATAAirportCode( destination );
    }
}
