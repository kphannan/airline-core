package com.airline.core.currency;


public class Cash extends Money
{
    public Cash( final String currencyCode, final int amount, final int precision )
    {
        super( currencyCode, precision, amount );
    }
}
