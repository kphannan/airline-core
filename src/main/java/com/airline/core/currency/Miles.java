package com.airline.core.currency;


public class Miles extends Money
{
    public static final String MILES_CURRENCY_CODE = "ZZZ";

    public Miles( final int amount, final int precision )
    {
        super( MILES_CURRENCY_CODE, amount, precision );
    }
}

