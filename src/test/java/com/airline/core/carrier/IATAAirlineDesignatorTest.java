package com.delta.rm.core.carrier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// import net.serenitybdd.junit5.SerenityTest;

// @SerenityTest
public class IATAAirlineDesignatorTest
{

    @Test
    public void testDeltaAirlinesCode()
    {
        IATAAirlineDesignator dl = new IATAAirlineDesignator( "DL" );

        assertNotNull( dl );
        assertEquals( "DL", dl.getAirlineCode() );
    }

    @Test
    public void testAirNorthCharterCode()
    {
        IATAAirlineDesignator airNorthCharter = new IATAAirlineDesignator( "4N" );

        assertNotNull( airNorthCharter );
        assertEquals( "4N", airNorthCharter.getAirlineCode() );
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
        Throwable throwable = assertThrows( IllegalArgumentException.class
                                           ,() -> {
                                               new IATAAirlineDesignator( "dl" );
                                            });

        assertThat( throwable.getMessage()
                   ,both(containsString("Invalid IATA airline code"))
                   .and( containsString( "'dl'")) );
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
