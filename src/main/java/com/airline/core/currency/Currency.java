package com.airline.core.currency;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.Value;

// @Value
@Data
// @AllArgsConstructor
public class Currency implements Comparable<Currency>
{
    private String currencyCode;

    private int decimalPrecision;

    private int amount;

    public Currency( final String currencyCode, final int precision, final int amount )
    {
        if ( currencyCode == null || currencyCode.isEmpty() )
            throw new IllegalArgumentException("Currency code is required" );

        this.currencyCode     = currencyCode;
        this.decimalPrecision = precision;
        this.amount           = amount;
    }

    @Override
    public int compareTo( final Currency currency )
    {
        if ( currency == null )
        {
            throw new IllegalArgumentException( "Can not compare against (null) currency" );
        }

        // ! Need to ensure currency types are the same
        // !   do conversion maybe before compare.
        if ( !this.currencyCode.equals( currency.currencyCode ))
        {
            throw new IllegalArgumentException(String.format("Incompatible currencies %s and %s", currencyCode, currency.currencyCode ));
        }

        // ! Need to normalize the precision before compare
        if ( this.decimalPrecision != currency.decimalPrecision )
        {
            throw new IllegalArgumentException(String.format( "Precision mismatch %s(%d) and %s(%d)",
                                                              currencyCode, decimalPrecision,
                                                              currency.currencyCode, currency.decimalPrecision ));
        }


        return this.amount - currency.amount;
    }


    public Currency add( final Currency arg )
    {
        if ( !isCompatible(arg) )
        {
            throw new IllegalArgumentException("Can not add incompatible currencies");
        }

        // TODO Normalize amount based on precision
        this.amount += arg.amount;
        return this;
    }

    public Currency subtract( final Currency arg )
    {
        if ( !isCompatible(arg) )
        {
            throw new IllegalArgumentException("Can not subtract incompatible currencies");
        }

        // TODO Normalize amount based on precision
        this.amount -= arg.amount;
        return this;
    }


    private boolean isCompatible( final Currency rhs )
    {
        if ( rhs == null )
            throw new IllegalArgumentException( "Incompatible currency (null)" );

        // Verify they are the same class - a common parent does not count
        if ( this.getClass().equals(rhs.getClass()) )
        {
            if (decimalPrecision == rhs.decimalPrecision )
                return true;
        }

        return false;
    }
}




