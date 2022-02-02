
package com.airline.core.currency;

import lombok.Data;

/**
 * Amount is a fixed decimal number.
 */
@Data
public class Amount implements Comparable<Amount>
{
    private int value;
    private int decimalPrecision;


    /**
     * Create a fixed decimal value with a specified number of fractional digits.
     *
     * @param amount the normalized value.
     * @param precision the number of fractional digits.
     */
    public Amount( final int amount, final int precision )
    {
        this.decimalPrecision = precision;
        this.value            = amount;
    }



    /**
     * Add a fixed point number to this amount, return this amount.
     *
     * @param arg the amount to add.
     * @return the result of the addition.
     */
    public Amount add( final Amount arg )
    {
        if ( arg == null )
        {
            throw new IllegalArgumentException( "Incompatible (null) amount" );
        }

        if ( decimalPrecision != arg.getDecimalPrecision() )
        {
            throw new ArithmeticException( String.format(  "Precision mismatch %d != %d"
                                                         , decimalPrecision, arg.decimalPrecision ) );
        }

        // TODO Normalize amount based on precision
        this.value += arg.value;
        return this;
    }

    /**
     * Subract a a fixed point number from this amount, return this amount.
     *
     * @param arg the amount to subtract.
     * @return the result of the subtraction.
     */
    public Amount subtract( final Amount arg )
    {
        if ( arg == null )
        {
            throw new IllegalArgumentException( "Incompatible (null) amount" );
        }

        if ( decimalPrecision != arg.getDecimalPrecision() )
        {
            throw new ArithmeticException( String.format(  "Precision mismatch %d != %d"
                                                         , decimalPrecision, arg.decimalPrecision ) );
        }

        // TODO Normalize amount based on precision
        this.value -= arg.value;
        return this;
    }


    @Override
    public int compareTo( final Amount amount )
    {
        if ( amount == null )
        {
            throw new IllegalArgumentException( "Can not compare against (null) amount" );
        }

        // ! Need to normalize the precision before compare
        if ( this.decimalPrecision != amount.decimalPrecision )
        {
            throw new ArithmeticException( String.format(  "Precision mismatch %d != %d"
                                                         , decimalPrecision
                                                         , amount.decimalPrecision ) );
        }

        return this.value - amount.value;
    }

}