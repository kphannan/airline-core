package com.airline.core.currency;


public class MilesPrice extends Price
{
    public MilesPrice( final int amount, final int precision )
    {
        super( Miles.MILES_CURRENCY_CODE, amount, precision );
    }
}

