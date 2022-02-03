package com.airline.core.carrier;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
class ICAOAirlineDesignatorTest
{

    @Test
    void testDeltaAirlinesCode()
    {
        ICAOAirlineDesignator deltaAirlinesCode = new ICAOAirlineDesignator( "DAL" );

        assertEquals(  "DAL", deltaAirlinesCode.getAirlineCode()
                     , "Airline code not set properly in constructor" );
    }

    @Test
    void testAirFranceCode()
    {
        ICAOAirlineDesignator aeroMexicoCode = new ICAOAirlineDesignator( "AMX" );

        assertEquals(  "AMX", aeroMexicoCode.getAirlineCode()
                     , "Airline code not set properly in constructor" );
    }


    @Test
    void testDeltaAirlinesCodeLowercase()
    {
        Throwable throwable = assertThrows(  IllegalArgumentException.class
                                           , () -> { new ICAOAirlineDesignator( "dal" ); }
                                           , "IllegalArgumentException not thrown when expected"
            );


        assertThat(  "Exception message contains the necessary detail"
                   , throwable.getMessage()
                   , both( containsString("Invalid ICAO airline code" ) )
                   . and(  containsString( "'dal'")) );
    }


    @Test
    void testAirlineCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class
                                           ,() -> { new ICAOAirlineDesignator( null );}
                                           ,"IllegalArgumentException not thrown when expected"
            );

        assertEquals(  "Airline code is required", throwable.getMessage()
                     , "Incorrect exception message" );
    }


    @Test
    void icaoDesignator_equalsSimilar_returnsTrue()
    {
        ICAOAirlineDesignator designatorA = new ICAOAirlineDesignator( "DAL" );
        ICAOAirlineDesignator designatorB = new ICAOAirlineDesignator( "DAL" );

        assertTrue( designatorA.equals( designatorB ) );
    }

}
