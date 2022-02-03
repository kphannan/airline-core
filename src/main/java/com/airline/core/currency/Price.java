package com.airline.core.currency;

import lombok.Value;
import lombok.experimental.NonFinal;


/**
 * Generic price, whose code is a CurrencyCode.
 */
@Value
@NonFinal
public class Price implements Comparable<Price>
{
    private String priceCode;

    private Amount amount;


    /**
     * Instantiate a Price.
     * @param priceCode
     * @param amount
     * @param precision
     */
    protected Price( final String priceCode, final int amount, final int precision )
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


    /**
     * Add one price to another, with the target updated.
     *
     * @param arg the price to add.
     * @return this instance with the value changed.
     */
    public Price add( final Price arg )
    {
        if ( !isCompatible( arg ) )
        {
            throw new IllegalArgumentException( "Can not add incompatible prices" );
        }

        this.amount.add( arg.amount );
        return this;
    }

    /**
     * Subtract a price from this target price.
     *
     * @param arg the price to subtract.
     * @return this instance with the value changed.
     */
    public Price subtract( final Price arg )
    {
        if ( !isCompatible( arg ) )
        {
            throw new IllegalArgumentException( "Can not subtract incompatible prices" );
        }

        this.amount.subtract( arg.amount );
        return this;
    }


    /**
     * Determine if a price is of the same type and currency as this.
     *
     * @param rhs the price to check compatibility.
     * @return true if compatible, false otherwise.
     */
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
