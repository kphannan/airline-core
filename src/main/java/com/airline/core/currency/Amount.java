
package com.airline.core.currency;

import lombok.Data;

@Data
public class Amount implements Comparable<Amount>
{
    private int amount;
    private int decimalPrecision;


    public Amount( final int amount, final int precision )
    {
        this.decimalPrecision = precision;
        this.amount           = amount;
    }


    public Amount add( final Amount arg )
    {
        if ( arg == null )
        {
            throw new IllegalArgumentException( "Incompatible (null) amount" );
        }        

        if ( decimalPrecision != arg.getDecimalPrecision() )
            throw new ArithmeticException( String.format( "Precision mismatch %d != %d", decimalPrecision, arg.decimalPrecision ));

        // TODO Normalize amount based on precision
        this.amount += arg.amount;
        return this;
    }

    public Amount subtract( final Amount arg )
    {
        if ( arg == null )
        {
            throw new IllegalArgumentException( "Incompatible (null) amount" );
        }        

        if ( decimalPrecision != arg.getDecimalPrecision() )
            throw new ArithmeticException( String.format( "Precision mismatch %d != %d", decimalPrecision, arg.decimalPrecision ));

        // TODO Normalize amount based on precision
        this.amount -= arg.amount;
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
            throw new ArithmeticException( String.format( "Precision mismatch %d != %d", decimalPrecision, amount.decimalPrecision ));
        }

        return this.amount - amount.amount;
    }

}