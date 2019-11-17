package com.delta.rm.core;


import lombok.Value;

import java.util.regex.Pattern;


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
