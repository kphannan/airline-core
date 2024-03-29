package com.airline.core.carrier;

import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;



// AccountingNumber                    // IATA 3 digit accounting code




// ICAO Airline designator, which is a 3 letter code
// https://www.wikidata.org/wiki/Property:P230
/**
 * Represents an industry standard 3 character uppercase alpha code.
 * The code is verified for format only.  It is not validated against a published
 * list of air carriers.
 */
@Value
public class ICAOAirlineDesignator implements AirlineCode
{
    // ICAO 3 letter aiprport code
    @JsonProperty( "icaoAirlineCode")
    private final String airlineCode;


    /**
     * Create a ICAO carrier code instance if the code is exacly three
     * alphabetic characters, all uppercase.
     * @param carrierCode 3 uppercase letter airport code
     * @throws IllegalArgumentException if the code does not pass validation.
     */
    @JsonCreator
    public ICAOAirlineDesignator( @JsonProperty( "icaoAirlineCode")final String carrierCode )
    {
        if ( !isCarrierCodeValid( carrierCode ))
        {
            throw new IllegalArgumentException( "Invalid ICAO airline code '" + carrierCode + "'" );
        }

        this.airlineCode = carrierCode;
    }


    private boolean isCarrierCodeValid( final String code )
    {
        if ( code == null )
        {
            throw new IllegalArgumentException( "Airline code is required" );
        }

        // Only accept 3 uppercase letters, no leading or trailing spaces
        return Pattern.matches( "^[A-Z]{3}$", code );
    }
}
