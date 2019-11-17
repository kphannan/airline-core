package com.delta.rm.core.carrier;


import java.util.regex.Pattern;

import lombok.Value;



// AirlineDesignator                   // https://en.wikipedia.org/wiki/Airline_codes
//    - IATA Airline Designator   // 2 letter
//    - ICAO Airline Designator   // 3 letter code



// AccountingNumber                    // IATA 3 digit accounting code





@Value
public class IATAAirlineDesignator implements AirlineCode
{
    // IATA 2 letter aiprport code
    private final String airlineCode;


    public IATAAirlineDesignator( final String carrierCode )
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

        // Only accept 2 uppercase letters, no leading or trailing spaces
        return Pattern.matches( "^[A-Z0-9]{2}$", code );
    }
}
