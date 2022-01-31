package com.airline.core.currency;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class PriceTest
{
    @Test
    public void basicConstructor()
    {
        Price c = new Price( "cur", 3, 1234567890 );        // NOPMD  (DU - Anomaly)

        assertAll(  "Verify constructor sets multiple attributes properly"
                  , () -> assertEquals( "cur", c.getPriceCode(), "construct didn't set currency code" )
                  , () -> assertEquals( 3, c.getAmount().getDecimalPrecision(), "construct didn't set precision" )
                  , () -> assertEquals( 1234567890, c.getAmount().getValue(), "construct didn't set value properly" )
                 );
    }

    @Test
    public void basicConstructorAlternate()
    {
        Price c = new Price( "foo", 2, 4321 );          // NOPMD  (DU - Anomaly)

        assertAll(  "Verify constructor sets multiple attributes properly"
                  , () -> assertEquals( "foo", c.getPriceCode(), "construct didn't set currency code" )
                  , () -> assertEquals( 2, c.getAmount().getDecimalPrecision(), "construct didn't set precision" )
                  , () -> assertEquals( 4321, c.getAmount().getValue(), "construct didn't set value properly" )
                 );
    }

    @Test
    public void nullPriceCodeNotAllowed()
    {
        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { new Price( null, 3, 1234567890 ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Price code is required" , t.getMessage()
                     ,"Incorrect exception message");
    }

    @Test
    public void blankPriceCodeNotAllowed()
    {
        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { new Price( "", 3, 1234567890 ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Price code is required" , t.getMessage()
                     , "Incorrect exception message");
    }


    // ----- Addition -----
    @Test
    public void addPositiveValue()
    {
        Price total  = new Price( "mno", 3, 1234567890 );
        Price addend = new Price( "mno", 3, 5 );

        total.add( addend );

        assertAll(  "Verify result of addition"
                  , () -> assertEquals(  "mno", total.getPriceCode()
                                       , "currency code changed incorrectly" )
                  , () -> assertEquals(  3, total.getAmount().getDecimalPrecision()
                                       , "precision somehow got altered")
                  , () -> assertEquals(  1234567895, total.getAmount().getValue()
                                       , "result of addition is wrong")
                 );
    }

    @Test
    public void addNullValue()
    {
        Price total  = new Price( "pqr", 3, 1234567890 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.add( null ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Incompatible Price (null)", t.getMessage()
                     , "Incorrect exception message");
    }

    @Test
    public void addAddIncompatibleValue()
    {
        Price total   = new Price( "cur", 3, 1234567890 );
        Price addend  = new Price( "ruc", 2, 5 );


        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.add( addend ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Can not add incompatible prices", t.getMessage()
                     , "Incorrect exception message");
    }




    // ----- Subtraction -----
    @Test
    public void subtractPositiveValue()
    {
        Price total  = new Price( "abc", 3, 1234567890 );
        Price addend = new Price( "abc", 3, 5 );

        total.subtract( addend );

        assertAll(  "Verify integrity of the total"
                  , () -> assertEquals( "abc", total.getPriceCode(), "Incorrect currency code" )
                  , () -> assertEquals( 3, total.getAmount().getDecimalPrecision(), "Precision incorrectly modified" )
                  , () -> assertEquals( 1234567885, total.getAmount().getValue(), "subtraction failed" )
                 );
    }

    @Test
    public void addSubtractIncompatibleValue()
    {
        Price total   = new Price( "cur", 3, 1234567890 );
        Price subtend = new Price( "ruc", 2, 5 );


        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.subtract( subtend ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Can not subtract incompatible prices", t.getMessage()
                     , "Incorrect exception message");
    }


    // ----- Comparison -----
    @Test
    public void compareEquivalentValues()
    {
        Price valueA  = new Price( "stu", 3, 1234567890 );
        Price valueB  = new Price( "stu", 3, 1234567890 );

        assertEquals( 0, valueA.compareTo( valueB )
                     ,"Numerically equivalent instances should compare equally"
                    );
    }

    @Test
    public void compareSameInstance()
    {
        Price total  = new Price( "vwx", 3, 1234567890 );

        assertEquals(  0, total.compareTo( total )
                     , "value compared to self should result in zero (0)"
                    );
    }

    @Test
    public void compareAgainstNullFails()
    {
        Price total  = new Price( "yzz", 3, 1234567890 );

        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { total.compareTo( null ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Can not compare against (null) price", t.getMessage()
                     , "Incorrect exception message");
    }

    @Test
    public void compareDissimilarPrecision()
    {
        Price valueA  = new Price( "def", 3, 1234567890 );
        Price valueB  = new Price( "def", 2, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows(  ArithmeticException.class
                                   , () -> { valueA.compareTo( valueB ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Precision mismatch 3 != 2", t.getMessage()
                     , "Incorrect exception message");
    }

    @Test
    public void compareDissimilarCurrencies()
    {
        Price valueA  = new Price( "cur", 3, 1234567890 );
        Price valueB  = new Price( "ruc", 3, 1234567890 );

        // TODO catch exception - until normalization of precision is implemented
        Throwable t = assertThrows(  IllegalArgumentException.class
                                   , () -> { valueA.compareTo( valueB ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Incompatible price codes cur and ruc", t.getMessage()
                     , "Incorrect exception message");
    }


    @Test
    public void compareAgainstSmallerValue()
    {
        Price valueA  = new Price( "ghi", 3, 1234567890 );
        Price valueB  = new Price( "ghi", 3, 1111111111 );

        assertTrue(  0 < valueA.compareTo( valueB )
                   , "A incorrectly treated larger than B");
    }


    @Test
    public void compareAgainstLargerValue()
    {
        Price valueA  = new Price( "jkl", 3, 123456789 );
        Price valueB  = new Price( "jkl", 3, 222222222 );

        assertTrue(  0 > valueA.compareTo( valueB )
                   , "Comparison thinks B is not greater than A");
    }

}