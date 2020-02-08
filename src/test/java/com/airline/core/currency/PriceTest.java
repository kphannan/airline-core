package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class PriceTest
{
    @Test
    public void basicConstructor()
    {
        Price c = new Price( "cur", 3, 1234567890 );

        assertEquals( "cur", c.getPriceCode() );
        assertEquals( 3, c.getDecimalPrecision() );
        assertEquals( 1234567890, c.getAmount() );
    }

    @Test
    public void basicConstructorAlternate()
    {
        Price c = new Price( "foo", 2, 4321 );

        assertEquals( "foo", c.getPriceCode() );
        assertEquals( 2, c.getDecimalPrecision() );
        assertEquals( 4321, c.getAmount() );
    }

    @Test
    public void nullPriceCodeNotAllowed()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    new Price( null, 3, 1234567890 );
                                   });

        assertEquals( "Price code is required" , t.getMessage());
    }

    @Test
    public void blankPriceCodeNotAllowed()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                    new Price( "", 3, 1234567890 );
                                   });

        assertEquals( "Price code is required" , t.getMessage());
    }


    // ----- Addition -----
    @Test
    public void addPositiveValue()
    {
        Price total  = new Price( "cur", 3, 1234567890 );
        Price addend = new Price( "cur", 3, 5 );

        total.add( addend );

        assertEquals( "cur", total.getPriceCode() );
        assertEquals( 3, total.getDecimalPrecision() );
        assertEquals( 1234567895, total.getAmount() );
    }

    @Test
    public void addNullValue()
    {
        Price total  = new Price( "cur", 3, 1234567890 );
        // Price addend = new Price( "cur", 3, 5 );

        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.add( null );
                                   });

        assertEquals( "Incompatible Price (null)", t.getMessage());
    }

    @Test
    public void addAddIncompatibleValue()
    {
        Price total   = new Price( "cur", 3, 1234567890 );
        Price addend  = new Price( "cur", 2, 5 );

        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.add( addend );
                                   });

        assertTrue( t.getMessage().contains("Can not add incompatible prices") );
    }




    // ----- Subtraction -----
    @Test
    public void subtractPositiveValue()
    {
        Price total  = new Price( "cur", 3, 1234567890 );
        Price addend = new Price( "cur", 3, 5 );

        total.subtract( addend );

        assertEquals( "cur", total.getPriceCode() );
        assertEquals( 3, total.getDecimalPrecision() );
        assertEquals( 1234567885, total.getAmount() );
    }

    @Test
    public void addSubtractIncompatibleValue()
    {
        Price total   = new Price( "cur", 3, 1234567890 );
        Price subtend = new Price( "cur", 2, 5 );

        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.subtract( subtend );
                                   });

        assertTrue( t.getMessage().contains("Can not subtract incompatible prices") );
    }


    // ----- Comparison -----
    @Test
    public void compareEquivalentValues()
    {
        Price valueA  = new Price( "cur", 3, 1234567890 );
        Price valueB  = new Price( "cur", 3, 1234567890 );

        assertEquals( 0, valueA.compareTo( valueB ));
    }

    @Test
    public void compareSameInstance()
    {
        Price total  = new Price( "cur", 3, 1234567890 );

        assertEquals( 0, total.compareTo( total ));
    }

    @Test
    public void compareAgainstNullFails()
    {
        Price total  = new Price( "cur", 3, 1234567890 );

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.compareTo( null );
                                   });

        assertTrue( t.getMessage().contains("Can not compare against (null) price") );
    }

    @Test
    public void compareDissimilarPrecision()
    {
        Price valueA  = new Price( "cur", 3, 1234567890 );
        Price valueB  = new Price( "cur", 2, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      valueA.compareTo( valueB );
                                   });

        assertTrue( t.getMessage().contains("Precision mismatch cur(3) and cur(2)") );
    }

    @Test
    public void compareDissimilarCurrencies()
    {
        Price valueA  = new Price( "cur", 3, 1234567890 );
        Price valueB  = new Price( "ruc", 3, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      valueA.compareTo( valueB );
                                   });

        assertTrue( t.getMessage().contains("Incompatible price codes cur and ruc") );
    }
    // @Test
    // public void compareDissimilarObjects()
    // {
    //     Price valueA  = new Price( "cur", 3, 1234567890 );
    //     String   valueB  = new String("Incompatible");

    //     assertEquals( 0, valueA.compareTo( valueB ));
    // }

    @Test
    public void compareAgainstSmallerValue()
    {
        Price valueA  = new Price( "cur", 3, 1234567890 );
        Price valueB  = new Price( "cur", 3, 1111111111 );

        assertTrue( valueA.compareTo( valueB ) > 0);
    }


    @Test
    public void compareAgainstLargerValue()
    {
        Price valueA  = new Price( "cur", 3, 123456789 );
        Price valueB  = new Price( "cur", 3, 222222222 );

        assertTrue( valueA.compareTo( valueB ) < 0);
    }

}