package com.delta.rm.core;


import java.util.regex.Pattern;

import lombok.Value;



// AirlineDesignator                   // https://en.wikipedia.org/wiki/Airline_codes
//    - IATA Airline Designator   // 2 letter
//    - ICAO Airline Designator   // 3 letter code



// AccountingNumber                    // IATA 3 digit accounting code





@Value
public class CarrierCode
{
    // IATA 2 letter aiprport code
    private final String carrierCode;


    public CarrierCode( final String carrierCode )
    {
        if ( !isCarrierCodeValid( carrierCode ))
        {
            throw new IllegalArgumentException( "Invalid IATA carrier code '" + carrierCode + "'" );
        }

        this.carrierCode = carrierCode;
    }


    private boolean isCarrierCodeValid( final String code )
    {
        if ( code == null )
        {
            throw new IllegalArgumentException( "Carrier Code can not be null" );
        }

        // Only accept 2 uppercase letters, no leading or trailing spaces
        return Pattern.matches( "^[A-Z]{2}$", code );
    }
}
