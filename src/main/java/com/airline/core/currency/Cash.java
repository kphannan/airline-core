package com.airline.core.currency;


/**
 * A cash currency amount.
 */
public class Cash extends Money
{
    /**
     * Creates an instance of Cash in a specific currency.
     *
     * @param currencyCode ISO currency code.
     * @param amount fixed point value of the cash.
     * @param precision the number of decimal digits in the fixed point number.
     */
    public Cash( final String currencyCode, final int amount, final int precision )
    {
        super( currencyCode, precision, amount );
    }
}
