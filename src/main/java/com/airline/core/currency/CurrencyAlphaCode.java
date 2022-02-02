package com.airline.core.currency;

import java.util.regex.Pattern;


/**
 * ISO 4217 currency names.
 */
// @EqualsAndHashCode(callSuper=false)
public class CurrencyAlphaCode extends CurrencyCode<String>
{
    /**
     * Construct an ISO country code.
     * The country code is validated against a list of acceptable codes.
     *
     * @param isoCountryCode the intended ISO country code.
     */
    public CurrencyAlphaCode( final String code )
    {
        super( code );
    }

    /**
     * Verify the format of an ISO 4217 alphabetic country code.
     * A country code must be 3 uppercase alphabetic characters.
     * @param code the code to validate.
     * @return true if the code meets the standard, false otherwise.
     */
    @Override
    protected boolean isCodeValid( final String code )
    {
        return Pattern.matches( "^[A-Z]{3}$", code );
    }
}
