package com.delta.rm.core.location;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// import com.delta.rm.core.carrier.AirlineCode;
// import com.delta.rm.core.carrier.AirlineCodeFactory;
// import com.delta.rm.core.carrier.IATAAirlineDesignator;
// import com.delta.rm.core.carrier.ICAOAirlineDesignator;

import org.junit.jupiter.api.Test;


public class AirportCodeFactoryTest
{
    @Test
    public void nullAirportCodeThrowsIllegalArgument()
    {
        Throwable throwable = assertThrows( IllegalArgumentException.class, () -> {
            AirportCodeFactory.build( null );
        });

        assertEquals( "Airport code is required", throwable.getMessage() );
    }

    @Test
    public void iataCodeFromTwoCharacterCode()
    {
        AirportCode airportCode = AirportCodeFactory.build( "ORD" );

        assertEquals( IATAAirportCode.class, airportCode.getClass() );
    }

    @Test
    public void icaoCodeFromThreeCharacterCode()
    {
        AirportCode airportCode = AirportCodeFactory.build( "CYEG" );

        assertEquals( ICAOAirportCode.class, airportCode.getClass() );
    }
}