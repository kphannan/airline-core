package com.airline.core.currency;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.Value;

// @Value
@Data
// @AllArgsConstructor
public class Price implements Comparable<Price>
{
    private String priceCode;

    private int    decimalPrecision;

    private int    amount;

    public Price( final String priceCode, final int precision, final int amount )
    {
        if ( priceCode == null || priceCode.isEmpty() )
            throw new IllegalArgumentException("Price code is required" );

        this.priceCode        = priceCode;
        this.decimalPrecision = precision;
        this.amount           = amount;
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
        if ( !this.priceCode.equals( price.priceCode ))
        {
            throw new IllegalArgumentException(String.format("Incompatible price codes %s and %s", priceCode, price.priceCode ));
        }

        // ! Need to normalize the precision before compare
        if ( this.decimalPrecision != price.decimalPrecision )
        {
            throw new IllegalArgumentException(String.format( "Precision mismatch %s(%d) and %s(%d)",
                                                              priceCode, decimalPrecision,
                                                              price.priceCode, price.decimalPrecision ));
        }


        return this.amount - price.amount;
    }


    public Price add( final Price arg )
    {
        if ( !isCompatible(arg) )
        {
            throw new IllegalArgumentException("Can not add incompatible prices");
        }

        // TODO Normalize amount based on precision
        this.amount += arg.amount;
        return this;
    }

    public Price subtract( final Price arg )
    {
        if ( !isCompatible(arg) )
        {
            throw new IllegalArgumentException("Can not subtract incompatible prices");
        }

        // TODO Normalize amount based on precision
        this.amount -= arg.amount;
        return this;
    }


    private boolean isCompatible( final Price rhs )
    {
        if ( rhs == null )
            throw new IllegalArgumentException( "Incompatible Price (null)" );

        // Verify they are the same class - a common parent does not count
        if ( this.getClass().equals(rhs.getClass()) )
        {
            if (decimalPrecision == rhs.decimalPrecision )
                return true;
        }

        return false;
    }
}




