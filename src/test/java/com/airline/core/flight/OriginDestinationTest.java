package com.airline.core.flight;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.airline.core.location.AirportCode;
import com.airline.core.location.IATAAirportCode;

import org.junit.jupiter.api.Test;

@SuppressWarnings( { "PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals" } )
class OriginDestinationTest
{
    @Test
    void testConstructorWithBothOriginAndDestinationAsStrings()
    {
        final OriginDestination od = new OriginDestination( "ATL", "JFK" );       // NOPMD

        assertAll(  "Origin and destination airport code check"
                  , () -> assertEquals( new IATAAirportCode( "ATL" ), od.getOrigin(), "Origin does not match" )
                  , () -> assertEquals( new IATAAirportCode( "JFK" ), od.getDestination(), "Destination does not match" )

                  , () -> assertEquals( "ATL", od.getOrigin().getAirportCode(), "origin airport code should match" )
                  , () -> assertEquals( "JFK", od.getDestination().getAirportCode(), "destination airport code should match" )
                 );
    }

    @Test
    void testConstructorWithOriginStringNullThrows()
    {
        final Throwable t = assertThrows(  IllegalArgumentException.class
                                         , () -> { new OriginDestination( null, "MCO" ); }
                                         , "IllegalArgumentException not thrown when expected"
                                        );

        assertEquals(  "Both origin and destination must be specified", t.getMessage()
                     , "Incorrect exception message" );
    }

    @Test
    void testConstructorWithDestinationStringNullThrows()
    {
        final Throwable t = assertThrows(  IllegalArgumentException.class
                                         , () -> { new OriginDestination( "MSP", null ); }
                                         , "IllegalArgumentException not thrown when expected"
                                        );

        assertEquals( "Both origin and destination must be specified", t.getMessage()
                     ,"Incorrect exception message" );
    }

    @Test
    void testConstructorWithBothOriginDestinationStringNullThrows()
    {
        final Throwable t = assertThrows(  IllegalArgumentException.class
                                                    // Cast to dis-ambiguate the constructor
                                         , () -> { new OriginDestination( (String)null, (String)null ); }
                                         , "IllegalArgumentException not thrown when expected"
                                         );

        assertEquals(  "Both origin and destination must be specified", t.getMessage()
                     , "Incorrect exception message" );
    }


    @Test
    void testConstructorWithBothOriginAndDestinationAsIATACodes()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "LAX" );
        final IATAAirportCode   jfk = new IATAAirportCode( "ABE" );
        final OriginDestination od  = new OriginDestination( atl, jfk );      // NOPMD

        assertAll(  "Origin and destination airport code check"
                  , () -> assertEquals( atl, od.getOrigin(), "Origin does not match" )
                  , () -> assertEquals( jfk, od.getDestination(), "Destination does not match" )

                  , () -> assertEquals( "LAX", od.getOrigin().getAirportCode(), "origin airport code should match" )
                  , () -> assertEquals( "ABE", od.getDestination().getAirportCode(), "destination airport code should match" )
                 );
    }


    @Test
    void testConstructorWithOriginAndNullDestinationIATACodes()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "ABG" );

        final Throwable t = assertThrows(  IllegalArgumentException.class
                                         , () -> { new OriginDestination( atl, null ); }
                                         , "IllegalArgumentException not thrown when expected"
                                        );

        assertEquals(  "Both origin and destination must be specified", t.getMessage()
                     , "Incorrect exception message" );
    }


    @Test
    void testConstructorWithBothOriginDestinationIATANullThrows()
    {
        Throwable t = assertThrows(  IllegalArgumentException.class
                                     // Cast to dis-ambiguate the constructor
                                   , () -> { new OriginDestination( (AirportCode)null, (AirportCode)null ); }
                                   , "IllegalArgumentException not thrown when expected"
                                  );

        assertEquals(  "Both origin and destination must be specified", t.getMessage()
                     , "Incorrect exception message" );
    }


    // ----- equals() -----
    @Test
    void equalsToNullIsAlwaysFalse()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "ABM" );
        final IATAAirportCode   jfk = new IATAAirportCode( "ACI" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertFalse( od.equals( null ), "comparison to null is never equal" );    // NOPMD
    }

    @Test
    void equalsToSelfAlwaysTrue()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "ABM" );
        final IATAAirportCode   jfk = new IATAAirportCode( "ACI" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals( od, od, "Self comparison is always equal" );
    }

    @Test
    void equalsToSimilarIsTrue()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "ACT" );
        final IATAAirportCode   jfk = new IATAAirportCode( "AKB" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals(  new OriginDestination( new IATAAirportCode( "ACT" ), new IATAAirportCode( "AKB" ) ), od
                     , "Both origin and destination must be equal"
                    );
}

    @Test
    void equalsToDissimilarOriginIsFalse()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "GCC" );
        final IATAAirportCode   jfk = new IATAAirportCode( "GWY" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertFalse(  od.equals( new OriginDestination( new IATAAirportCode( "LAX" ), new IATAAirportCode( "GWY" ) ) )
                    , "Both origin and destination must be equal"
                   );
    }

    @Test
    void equalsToDissimilarDestinationIsFalse()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "GLA" );
        final IATAAirportCode   jfk = new IATAAirportCode( "FNT" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertFalse(  od.equals( new OriginDestination( new IATAAirportCode( "GLA" ), new IATAAirportCode( "YFO" ) ) )
                    , "Different instances should compare equally"
                   );
    }

    // ----- compareTo() -----
    @Test
    void compareToNullFails()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "FLL" );
        final IATAAirportCode   jfk = new IATAAirportCode( "PPG" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals( -1, od.compareTo( null ), "Comparison to nul should always be inequal" );
    }
    @Test
    void compareToSelfAlwaysTrue()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "PMO" );
        final IATAAirportCode   jfk = new IATAAirportCode( "PFN" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals( 0, od.compareTo( od ), "OD compared to self should be equal" );
    }

    @Test
    void compareToSimilarIsTrue()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "GEO" );
        final IATAAirportCode   jfk = new IATAAirportCode( "JFK" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals(  0, od.compareTo( new OriginDestination( new IATAAirportCode( "GEO" ), new IATAAirportCode( "JFK" ) ) )
                     , "Collation order is incorrect"
                    );
}


    @Test
    void compareToDisSimilarOriginIsFalse()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "AVB" );
        final IATAAirportCode   jfk = new IATAAirportCode( "DFW" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertTrue(  0 < od.compareTo( new OriginDestination( new IATAAirportCode( "ABY" ), new IATAAirportCode( "ROC" ) ) )
                   , "Collation order is incorrect"
                  );
}


    @Test
    void compareToDissimilarDestinationIsFalse()
    {
        final IATAAirportCode   atl = new IATAAirportCode( "ORY" );
        final IATAAirportCode   jfk = new IATAAirportCode( "HCR" );
        final OriginDestination od  = new OriginDestination( atl, jfk );

        assertTrue(  0 > od.compareTo( new OriginDestination(  new IATAAirportCode( "ORY" )
                                                             , new IATAAirportCode( "PBO" ) ) )
                   , "Collation order is incorrect"
                  );
    }

}