package com.airline.core.location;

import java.util.regex.Pattern;

import lombok.Value;
// import lombok.Data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
// import com.fasterxml.jackson.annotation.JsonName;
 


// ICAO airport code
//   - https://en.wikipedia.org/wiki/ICAO_airport_code
@Value
public class ICAOAirportCode implements AirportCode
{
    // ICAO 4 letter aiprport code
    @JsonProperty( "icaoAirportCode")
    private final String airportCode;



    /**
     * Create a new ICAOAirportCode instance if the string airport code is
     * exactly 4 uppercase alphabetic characters.
     * @param airportCode target airport code string as 4 uppercase characters
     * @throws IllegalArgumentException when input is not of the proper format
     */
    @JsonCreator
    public ICAOAirportCode( @JsonProperty("icaoAirportCode") final String icaoAirportCode )
    {
        if ( !isAirportCodeValid( icaoAirportCode ))
        {
            throw new IllegalArgumentException( "Invalid ICAO airport code '" + icaoAirportCode + "'" );
        }

        this.airportCode = icaoAirportCode;
    }


    private boolean isAirportCodeValid( final String code )
    {
        if ( code == null )
        {
            throw new IllegalArgumentException( "Airport code is required" );
        }

        // Only accept 4 uppercase letters, no leading or trailing spaces
        return Pattern.matches( "^[A-Z]{4}$", code );
    }

   // ----- Comparable
   @Override
   public int compareTo( final AirportCode rhs )
   {
        return airportCode.compareTo(rhs.getAirportCode());
    }
}

