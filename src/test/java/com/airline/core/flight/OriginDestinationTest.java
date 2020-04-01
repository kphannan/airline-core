package com.airline.core.flight;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.airline.core.location.AirportCode;
import com.airline.core.location.IATAAirportCode;

import org.junit.jupiter.api.Test;

@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class OriginDestinationTest
{
    @Test
    public void testConstructorWithBothOriginAndDestinationAsStrings()
    {
        OriginDestination od = new OriginDestination( "ATL", "JFK" );       // NOPMD

        assertAll( "Origin and destination airport code check"
                  ,() -> assertEquals( new IATAAirportCode( "ATL" ), od.getOrigin(), "Origin does not match" )
                  ,() -> assertEquals( new IATAAirportCode( "JFK" ), od.getDestination(), "Destination does not match" )

                  ,() -> assertEquals( "ATL", od.getOrigin().getAirportCode(), "origin airport code should match" )
                  ,() -> assertEquals( "JFK", od.getDestination().getAirportCode(), "destination airport code should match" )
                 );
    }

    @Test
    public void testConstructorWithOriginStringNullThrows()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {new OriginDestination( null, "MCO" );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Both origin and destination must be specified", t.getMessage()
                     ,"Incorrect exception message" );
    }

    @Test
    public void testConstructorWithDestinationStringNullThrows()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {new OriginDestination( "MSP", null );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Both origin and destination must be specified", t.getMessage()
                     ,"Incorrect exception message" );
    }

    @Test
    public void testConstructorWithBothOriginDestinationStringNullThrows()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                             // Cast to dis-ambiguate the constructor
                                    ,() -> {new OriginDestination( (String)null, (String)null );}
                                    ,"IllegalArgumentException not thrown when expected"
                                    );

        assertEquals( "Both origin and destination must be specified", t.getMessage()
                     ,"Incorrect exception message" );
    }


    @Test
    public void testConstructorWithBothOriginAndDestinationAsIATACodes()
    {
        IATAAirportCode   atl = new IATAAirportCode( "LAX" );
        IATAAirportCode   jfk = new IATAAirportCode( "ABE" );
        OriginDestination od  = new OriginDestination( atl, jfk );      // NOPMD

        assertAll( "Origin and destination airport code check"
                  ,() -> assertEquals( atl, od.getOrigin(), "Origin does not match" )
                  ,() -> assertEquals( jfk, od.getDestination(), "Destination does not match" )

                  ,() -> assertEquals( "LAX", od.getOrigin().getAirportCode(), "origin airport code should match" )
                  ,() -> assertEquals( "ABE", od.getDestination().getAirportCode(), "destination airport code should match" )
                 );
    }


    @Test
    public void testConstructorWithOriginAndNullDestinationIATACodes()
    {
        IATAAirportCode   atl = new IATAAirportCode( "ABG" );

        Throwable t = assertThrows( IllegalArgumentException.class
                                   ,() -> {new OriginDestination( atl, null );}
                                   ,"IllegalArgumentException not thrown when expected"
                                  );

        assertEquals( "Both origin and destination must be specified", t.getMessage()
                     ,"Incorrect exception message" );

    }


    @Test
    public void testConstructorWithBothOriginDestinationIATANullThrows()
    {
        Throwable t = assertThrows( IllegalArgumentException.class
                                    // Cast to dis-ambiguate the constructor
                                    ,() -> {new OriginDestination( (AirportCode)null, (AirportCode)null );}
                                    ,"IllegalArgumentException not thrown when expected"
                                    );

        assertEquals( "Both origin and destination must be specified", t.getMessage()
                     ,"Incorrect exception message" );
    }


    // ----- equals() -----
    @Test
    public void equalsToNullIsAlwaysFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode("ABM");
        IATAAirportCode   jfk = new IATAAirportCode("ACI");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertFalse( od.equals(null), "comparison to null is never equal" );    // NOPMD
    }

    @Test
    public void equalsToSelfAlwaysTrue()
    {
        IATAAirportCode   atl = new IATAAirportCode("ABM");
        IATAAirportCode   jfk = new IATAAirportCode("ACI");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals( od, od, "Self comparison is always equal");
    }

    @Test
    public void equalsToSimilarIsTrue()
    {
        IATAAirportCode   atl = new IATAAirportCode( "ACT" );
        IATAAirportCode   jfk = new IATAAirportCode( "AKB" );
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals( new OriginDestination( new IATAAirportCode( "ACT" ), new IATAAirportCode( "AKB" )), od
                     ,"Both origin and destination must be equal"
                    );
}

    @Test
    public void equalsToDissimilarOriginIsFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode("GCC");
        IATAAirportCode   jfk = new IATAAirportCode("GWY");
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertFalse( od.equals( new OriginDestination(new IATAAirportCode("LAX"), new IATAAirportCode("GWY")) )
                    ,"Both origin and destination must be equal"
                   );
    }

    @Test
    public void equalsToDissimilarDestinationIsFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode( "GLA" );
        IATAAirportCode   jfk = new IATAAirportCode( "FNT" );
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertFalse( od.equals( new OriginDestination(new IATAAirportCode( "GLA" ), new IATAAirportCode( "YFO" )) )
                    ,"Different instances should compare equally"
                   );
    }

    // ----- compareTo() -----
    @Test
    public void compareToNullFails()
    {
        IATAAirportCode   atl = new IATAAirportCode( "FLL" );
        IATAAirportCode   jfk = new IATAAirportCode( "PPG" );
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals( -1, od.compareTo( null ), "Comparison to nul should always be inequal" );
    }
    @Test
    public void compareToSelfAlwaysTrue()
    {
        IATAAirportCode   atl = new IATAAirportCode( "PMO" );
        IATAAirportCode   jfk = new IATAAirportCode( "PFN" );
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals( 0, od.compareTo( od ), "OD compared to self should be equal" );
    }

    @Test
    public void compareToSimilarIsTrue()
    {
        IATAAirportCode   atl = new IATAAirportCode( "GEO" );
        IATAAirportCode   jfk = new IATAAirportCode( "JFK" );
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertEquals( 0, od.compareTo( new OriginDestination( new IATAAirportCode( "GEO" ), new IATAAirportCode( "JFK" )) )
                     ,"Collation order is incorrect"
                    );
}


    @Test
    public void compareToDisSimilarOriginIsFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode( "AVB" );
        IATAAirportCode   jfk = new IATAAirportCode( "DFW" );
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertTrue( 0 < od.compareTo( new OriginDestination( new IATAAirportCode( "ABY" ), new IATAAirportCode( "ROC" )) )
                   ,"Collation order is incorrect"
                  );
}


    @Test
    public void compareToDissimilarDestinationIsFalse()
    {
        IATAAirportCode   atl = new IATAAirportCode( "ORY" );
        IATAAirportCode   jfk = new IATAAirportCode( "HCR" );
        OriginDestination od  = new OriginDestination( atl, jfk );

        assertTrue( 0 > od.compareTo( new OriginDestination( new IATAAirportCode( "ORY" ), new IATAAirportCode( "PBO" )) )
                   ,"Collation order is incorrect"
                  );
    }

}