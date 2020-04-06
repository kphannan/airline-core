package com.airline.core.carrier;

import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;


// AirlineDesignator                   // https://en.wikipedia.org/wiki/Airline_codes
//    - IATA Airline Designator   // 2 letter
//    - ICAO Airline Designator   // 3 letter code



// AccountingNumber                    // IATA 3 digit accounting code





//    - IATA Airline Designator   // 2 letter/number
@Value
public class IATAAirlineDesignator implements AirlineCode
{
    // IATA 2 letter airline code
    @JsonProperty( "iataAirlineCode")
    private final String airlineCode;


    /**
     * Create a IATA carrier code instance if the code is no more than two
     * characters/letters, all uppercase.
     * @param carrierCode 2 character uppercase alphanumeric airport code
     * @throws IllegalArgumentException if the code does not pass validation.
     */
    @JsonCreator
    public IATAAirlineDesignator( @JsonProperty( "iataAirlineCode")final String carrierCode )
    {
        if ( !isCarrierCodeValid( carrierCode ))
        {
            throw new IllegalArgumentException( "Invalid IATA airline code '" + carrierCode + "'" );
        }

        this.airlineCode = carrierCode;
    }


    private boolean isCarrierCodeValid( final String code )
    {
        if ( code == null )
        {
            throw new IllegalArgumentException( "Airline code is required" );
        }

        // Only accept 2 uppercase letters/numbers, no leading or trailing spaces
        return Pattern.matches( "^[A-Z0-9]{2}$", code );
    }
}
