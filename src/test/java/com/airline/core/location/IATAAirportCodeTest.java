package com.airline.core.location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.both;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@SuppressWarnings( { "PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals" } )
class IATAAirportCodeTest
{

    @Test
    void testAltantaIATAAirportCode()
    {
        IATAAirportCode atlantaIATAAirportCode = new IATAAirportCode( "ATL" );

        assertEquals(  "ATL", atlantaIATAAirportCode.getAirportCode()
                     , "Airport code not properly set via constructor" );
    }

    @Test
    void testNaritaIATAAirportCode()
    {
        IATAAirportCode naritaIATAAirportCode = new IATAAirportCode( "NRT" );

        assertEquals(  "NRT", naritaIATAAirportCode.getAirportCode()
                     , "Airport code not properly set via constructor" );
    }

    @Test
    void testAltantaIATAAirportCodeLowercase()
    {
        Throwable throwable = assertThrows(  IllegalArgumentException.class
                                           , () -> { new IATAAirportCode( "atl" ); }
                                           , "IllegalArgumentException not thrown when expected"
                                          );


        assertThat(  "Exception message contains the necessary detail"
                   , throwable.getMessage()
                   , both( containsString( "Invalid IATA airport code" ) )
                     .and( containsString( "'atl'")) );
    }


    @Test
    void testIATAAirportCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows(  IllegalArgumentException.class
                                           , () -> { new IATAAirportCode( null ); }
                                           , "IllegalArgumentException not thrown when expected"
                                          );

        assertEquals(  "Airport code is required", throwable.getMessage()
                     , "Incorrect exception message" );
    }

    // ----- Comparable

    @Test
    void compareSimilarIATAAirportCode()
    {
        IATAAirportCode narita1IATAAirportCode = new IATAAirportCode( "NRT" );
        IATAAirportCode narita2IATAAirportCode = new IATAAirportCode( "NRT" );

        assertEquals(  0, narita1IATAAirportCode.compareTo( narita2IATAAirportCode )
                     , "Different instances with same airport code should be equivalent"
                    );
    }

    @Test
    void collationOrderOfIATAAirportCode()
    {
        IATAAirportCode naritaIATAAirportCode  = new IATAAirportCode( "NRT" );
        IATAAirportCode atlantaIATAAirportCode = new IATAAirportCode( "ATL" );

        assertAll(
              () -> assertTrue(  0 < naritaIATAAirportCode.compareTo( atlantaIATAAirportCode )
                               , "Sort order of airport coes is incorrect" )

            , () -> assertTrue(  0 > atlantaIATAAirportCode.compareTo( naritaIATAAirportCode )
                               , "Sort order of airport coes is incorrect" )
        );
    }

}
