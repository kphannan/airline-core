package com.airline.core.location;

import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;


// IATA airport code, IATA location identifier, IATA station code
// https://www.wikidata.org/wiki/Property:P238
// @RequiredArgsConstructor
@Value
public class IATAAirportCode implements AirportCode
{
    // IATA 3 letter aiprport code
    @JsonProperty( "iataAirportCode")
    private final String airportCode;


    /**
     * Create a new IATAAirportCode instance if the string airport code is
     * exactly 3 uppercase alphabetic characters.
     * @param airportCode target airport code string as 3 uppercase characters
     * @throws IllegalArgumentException when input is not of the proper format
     */
    @JsonCreator
    public IATAAirportCode( @JsonProperty( "iataAirportCode" ) final String iataAirportCode )
    {
        if ( !isAirportCodeValid( iataAirportCode ))
        {
            throw new IllegalArgumentException( "Invalid IATA airport code '" + iataAirportCode + "'" );
        }

        this.airportCode = iataAirportCode;
    }


    private boolean isAirportCodeValid( final String code )
    {
        if ( code == null )
        {
            throw new IllegalArgumentException( "Airport code is required" );
        }

        // Only accept 3 uppercase letters, no leading or trailing spaces
        return Pattern.matches( "^[A-Z0-9]{3}$", code );
    }

    // public String toString()
    // {
    //     return airportCode;
    // }

    // ----- Comparable
    @Override
    public int compareTo( final AirportCode rhs )
    {
        return airportCode.compareTo(rhs.getAirportCode());
    }

}

