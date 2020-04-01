package com.airline.core.currency;

import java.util.regex.Pattern;

// import lombok.EqualsAndHashCode;

/**
 * ISO 4217 currency number code.
 */
// @EqualsAndHashCode(callSuper=false)
public class CurrencyNumericCode extends CurrencyCode<String>
{
    public CurrencyNumericCode( final String c )
    {
        super( c );
    }

    @Override
    protected boolean isCodeValid( final String code )
    {
        return Pattern.matches( "^[0-9]{3}$", code.toString() );
    }
}
