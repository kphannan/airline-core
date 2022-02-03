package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


/**
 * Unit tests of Amount class
 */
@SuppressWarnings( { "PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals" } )
class AmountTest
{
    // ----- Addition -----
    @Test
    void addPositiveValue()
    {
        final Amount total  = new Amount( 1_234_567_800, 3 );
        final Amount addend = new Amount( 5, 3 );

        final Amount result = total.add( addend );

        assertAll(  "Money internal state"
                  , () -> assertEquals(  3, total.getDecimalPrecision()
                                       , "Precision altered in calculation" )
                  , () -> assertEquals(  1_234_567_805, total.getValue()
                                       , "sum of 2 values is incorrect" )
                  , () -> assertSame( total, result )
                  , () -> assertEquals( 1_234_567_805, result.getValue() )
                );
    }

    @Test
    void addNullValue()
    {
        Amount total  = new Amount( 1_234_567_890, 3 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.add( null ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Incompatible (null) amount", t.getMessage()
                     , "Incorrect exception message text" );
    }

    @Test
    void addAddIncompatibleValue()
    {
        Amount total   = new Amount( 1_234_567_890, 3 );
        Amount addend  = new Amount( 5, 2 );

        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { total.add( addend ); }
                                   , "ArithmeticException not thrown when expected"
                                  );

        assertEquals(  "Precision mismatch 3 != 2", t.getMessage()
                     , "Exception message should identify precision mismatch" );
    }


    // ----- Subtraction -----
    @Test
    void subtractPositiveValue()
    {
        final Amount total  = new Amount( 123_4567_890, 3 );
        final Amount addend = new Amount( 5, 3 );

        final Amount result = total.subtract( addend );

        assertAll(  "Money internal state"
                  , () -> assertEquals( 3, total.getDecimalPrecision(), "precision does not match" )
                  , () -> assertEquals( 1_234_567_885, total.getValue(), "fixed numeric value is incorrect" )
                  , () -> assertSame( total, result )
                 );
    }

    @Test
    void subtractNullValue()
    {
        Amount total  = new Amount( 1_234_567_890, 3 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.subtract( null ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Incompatible (null) amount", t.getMessage()
                     , "Unexpected exception message");
    }

    @Test
    void addSubtractIncompatibleValue()
    {
        Amount total   = new Amount( 1_234_567_890, 3 );
        Amount subtend = new Amount( 5, 2 );


        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { total.subtract( subtend ); }
                                   , "ArithmeticException not thrown when expected"
                                  );

        assertEquals(  "Precision mismatch 3 != 2", t.getMessage()
                     , "Incorrect exception message" );
    }


    // ----- Comparison -----
    @Test
    void compareEquivalentValues()
    {
        Amount valueA  = new Amount( 1_234_567_890, 3 );
        Amount valueB  = new Amount( 1_234_567_890, 3 );

        assertEquals(  0, valueA.compareTo( valueB )
                     , "different instances with similar values should compare equally");
    }

    @Test
    void compareSameInstance()
    {
        Amount total  = new Amount( 1_234_567_890, 3 );

        assertEquals( 0, total.compareTo( total ), "an instance should compare to itself" );
    }

    @Test
    void compareAgainstNullFails()
    {
        final Amount total  = new Amount( 1_234_567_890, 3 );

        final Throwable t = assertThrows(  IllegalArgumentException.class
                                         , () -> { total.compareTo( null ); }
                                         , "IllegalArgumentException not thrown when expected"
                                        );

        assertEquals( "Can not compare against (null) amount", t.getMessage(), "Incorrect exception message" );
    }

    @Test
    void compareDissimilarPrecision()
    {
        final Amount valueA  = new Amount( 1_234_567_890, 3 );
        final Amount valueB  = new Amount( 1_234_567_890, 2 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { valueA.compareTo( valueB ); }
                                   , "ArithmeticException not thrown when expected"
                                  );

        assertEquals( "Precision mismatch 3 != 2", t.getMessage(), "Exception message is not correct" );
    }


    @Test
    void compareAgainstSmallerValue()
    {
        final Amount valueA  = new Amount( 1_234_567_890, 3 );
        final Amount valueB  = new Amount( 1_111_111_111, 3 );

        assertTrue( valueA.compareTo( valueB ) > 0, "Collation order is reversed"  );
    }


    @Test
    void compareAgainstLargerValue()
    {
        final Amount valueA  = new Amount( 123_456_789, 3 );
        final Amount valueB  = new Amount( 222_222_222, 3 );

        assertTrue( valueA.compareTo( valueB ) < 0, "Collation order is not correct" );
    }


    @Test
    void amount_equalsDifferentAmount_returnsFalse()
    {
        final Amount valueA  = new Amount( 123_456_789, 3 );
        final Amount valueB  = new Amount( 222_222_222, 3 );

        assertFalse( valueA.equals( valueB ), "Collation order is not correct" );
    }

    @Test
    void amount_equalsSimilarAmount_returnsTrue()
    {
        final Amount valueA  = new Amount( 123_456_789, 3 );
        final Amount valueB  = new Amount( 123_456_789, 3 );

        assertTrue( valueA.equals( valueB ), "Collation order is not correct" );
    }
}