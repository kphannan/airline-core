package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class CurrencyTest
{
    @Test
    public void basicConstructor()
    {
        Currency c = new Currency( "cur", 3, 1234567890 );

        assertEquals( "cur", c.getCurrencyCode() );
        assertEquals( 3, c.getAmount().getDecimalPrecision() );
        assertEquals( 1234567890, c.getAmount().getAmount() );
    }

    @Test
    public void basicConstructorAlternate()
    {
        Currency c = new Currency( "foo", 2, 4321 );

        assertEquals( "foo", c.getCurrencyCode() );
        assertEquals( 2, c.getAmount().getDecimalPrecision() );
        assertEquals( 4321, c.getAmount().getAmount() );
    }

    @Test
    public void nullCurrencyCodeNotAllowed()
    {
        Throwable t = assertThrows( IllegalArgumentException.class,
                                   () -> {
                                    new Currency( null, 3, 1234567890 );
                                   });

        assertEquals( "Currency code is required", t.getMessage());
    }

    @Test
    public void blankCurrencyCodeNotAllowed()
    {
        Throwable t = assertThrows( IllegalArgumentException.class,
                                   () -> {
                                    new Currency( "", 3, 1234567890 );
                                   });

        assertEquals( "Currency code is required", t.getMessage());
    }


    // ----- Addition -----
    @Test
    public void addPositiveValue()
    {
        Currency total  = new Currency( "cur", 3, 1234567890 );
        Currency addend = new Currency( "cur", 3, 5 );

        total.add( addend );

        assertEquals( "cur", total.getCurrencyCode() );
        assertEquals( 3, total.getAmount().getDecimalPrecision() );
        assertEquals( 1234567895, total.getAmount().getAmount() );
    }

    @Test
    public void addNullValue()
    {
        Currency total  = new Currency( "cur", 3, 1234567890 );
        
        Throwable t = assertThrows( IllegalArgumentException.class,
                                   () -> {
                                      total.add( null );
                                   });

        assertEquals( "Incompatible currency (null)", t.getMessage());
    }

    @Test
    public void addAddIncompatibleValue()
    {
        Currency total   = new Currency( "cur", 3, 1234567890 );
        Currency addend  = new Currency( "cur", 2, 5 );

        
        Throwable t = assertThrows( IllegalArgumentException.class,
                                   () -> {
                                      total.add( addend );
                                   });

        assertTrue( t.getMessage().contains("Can not add incompatible currencies") );
    }



    // ----- Subtraction -----
    @Test
    public void subtractPositiveValue()
    {
        Currency total  = new Currency( "cur", 3, 1234567890 );
        Currency addend = new Currency( "cur", 3, 5 );

        total.subtract( addend );

        assertEquals( "cur", total.getCurrencyCode() );
        assertEquals( 3, total.getAmount().getDecimalPrecision() );
        assertEquals( 1234567885, total.getAmount().getAmount() );
    }

    @Test
    public void addSubtractIncompatibleValue()
    {
        Currency total   = new Currency( "cur", 3, 1234567890 );
        Currency subtend = new Currency( "cur", 2, 5 );

        
        Throwable t = assertThrows( IllegalArgumentException.class,
                                   () -> {
                                      total.subtract( subtend );
                                   });

        assertEquals( "Can not subtract incompatible currencies", t.getMessage() );
    }


    // ----- Comparison -----
    @Test
    public void compareEquivalentValues()
    {
        Currency valueA  = new Currency( "cur", 3, 1234567890 );
        Currency valueB  = new Currency( "cur", 3, 1234567890 );

        assertEquals( 0, valueA.compareTo( valueB ));
    }

    @Test
    public void compareSameInstance()
    {
        Currency total  = new Currency( "cur", 3, 1234567890 );

        assertEquals( 0, total.compareTo( total ));
    }

    @Test
    public void compareAgainstNullFails()
    {
        Currency total  = new Currency( "cur", 3, 1234567890 );

        Throwable t = assertThrows( IllegalArgumentException.class,
                                   () -> {
                                      total.compareTo( null );
                                   });

        assertTrue( t.getMessage().contains("Can not compare against (null) currency") );
    }

    @Test
    public void compareDissimilarPrecision()
    {
        Currency valueA  = new Currency( "cur", 4, 1234567890 );
        Currency valueB  = new Currency( "cur", 3, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows( ArithmeticException.class,
                                   () -> {
                                      valueA.compareTo( valueB );
                                   });

        assertEquals( "Precision mismatch 4 != 3", t.getMessage() );
    }

    @Test
    public void compareDissimilarCurrencies()
    {
        Currency valueA  = new Currency( "cur", 3, 1234567890 );
        Currency valueB  = new Currency( "ruc", 3, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {
                                      valueA.compareTo( valueB );
                                   });

        assertEquals( "Incompatible currencies cur and ruc", t.getMessage() );
    }

    

    @Test
    public void compareAgainstSmallerValue()
    {
        Currency valueA  = new Currency( "cur", 3, 1234567890 );
        Currency valueB  = new Currency( "cur", 3, 1111111111 );

        assertTrue( valueA.compareTo( valueB ) > 0 );
    }


    @Test
    public void compareAgainstLargerValue()
    {
        Currency valueA  = new Currency( "cur", 3, 123456789 );
        Currency valueB  = new Currency( "cur", 3, 222222222 );

        assertTrue( valueA.compareTo( valueB ) < 0 );
    }

}