package com.airline.core.currency;

import java.util.regex.Pattern;


/**
 * ISO 4217 currency number code.
 */
// @EqualsAndHashCode(callSuper=false)
public class CurrencyNumericCode extends CurrencyCode<String>
{
    /**
     * Construct an ISO country code.
     * The country code is validated against a list of acceptable codes.
     *
     * @param isoCountryCode the intended ISO country code.
     */
    public CurrencyNumericCode( final String code )
    {
        super( code );
    }

    @Override
    protected boolean isCodeValid( final String code )
    {
        return Pattern.matches( "^[0-9]{3}$", code );
    }
}
