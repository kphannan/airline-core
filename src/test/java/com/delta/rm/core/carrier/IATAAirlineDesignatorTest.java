package com.delta.rm.core.carrier;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

// import net.serenitybdd.junit5.SerenityTest;

// @SerenityTest
public class IATAAirlineDesignatorTest
{

    @Test
    public void testDeltaAirlinesCode()
    {
        IATAAirlineDesignator deltaAirlinesCode = new IATAAirlineDesignator( "DL" );

        assertNotNull( deltaAirlinesCode );
        assertEquals( "DL", deltaAirlinesCode.getAirlineCode() );
    }

    @Test
    public void testAirNorthCharterCode()
    {
        IATAAirlineDesignator airNorthCharterCode = new IATAAirlineDesignator( "4N" );

        assertNotNull( airNorthCharterCode );
        assertEquals( "4N", airNorthCharterCode.getAirlineCode() );
    }


    @Test
    public void testAirSaharaCode()
    {
        IATAAirlineDesignator airSaharaCode = new IATAAirlineDesignator( "S2" );

        assertNotNull( airSaharaCode );
        assertEquals( "S2", airSaharaCode.getAirlineCode() );
    }



    @Test
    public void testAirFranceCode()
    {
        IATAAirlineDesignator airFranceCode = new IATAAirlineDesignator( "AF" );

        assertNotNull( airFranceCode );
        assertEquals( "AF", airFranceCode.getAirlineCode() );
    }


    @Test
    public void testDeltaAirlinesCodeLowercase()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new IATAAirlineDesignator( "dl" );
        });

        assertTrue( throwable.getMessage().contains("Invalid IATA airline code")
                   ,"Exception message contains Invalid IATA airline code" );

        assertTrue( throwable.getMessage().contains("'dl'")
                   ,"Exception message should contain the failing airline code." );

    }


    @Test
    public void testAirlineCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new IATAAirlineDesignator( null );
        });

        assertEquals( "Airline code is required", throwable.getMessage() );
    }


}
