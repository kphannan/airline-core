package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class CrossCurrencyTest
{
    @Test
    public void verifyCashAndMilesCannotBeCompared()
    {
        Miles miles = new Miles( "LOY", 3, 1234567890 );
        Cash  cash  = new Cash( "GBP", 3, 1234567890 );

        Throwable t = assertThrows( CurrencyException.class
                                   ,() -> {miles.compareTo(cash);}
                                   ,"CurrencyException not thrown when expected"
                                  );

        assertEquals( "Incompatible currencies LOY and GBP" , t.getMessage()
                     ,"Incorrect exception message");
    }

    
    @Test
    public void verifyCashAndMilesCanNotBeAdded()
    {
        Miles miles = new Miles( "LOY", 3, 1234567890 );
        Cash  cash  = new Cash( "GBP", 3, 1234567890 );
    
        Throwable t = assertThrows( CurrencyException.class
                                   ,() -> {miles.add(cash);}
                                   ,"CurrencyException not thrown when expected"
                                  );
    
        assertEquals( "Can not add incompatible currencies" , t.getMessage()
                     ,"Incorrect exception message");
    }
}
    
