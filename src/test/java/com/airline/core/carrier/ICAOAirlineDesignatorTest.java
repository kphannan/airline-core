package com.airline.core.carrier;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


        assertThat( throwable.getMessage()
                   ,both(containsString("Invalid ICAO airline code"))
                   .and( containsString( "'dal'")) );
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
