package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class AmountTest
{
    // @Test
    // public void basicConstructor()
    // {
    //     Currency c = new Currency( 3, 1234567890 );

    //     assertEquals( c.getCurrencyCode() );
    //     assertEquals( 3, c.getDecimalPrecision() );
    //     assertEquals( 1234567890, c.getAmount() );
    // }

    // @Test
    // public void basicConstructorAlternate()
    // {
    //     Currency c = new Currency( "foo", 2, 4321 );

    //     assertEquals( "foo", c.getCurrencyCode() );
    //     assertEquals( 2, c.getDecimalPrecision() );
    //     assertEquals( 4321, c.getAmount() );
    // }

    // @Test
    // public void nullCurrencyCodeNotAllowed()
    // {
    //     Throwable t = assertThrows( IllegalArgumentException.class
    //                                ,() -> {
    //                                 new Currency( null, 3, 1234567890 );
    //                                });

    //     assertEquals( "Currency code is required" , t.getMessage());
    // }



    // ----- Addition -----
    @Test
    public void addPositiveValue()
    {
        Amount total  = new Amount( 1234567890, 3 );
        Amount addend = new Amount( 5, 3 );

        total.add( addend );

        assertEquals( 3, total.getDecimalPrecision() );
        assertEquals( 1234567895, total.getAmount() );
    }

    @Test
    public void addNullValue()
    {
        Amount total  = new Amount( 1234567890, 3 );
        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.add( null );
                                   });

        assertEquals( "Incompatible (null) amount", t.getMessage());
    }

    @Test
    public void addAddIncompatibleValue()
    {
        Amount total   = new Amount( 1234567890, 3 );
        Amount addend  = new Amount( 5, 2 );

        
        Throwable t = assertThrows( ArithmeticException.class
                                   ,() -> {
                                      total.add( addend );
                                   });

        assertEquals( "Precision mismatch 3 != 2", t.getMessage() );
    }


    // @Test
    // public void basicConstructor()
    // {
    //     Throwable throwable = assertThrows( IllegalArgumentException.class
    //                                        ,() -> {
    //                                            AirlineCodeFactory.build( null );
    //                                         });

    //     assertEquals( "Airline code is required", throwable.getMessage() );
    // }



    // ----- Subtraction -----
    @Test
    public void subtractPositiveValue()
    {
        Amount total  = new Amount( 1234567890, 3 );
        Amount addend = new Amount( 5, 3 );

        total.subtract( addend );

        assertEquals( 3, total.getDecimalPrecision() );
        assertEquals( 1234567885, total.getAmount() );
    }

    @Test
    public void subtractNullValue()
    {
        Amount total  = new Amount( 1234567890, 3 );
        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.subtract( null );
                                   });

        assertEquals( "Incompatible (null) amount", t.getMessage());
    }

    @Test
    public void addSubtractIncompatibleValue()
    {
        Amount total   = new Amount( 1234567890, 3 );
        Amount subtend = new Amount( 5, 2 );

        
        Throwable t = assertThrows( ArithmeticException.class
                                   ,() -> {
                                      total.subtract( subtend );
                                   });

        assertEquals( "Precision mismatch 3 != 2", t.getMessage() );
    }


    // ----- Comparison -----
    @Test
    public void compareEquivalentValues()
    {
        Amount valueA  = new Amount( 1234567890, 3 );
        Amount valueB  = new Amount( 1234567890, 3 );

        assertEquals( 0, valueA.compareTo( valueB ));
    }

    @Test
    public void compareSameInstance()
    {
        Amount total  = new Amount( 1234567890, 3 );

        assertEquals( 0, total.compareTo( total ));
    }

    @Test
    public void compareAgainstNullFails()
    {
        Amount total  = new Amount( 1234567890, 3 );

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      total.compareTo( null );
                                   });

        assertEquals( "Can not compare against (null) amount", t.getMessage() );
    }

    @Test
    public void compareDissimilarPrecision()
    {
        Amount valueA  = new Amount( 1234567890, 3 );
        Amount valueB  = new Amount( 1234567890, 2 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows( ArithmeticException.class
                                   ,() -> {
                                      valueA.compareTo( valueB );
                                   });

        assertEquals( "Precision mismatch (3) and (2)", t.getMessage() );
    }

    // @Test
    // public void compareDissimilarCurrencies()
    // {
    //     Amount valueA  = new Amount( 1234567890, 3 );
    //     Amount valueB  = new Amount( 1234567890, 3 );

    //     // TODO catch exception - until normalization of precision is implemented
    //     Throwable t = assertThrows( IllegalArgumentException.class
    //                                ,() -> {
    //                                   valueA.compareTo( valueB );
    //                                });

    //     assertTrue( t.getMessage().contains("Incompatible amounts") );
    // }


    // @Test
    // public void compareDissimilarObjects()
    // {
    //     Currency valueA  = new Currency( 3, 1234567890 );
    //     String   valueB  = new String("Incompatible");

    //     assertEquals( 0, valueA.compareTo( valueB ));
    // }

    @Test
    public void compareAgainstSmallerValue()
    {
        Amount valueA  = new Amount( 1234567890, 3 );
        Amount valueB  = new Amount( 1111111111, 3 );

        assertTrue( valueA.compareTo( valueB ) > 0);
    }


    @Test
    public void compareAgainstLargerValue()
    {
        Amount valueA  = new Amount( 123456789, 3 );
        Amount valueB  = new Amount( 222222222, 3 );

        assertTrue( valueA.compareTo( valueB ) < 0);
    }

}