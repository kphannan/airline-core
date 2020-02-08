package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class CrossPriceTest
{
    @Test
    public void verifyCashAndMilesCannotBeCompared()
    {
        MilesPrice miles = new MilesPrice( "loy", 3, 1234567890 );
        CashPrice  cash  = new CashPrice( "GBP", 3, 1234567890 );

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    miles.compareTo(cash);
                                   });

        assertEquals( "Incompatible price codes loy and GBP" , t.getMessage());
    }

    
    @Test
    public void verifyCashAndMilesCanNotBeAdded()
    {
        MilesPrice miles = new MilesPrice( "loy", 3, 1234567890 );
        CashPrice  cash  = new CashPrice( "GBP", 3, 1234567890 );
    
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    miles.add(cash);
                                   });
    
        assertEquals( "Can not add incompatible prices" , t.getMessage());
    }
}
    
