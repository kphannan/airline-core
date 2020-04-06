package com.airline.core.carrier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// import net.serenitybdd.junit5.SerenityTest;

// @SerenityTest
@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
public class IATAAirlineDesignatorTest
{

    @Test
    public void testDeltaAirlinesCode()
    {
        IATAAirlineDesignator dl = new IATAAirlineDesignator( "DL" );

        assertEquals( "DL", dl.getAirlineCode(), "Airline code is not as expected" );
    }

    @Test
    public void testAirNorthCharterCode()
    {
        IATAAirlineDesignator airNorthCharter = new IATAAirlineDesignator( "4N" );

        assertEquals( "4N", airNorthCharter.getAirlineCode(), "Airline code is not correct" );
    }


    @Test
    public void testAirSaharaCode()
    {
        IATAAirlineDesignator airSaharaCode = new IATAAirlineDesignator( "S2" );

        assertEquals( "S2", airSaharaCode.getAirlineCode(), "Airline code is not as expected" );
    }



    @Test
    public void testAirFranceCode()
    {
        IATAAirlineDesignator airFranceCode = new IATAAirlineDesignator( "AF" );

        assertEquals( "AF", airFranceCode.getAirlineCode()
                     ,"Expected airline code of 'AF' is missing" );
    }


    @Test
    public void testDeltaAirlinesCodeLowercase()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class
                                           ,() -> {new IATAAirlineDesignator( "dl" );}
                                           ,"IllegalArgumentException not thrown when expected"
                                           );

        assertThat(  "Exception message contains the necessary detail"
                   ,throwable.getMessage()
                   ,both(containsString("Invalid IATA airline code"))
                   .and( containsString( "'dl'")) );
    }


    @Test
    public void testAirlineCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new IATAAirlineDesignator( null );
        });

        assertEquals( "Airline code is required", throwable.getMessage()
                     ,"exception message is not correct" );
    }


}
