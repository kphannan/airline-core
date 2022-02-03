package com.airline.core.currency;

import lombok.Value;
import lombok.experimental.NonFinal;


//! Refactor this to a Money class
//! Create a CurrencyCode class for DDD / type safety
//! TODO create CurrencyConverter class Money currencyConversion(currencyCode, Money)
/**
 * An amount of money in a specific currency.
 */
@Value
@NonFinal
// @Data
// @AllArgsConstructor
// @Setter( AccessLevel.PRIVATE )
public class Money implements Comparable<Money>
{
    private CurrencyAlphaCode currencyCode;

    private Amount amount;


    /**
     * Create an amount of Money in a specific currency.
     *
     * @param currencyCode the currency.
     * @param precision number of fractional digits.
     * @param amount the fixed point amount.
     */
    public Money( final CurrencyAlphaCode currencyCode, final int amount, final int precision )
    {
        if ( currencyCode == null )
        {
            throw new IllegalArgumentException( "Currency code is required" );
        }
        this.currencyCode     = currencyCode;
        this.amount           = new Amount( amount, precision );
    }

    /**
     * Create an amount of Money in a specific currency.
     *
     * @param currencyCode the currency.
     * @param precision number of fractional digits.
     * @param amount the fixed point amount.
     */
    public Money( final String currencyCode, final int amount, final int precision )
    {
        this( new  CurrencyAlphaCode( currencyCode ), amount, precision );
    }

    @Override
    public int compareTo( final Money rhs )
    {
        if ( rhs == null )
        {
            throw new IllegalArgumentException( "Can not compare against (null) currency" );
        }

        // ! Need to ensure currency types are the same
        // !   do conversion maybe before compare.
        if ( !isCompatible( rhs ) )
        {
            throw new CurrencyException( String.format(  "Incompatible currencies %s and %s"
                                                       , currencyCode.getCode(), rhs.currencyCode.getCode() ) );
        }

        return this.amount.compareTo( rhs.amount );
    }


    /**
     * Add a money value to the current Money value.
     *
     * @param arg the Money subtractor
     * @return the new value of current Money.
     */
    public Money add( final Money arg )
    {
        if ( !isCompatible( arg ) )
        {
            throw new CurrencyException( "Can not add incompatible currencies" );
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
        if ( !isCompatible( arg ) )
        {
            throw new CurrencyException( "Can not subtract incompatible currencies" );
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

        if ( this.getClass().equals( rhs.getClass() ) )
        {
            return currencyCode.equals( rhs.currencyCode );
        }

        return false;
    }
}
