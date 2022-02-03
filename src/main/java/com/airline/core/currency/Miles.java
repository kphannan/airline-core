package com.airline.core.currency;


/**
 * Loyalty program, miles value.
 */
public class Miles extends Money
{
    /**
     * Industry standard currency code for 'miles'.
     */
    public static final String MILES_CURRENCY_CODE = "ZZZ";

    /**
     * Creates an instance of loyalty program miles.
     *
     * @param amount fixed point value of the cash.
     * @param precision the number of decimal digits in the fixed point number.
     */    public Miles( final int amount, final int precision )
    {
        super( MILES_CURRENCY_CODE, amount, precision );
    }
}

