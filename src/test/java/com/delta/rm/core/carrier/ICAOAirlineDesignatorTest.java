package com.delta.rm.core.carrier;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ICAOAirlineDesignatorTest
{

    @Test
    public void testDeltaAirlinesCode()
    {
        ICAOAirlineDesignator deltaAirlinesCode = new ICAOAirlineDesignator( "DAL" );

        assertNotNull( deltaAirlinesCode );
        assertEquals( "DAL", deltaAirlinesCode.getAirlineCode() );
    }

    @Test
    public void testAirFranceCode()
    {
        ICAOAirlineDesignator aeroMexicoCode = new ICAOAirlineDesignator( "AMX" );

        assertNotNull( aeroMexicoCode );
        assertEquals( "AMX", aeroMexicoCode.getAirlineCode() );
    }


    @Test
    public void testDeltaAirlinesCodeLowercase()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new ICAOAirlineDesignator( "dal" );
        });

        assertTrue( throwable.getMessage().contains("Invalid ICAO airline code")
                   ,"Exception message contains Invalid ICAO airline code" );

        assertTrue( throwable.getMessage().contains("'dal'")
                   ,"Exception message should contain the failing airline code." );

    }


    @Test
    public void testAirlineCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new ICAOAirlineDesignator( null );
        });

        assertEquals( "Airline code is required", throwable.getMessage() );
    }


}
