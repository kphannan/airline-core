package com.delta.rm.core;


import lombok.Value;

import java.util.regex.Pattern;


// @RequiredArgsConstructor
@Value
public class AirportCode
{
    // IATA 3 letter aiprport code
    private final String airportCode;


    public AirportCode( final String airportCode )
    {
        if ( !isAirportCodeValid( airportCode ))
        {
            throw new IllegalArgumentException( "Invalid IATA airport code '" + airportCode + "'" );
        }

        this.airportCode = airportCode;
    }


    private boolean isAirportCodeValid( final String code )
    {
        if ( code == null )
        {
            throw new IllegalArgumentException( "Airport Code can not be null" );
        }

        // Only accept 3 uppercase letters, no leading or trailing spaces
        return Pattern.matches( "^[A-Z]{3}$", code );
    }
}