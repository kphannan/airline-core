package com.delta.rm.core.location;

import java.util.regex.Pattern;

import lombok.Value;

// IATA airport code, IATA location identifier, IATA station code
// @RequiredArgsConstructor
@Value
public class IATAAirportCode implements AirportCode
{
    // IATA 3 letter aiprport code
    private final String airportCode;


    /**
     * Create a new IATAAirportCode instance if the string airport code is
     * exactly 3 uppercase alphabetic characters.
     * @param airportCode target airport code string as 3 uppercase characters
     * @throws IllegalArgumentException when input is not of the proper format
     */
    public IATAAirportCode( final String airportCode )
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
            throw new IllegalArgumentException( "Airport code is required" );
        }

        // Only accept 3 uppercase letters, no leading or trailing spaces
        return Pattern.matches( "^[A-Z]{3}$", code );
    }

    // public String toString()
    // {
    //     return airportCode;
    // }
}

