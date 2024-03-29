package com.airline.core.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.utility.TestUtility;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;


@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals"})
class AirportCodeFactoryTest
{

    @Test
    void airportCodeFactory_isWellFormedUtilityClass()
        throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        TestUtility.assertUtilityClassWellDefined( AirportCodeFactory.class );
    }


    @Test
    void nullAirportCodeThrowsIllegalArgument()
    {
        Throwable throwable = assertThrows(  IllegalArgumentException.class
                                           , () -> { AirportCodeFactory.build( null ); }
                                           , "IllegalArgumentException not thrown when expected"
                                           );

        assertEquals( "Airport code is required", throwable.getMessage(), "Incorrect exception message" );
    }

    @Test
    void iataCodeFromTwoCharacterCode()
    {
        AirportCode airportCode = AirportCodeFactory.build( "ORD" );

        assertEquals(  IATAAirportCode.class, airportCode.getClass()
                     , "Object of the wrong class was constructed based on the airport code form" );
    }

    @Test
    void icaoCodeFromThreeCharacterCode()
    {
        AirportCode airportCode = AirportCodeFactory.build( "CYEG" );

        assertEquals(  ICAOAirportCode.class, airportCode.getClass()
                     , "Object of the wrong class was constructed based on the airport code form" );
    }
}