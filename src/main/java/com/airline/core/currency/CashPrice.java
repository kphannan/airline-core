package com.airline.core.currency;


public class CashPrice extends Price
{
    public CashPrice( final String priceCode, final int amount, final int precision )
    {
        super( priceCode, precision, amount );
    }
}


