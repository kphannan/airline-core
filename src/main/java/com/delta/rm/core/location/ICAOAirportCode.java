package com.delta.rm.core.location;


import java.util.regex.Pattern;

import lombok.Value;

// ICAO airport code
//   - https://en.wikipedia.org/wiki/ICAO_airport_code
@Value
public class ICAOAirportCode implements AirportCode
{
    // ICAO 4 letter aiprport code
    private final String airportCode;



    public ICAOAirportCode( final String airportCode )
    {
        if ( !isAirportCodeValid( airportCode ))
        {
            throw new IllegalArgumentException( "Invalid ICAO airport code '" + airportCode + "'" );
        }

        this.airportCode = airportCode;
    }


    private boolean isAirportCodeValid( final String code )
    {
        if ( code == null )
        {
            throw new IllegalArgumentException( "Airport Code can not be null" );
        }

        // Only accept 4 uppercase letters, no leading or trailing spaces
        return Pattern.matches( "^[A-Z]{4}$", code );
    }
}

