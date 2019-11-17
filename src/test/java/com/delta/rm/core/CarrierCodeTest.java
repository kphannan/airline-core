package com.delta.rm.core;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CarrierCodeTest
{

    @Test
    public void testDeltaAirlinesCode()
    {
        CarrierCode deltaAirlinesCode = new CarrierCode( "DL" );

        assertNotNull( deltaAirlinesCode );
        assertEquals( "DL", deltaAirlinesCode.getCarrierCode() );
    }

    @Test
    public void testAirFranceCode()
    {
        CarrierCode airFranceCode = new CarrierCode( "AF" );

        assertNotNull( airFranceCode );
        assertEquals( "AF", airFranceCode.getCarrierCode() );
    }


    @Test
    public void testDeltaAirlinesCodeLowercase()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new CarrierCode( "dl" );
        });

        assertTrue( throwable.getMessage().contains("Invalid IATA carrier code")
                   ,"Exception message contains Invalid IATA carrier code" );

        assertTrue( throwable.getMessage().contains("'dl'")
                   ,"Exception message should contain the failing carrier code." );

    }


    @Test
    public void testAirlineCodeThrowsIllegalArgumentException()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            new CarrierCode( null );
        });

        assertEquals( "Carrier Code can not be null", throwable.getMessage() );
    }


}
