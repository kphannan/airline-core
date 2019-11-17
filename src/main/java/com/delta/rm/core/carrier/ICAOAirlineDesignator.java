package com.delta.rm.core.carrier;


import java.util.regex.Pattern;

import lombok.Value;



// AccountingNumber                    // IATA 3 digit accounting code




// ICAO Airline designator, which is a 3 letter code
@Value
public class ICAOAirlineDesignator implements AirlineCode
{
    // ICAO 3 letter aiprport code
    private final String airlineCode;


    public ICAOAirlineDesignator( final String carrierCode )
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