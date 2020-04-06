package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class AmountTest
{

    // ----- Addition -----
    @Test
    public void addPositiveValue()
    {
        Amount total  = new Amount( 1234567890, 3 );
        Amount addend = new Amount( 5, 3 );

        total.add( addend );

        assertAll( "Money internal state"
                  ,() -> assertEquals( 3, total.getDecimalPrecision()
                                      ,"Precision altered in calculation" )
                  ,() -> assertEquals( 1234567895, total.getValue()
                                      ,"sum of 2 values is incorrect" )
                 );
    }

    @Test
    public void addNullValue()
    {
        Amount total  = new Amount( 1234567890, 3 );
        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {total.add( null );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Incompatible (null) amount", t.getMessage()
                     ,"Incorrect exception message text" );
    }

    @Test
    public void addAddIncompatibleValue()
    {
        Amount total   = new Amount( 1234567890, 3 );
        Amount addend  = new Amount( 5, 2 );
        
        Throwable t = assertThrows( ArithmeticException.class
                                   ,() -> {total.add( addend );}
                                   ,"ArithmeticException not thrown when expected"
                                  );

        assertEquals( "Precision mismatch 3 != 2", t.getMessage()
                     ,"Exception message should identify precision mismatch" );
    }


    // ----- Subtraction -----
    @Test
    public void subtractPositiveValue()
    {
        Amount total  = new Amount( 1234567890, 3 );
        Amount addend = new Amount( 5, 3 );

        total.subtract( addend );

        assertAll( "Money internal state"
                  ,() -> assertEquals( 3, total.getDecimalPrecision(), "precision does not match" )
                  ,() -> assertEquals( 1234567885, total.getValue(), "fixed numeric value is incorrect" )
                 );
    }

    @Test
    public void subtractNullValue()
    {
        Amount total  = new Amount( 1234567890, 3 );
        
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {total.subtract( null );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Incompatible (null) amount", t.getMessage()
                     ,"Unexpected exception message");
    }

    @Test
    public void addSubtractIncompatibleValue()
    {
        Amount total   = new Amount( 1234567890, 3 );
        Amount subtend = new Amount( 5, 2 );

        
        Throwable t = assertThrows( ArithmeticException.class
                                   ,() -> {total.subtract( subtend );}
                                   ,"ArithmeticException not thrown when expected"
                                  );

        assertEquals( "Precision mismatch 3 != 2", t.getMessage()
                     ,"Incorrect exception message" );
    }


    // ----- Comparison -----
    @Test
    public void compareEquivalentValues()
    {
        Amount valueA  = new Amount( 1234567890, 3 );
        Amount valueB  = new Amount( 1234567890, 3 );

        assertEquals( 0, valueA.compareTo( valueB )
                     ,"different instances with similar values should compare equally");
    }

    @Test
    public void compareSameInstance()
    {
        Amount total  = new Amount( 1234567890, 3 );

        assertEquals( 0, total.compareTo( total ), "an instance should compare to itself");
    }

    @Test
    public void compareAgainstNullFails()
    {
        Amount total  = new Amount( 1234567890, 3 );

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {total.compareTo( null );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Can not compare against (null) amount", t.getMessage(), "Incorrect exception message" );
    }

    @Test
    public void compareDissimilarPrecision()
    {
        Amount valueA  = new Amount( 1234567890, 3 );
        Amount valueB  = new Amount( 1234567890, 2 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows( ArithmeticException.class
                                   ,() -> {valueA.compareTo( valueB );}
                                   ,"ArithmeticException not thrown when expected"
                                  );

        assertEquals( "Precision mismatch 3 != 2", t.getMessage(), "Exception message is not correct" );
    }


    @Test
    public void compareAgainstSmallerValue()
    {
        Amount valueA  = new Amount( 1234567890, 3 );
        Amount valueB  = new Amount( 1111111111, 3 );

        assertTrue( valueA.compareTo( valueB ) > 0, "Collation order is reversed"  );
    }


    @Test
    public void compareAgainstLargerValue()
    {
        Amount valueA  = new Amount( 123456789, 3 );
        Amount valueB  = new Amount( 222222222, 3 );

        assertTrue( valueA.compareTo( valueB ) < 0, "Collation order is not correct" );
    }

}