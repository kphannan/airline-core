package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
class PriceTest
{
    @Test
    void basicConstructor()
    {
        Price c = new Price( "cur", 1_234_567_890, 3 );        // NOPMD  (DU - Anomaly)

        assertAll(  "Verify constructor sets multiple attributes properly"
                  , () -> assertEquals( "cur", c.getPriceCode(), "construct didn't set currency code" )
                  , () -> assertEquals( 3, c.getAmount().getDecimalPrecision(), "construct didn't set precision" )
                  , () -> assertEquals( 1234567890, c.getAmount().getValue(), "construct didn't set value properly" )
                 );
    }

    @Test
    void basicConstructorAlternate()
    {
        Price c = new Price( "foo", 4_321, 2 );          // NOPMD  (DU - Anomaly)

        assertAll(  "Verify constructor sets multiple attributes properly"
                  , () -> assertEquals( "foo", c.getPriceCode(), "construct didn't set currency code" )
                  , () -> assertEquals( 2, c.getAmount().getDecimalPrecision(), "construct didn't set precision" )
                  , () -> assertEquals( 4321, c.getAmount().getValue(), "construct didn't set value properly" )
                 );
    }

    @Test
    void nullPriceCodeNotAllowed()
    {
        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { new Price( null, 3, 1234567890 ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Price code is required" , t.getMessage()
                     , "Incorrect exception message" );
    }

    @Test
    void blankPriceCodeNotAllowed()
    {
        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { new Price( "", 1_234_567_890, 3 ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Price code is required" , t.getMessage()
                     , "Incorrect exception message" );
    }


    // ----- Addition -----
    @Test
    void addPositiveValue()
    {
        final Price total  = new Price( "mno", 1_234_567_890, 3 );
        final Price addend = new Price( "mno", 5, 3 );

        final Price result = total.add( addend );

        assertAll(  "Verify result of addition"
                  , () -> assertEquals(  "mno", total.getPriceCode()
                                       , "currency code changed incorrectly" )
                  , () -> assertEquals(  3, total.getAmount().getDecimalPrecision()
                                       , "precision somehow got altered")
                  , () -> assertEquals(  1_234_567_895, total.getAmount().getValue()
                                       , "result of addition is wrong" )
                  , () -> assertSame( total, result )
                  , () -> assertEquals( 1_234_567_895, result.getAmount().getValue() )
                 );
    }

    @Test
    void addNullValue()
    {
        Price total  = new Price( "pqr", 1_234_567_890, 3 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.add( null ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Incompatible Price (null)", t.getMessage()
                     , "Incorrect exception message" );
    }

    @Test
    void addAddIncompatibleValue()
    {
        Price total   = new Price( "cur", 1_234_567_890, 3 );
        Price addend  = new Price( "ruc", 5, 2 );


        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.add( addend ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Can not add incompatible prices", t.getMessage()
                     , "Incorrect exception message" );
    }




    // ----- Subtraction -----
    @Test
    void subtractPositiveValue()
    {
        final Price total  = new Price( "abc", 1_234_567_890, 3 );
        final Price addend = new Price( "abc", 5, 3 );

        final Price result = total.subtract( addend );

        assertAll(  "Verify integrity of the total"
                  , () -> assertEquals( "abc", total.getPriceCode(), "Incorrect currency code" )
                  , () -> assertEquals( 3, total.getAmount().getDecimalPrecision(), "Precision incorrectly modified" )
                  , () -> assertEquals( 1_234_567_885, total.getAmount().getValue(), "subtraction failed" )
                  , () -> assertSame( total, result )
                 );
    }

    @Test
    void addSubtractIncompatibleValue()
    {
        Price total   = new Price( "cur", 1_234_567_890, 3 );
        Price subtend = new Price( "ruc", 5, 2 );


        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.subtract( subtend ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Can not subtract incompatible prices", t.getMessage()
                     , "Incorrect exception message" );
    }


    // ----- Comparison -----
    @Test
    void compareEquivalentValues()
    {
        final Price valueA  = new Price( "stu", 1_234_567_890, 3 );
        final Price valueB  = new Price( "stu", 1_234_567_890, 3 );

        assertEquals( 0, valueA.compareTo( valueB )
                     ,"Numerically equivalent instances should compare equally"
                    );
    }

    @Test
    void compareSameInstance()
    {
        final Price total  = new Price( "vwx", 1_234_567_890, 3 );

        assertEquals(  0, total.compareTo( total )
                     , "value compared to self should result in zero (0)"
                    );
    }

    @Test
    void compareAgainstNullFails()
    {
        final Price total  = new Price( "yzz", 1_234_567_890, 3 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.compareTo( null ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Can not compare against (null) price", t.getMessage()
                     , "Incorrect exception message" );
    }

    @Test
    void compareDissimilarPrecision()
    {
        final Price valueA  = new Price( "def", 1_234_567_890, 3 );
        final Price valueB  = new Price( "def", 1_234_567_890, 2 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { valueA.compareTo( valueB ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Precision mismatch 3 != 2", t.getMessage()
                     , "Incorrect exception message" );
    }

    @Test
    void compareDissimilarCurrencies()
    {
        final Price valueA  = new Price( "cur", 1_234_567_890, 3 );
        final Price valueB  = new Price( "ruc", 1_234_567_890, 3 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { valueA.compareTo( valueB ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Incompatible price codes cur and ruc", t.getMessage()
                     , "Incorrect exception message" );
    }


    @Test
    void compareAgainstSmallerValue()
    {
        final Price valueA  = new Price( "ghi", 1_234_567_890, 3 );
        final Price valueB  = new Price( "ghi", 1_111_111_111, 3 );

        assertTrue(  0 < valueA.compareTo( valueB )
                   , "A incorrectly treated larger than B" );
    }


    @Test
    void compareAgainstLargerValue()
    {
        final Price valueA  = new Price( "jkl", 123_456_789, 3 );
        final Price valueB  = new Price( "jkl", 222_222_222, 3 );

        assertTrue(  0 > valueA.compareTo( valueB )
                   , "Comparison thinks B is not greater than A" );
    }




    @Test
    void price_equalsDifferentPrice_returnsFalse()
    {
        final Price valueA  = new Price( "ABC", 123_456_789, 3 );
        final Price valueB  = new Price( "ABC", 222_222_222, 3 );

        assertFalse( valueA.equals( valueB ), "Equals not working" );
    }

    @Test
    void price_equalsSimilarPrice_returnsTrue()
    {
        final Price valueA  = new Price( "IRL", 123_456_789, 3 );
        final Price valueB  = new Price( "IRL", 123_456_789, 3 );

        assertTrue( valueA.equals( valueB ), "Equals not working" );
    }

}