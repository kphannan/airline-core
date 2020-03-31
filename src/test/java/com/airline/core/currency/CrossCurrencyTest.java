package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class CrossCurrencyTest
{
    @Test
    public void verifyCashAndMilesCannotBeCompared()
    {
        Miles miles = new Miles( "LOY", 3, 1234567890 );
        Cash  cash  = new Cash( "GBP", 3, 1234567890 );

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    miles.compareTo(cash);
                                   });

        assertEquals( "Incompatible currencies CurrencyCode(code=LOY) and CurrencyCode(code=GBP)" , t.getMessage());
    }

    
    @Test
    public void verifyCashAndMilesCanNotBeAdded()
    {
        Miles miles = new Miles( "LOY", 3, 1234567890 );
        Cash  cash  = new Cash( "GBP", 3, 1234567890 );
    
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    miles.add(cash);
                                   });
    
        assertEquals( "Can not add incompatible currencies" , t.getMessage());
    }
}
    
