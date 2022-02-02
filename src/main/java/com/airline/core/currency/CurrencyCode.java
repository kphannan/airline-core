package com.airline.core.currency;

import lombok.Data;

/**
 * ISO 4217 currency names.
 */
@Data
public abstract class CurrencyCode<T>
{
    private final T code;

    /**
     * Construct an ISO country code.
     * The country code is validated against a list of acceptable codes.
     *
     * @param isoCountryCode the intended ISO country code.
     */
    protected CurrencyCode( final T isoCountryCode )
    {
        if ( null == isoCountryCode )
        {
            throw new IllegalArgumentException(  "Currency code is required" );
        }

        if ( !isCurrencyCodeValid( isoCountryCode ) )
        {
            throw new IllegalArgumentException(  "Invalid ISO 4217 currency code '"
                                               + isoCountryCode + "'" );
        }

        this.code = isoCountryCode;
    }



    /**
     * Verify the structure of a country code.  A validation predicate is
     * mandatory for a derived class.
     *
     * @param isoCountryCode the code to validate.
     * @return true on sucessful validation
     */
    protected abstract boolean isCodeValid( final T isoCountryCode );


    private boolean isCurrencyCodeValid( final T isoCountryCode )
    {
        return isCodeValid( isoCountryCode );
    }



}


