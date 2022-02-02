package com.airline.core.currency;

import lombok.Value;
import lombok.experimental.NonFinal;



@Value
@NonFinal
public class Price implements Comparable<Price>
{
    private String priceCode;

    private Amount amount;


    public Price( final String priceCode, final int amount, final int precision )
    {
        if ( priceCode == null || priceCode.isEmpty() )
        {
            throw new IllegalArgumentException( "Price code is required" );
        }

        this.priceCode = priceCode;
        this.amount    = new Amount( amount, precision );
    }

    @Override
    public int compareTo( final Price price )
    {
        if ( price == null )
        {
            throw new IllegalArgumentException( "Can not compare against (null) price" );
        }

        // ! Need to ensure Price types are the same
        // !   do conversion maybe before compare.
        if ( !this.priceCode.equals( price.priceCode ) )
        {
            throw new IllegalArgumentException( String.format( "Incompatible price codes %s and %s"
                                                              , priceCode, price.priceCode ) );
        }

        return this.amount.compareTo( price.amount );
    }


    public Price add( final Price arg )
    {
        if ( !isCompatible( arg ) )
        {
            throw new IllegalArgumentException( "Can not add incompatible prices" );
        }

        this.amount.add( arg.amount );
        return this;
    }

    public Price subtract( final Price arg )
    {
        if ( !isCompatible( arg ) )
        {
            throw new IllegalArgumentException( "Can not subtract incompatible prices" );
        }

        this.amount.subtract( arg.amount );
        return this;
    }


    private boolean isCompatible( final Price rhs )
    {
        if ( rhs == null )
        {
            throw new IllegalArgumentException( "Incompatible Price (null)" );
        }

        // Verify they are the same class - a common parent does not count
        if ( this.getClass().equals( rhs.getClass() ) )
        {
            return this.priceCode.equals( rhs.priceCode );
        }

        return false;
    }
}
