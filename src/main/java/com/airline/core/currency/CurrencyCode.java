package com.airline.core.currency;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.RequiredArgsConstructor;
// import lombok.Value;
import lombok.NonNull;

/**
 * ISO 4217 currency names.
 */
@Data
// @Value
// class CurrencyAlphaCode
public abstract class CurrencyCode<T>
{
    private final T code;

    public CurrencyCode( @NonNull final T isoCountryCode )
    {
        if ( !isCurrencyCodeValid( isoCountryCode ))
        {
            throw new IllegalArgumentException("Invalid ISO 4217 currency code '" + isoCountryCode + "'");
        }

        this.code = isoCountryCode;
    }

    /**
     * Verify the structure of a country code.  A validation predicate is
     * mandatory for a derived class.
     * 
     * @param isoCountryCode the code to validate.
     * @return true on sucessful validation
     */
    abstract protected boolean isCodeValid( final T isoCountryCode );

    private boolean isCurrencyCodeValid( final T isoCountryCode )
    {
        return isCodeValid( isoCountryCode );
    }
}





// @Data
// @AllArgsConstructor
// @RequiredArgsConstructor
// public class CurrencyCode
// {
//     // ISO alphabetic code (3 alphabetic characters - upper case only)
//     private final CurrencyAlphaCode code;       // "alphabetic code"

//     // ISO numeric code (3 digits with leading zeros)
//     private String number;      // "numeric code"

//     // Number of digits to the right of the decimal point
//     private final int minorUnit;        // "Minor unit"

//     public CurrencyCode( final String alphaCode )
//     {
//         code           = new CurrencyAlphaCode( alphaCode );
//         // must be a positive value
//     }
// }

