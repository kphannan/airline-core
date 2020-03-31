package com.airline.core.currency;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.Value;

//! Refactor this to a Money class
//! Create a CurrencyCode class for DDD / type safety
//! TODO create CurrencyConverter class Money currencyConversion(currencyCode, Money)
// @Value
@Data
// @AllArgsConstructor
public class Money implements Comparable<Money>
{
    private CurrencyAlphaCode currencyCode;

    private Amount amount;


    public Money( final CurrencyAlphaCode currencyCode, final int precision, final int amount )
    {
        if ( currencyCode == null )
        {
            throw new IllegalArgumentException("Currency code is required" );
        }
        this.currencyCode     = currencyCode;
        this.amount           = new Amount( amount, precision );
    }

    public Money( final String currencyCode, final int precision, final int amount )
    {
        if ( currencyCode == null || currencyCode.isEmpty() )
        {
            throw new IllegalArgumentException("Currency code is required" );
        }

        this.currencyCode     = new CurrencyAlphaCode( currencyCode );
        this.amount           = new Amount( amount, precision );
    }

    @Override
    public int compareTo( final Money currency )
    {
        if ( currency == null )
        {
            throw new IllegalArgumentException( "Can not compare against (null) currency" );
        }

        // ! Need to ensure currency types are the same
        // !   do conversion maybe before compare.
        if ( !this.currencyCode.equals( currency.currencyCode ))
        {
            throw new IllegalArgumentException(String.format("Incompatible currencies %s and %s"
                                                             ,currencyCode, currency.currencyCode ));
        }

        return this.amount.compareTo(currency.amount);
    }


    /**
     * Add a money value to the current Money value.
     * 
     * @param arg the Money subtractor
     * @return the new value of current Money.
     */
    public Money add( final Money arg )
    {
        if ( !isCompatible(arg) )
        {
            throw new IllegalArgumentException("Can not add incompatible currencies");
        }

        this.amount.add( arg.amount );
        return this;
    }

    /**
     * Subtract a money value from the current Money value.
     * 
     * @param arg the Money subtractor
     * @return the new value of current Money.
     */
    public Money subtract( final Money arg )
    {
        if ( !isCompatible(arg) )
        {
            throw new IllegalArgumentException("Can not subtract incompatible currencies");
        }

        this.amount.subtract( arg.amount );
        return this;
    }


    private boolean isCompatible( final Money rhs )
    {
        if ( rhs == null )
        {
            throw new IllegalArgumentException( "Incompatible currency (null)" );
        }

        // Verify they are the same class - a common parent does not count
        if ( this.getClass().equals(rhs.getClass()) )
        {
            return amount.getDecimalPrecision() == rhs.getAmount().getDecimalPrecision();
        }

        return false;
    }
}
