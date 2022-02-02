package com.airline.core.carrier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.utility.TestUtility;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;


@SuppressWarnings( { "PMD.JUnitTestContainsTooManyAsserts", "PMD.AvoidDuplicateLiterals" } )
class AirlineCodeFactoryTest
{
    @Test
    void airlineCodeFactory_isWellFormedUtilityClass()
        throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        TestUtility.assertUtilityClassWellDefined( AirlineCodeFactory.class );
    }



    @Test
    void nullAirlineCodeThrowsIllegalArgument()
    {
        Throwable throwable = assertThrows(  IllegalArgumentException.class
                                           , () -> {
                                               AirlineCodeFactory.build( null );
                                           });

        assertEquals( "Airline code is required", throwable.getMessage(), "Message is not as expected" );
    }

    @Test
    void iataCodeFromTwoCharacterCode()
    {
        AirlineCode airlineCode = AirlineCodeFactory.build( "AF" );

        assertEquals( IATAAirlineDesignator.class, airlineCode.getClass(), ".class mismatch" );
    }

    @Test
    void icaoCodeFromThreeCharacterCode()
    {
        AirlineCode airlineCode = AirlineCodeFactory.build( "DAL" );

        assertEquals( ICAOAirlineDesignator.class, airlineCode.getClass(), "class mismatch" );
    }
}