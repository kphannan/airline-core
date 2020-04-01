
package com.airline.core.currency;

import lombok.Data;

@Data
public class Amount implements Comparable<Amount>
{
    private int value;
    private int decimalPrecision;


    public Amount( final int amount, final int precision )
    {
        this.decimalPrecision = precision;
        this.value            = amount;
    }


    public Amount add( final Amount arg )
    {
        if ( arg == null )
        {
            throw new IllegalArgumentException( "Incompatible (null) amount" );
        }        

        if ( decimalPrecision != arg.getDecimalPrecision() )
        {
            throw new ArithmeticException( String.format( "Precision mismatch %d != %d"
                                                         ,decimalPrecision, arg.decimalPrecision ));
        }

        // TODO Normalize amount based on precision
        this.value += arg.value;
        return this;
    }

    public Amount subtract( final Amount arg )
    {
        if ( arg == null )
        {
            throw new IllegalArgumentException( "Incompatible (null) amount" );
        }        

        if ( decimalPrecision != arg.getDecimalPrecision() )
        {
            throw new ArithmeticException( String.format( "Precision mismatch %d != %d"
                                                         ,decimalPrecision, arg.decimalPrecision ));
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
            throw new ArithmeticException( String.format( "Precision mismatch %d != %d"
                                                         ,decimalPrecision
                                                         ,amount.decimalPrecision ));
        }

        return this.value - amount.value;
    }

}