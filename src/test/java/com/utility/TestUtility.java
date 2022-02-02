package com.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestUtility {

    /**
     * Verifies that a utility class is well defined.
     *
     * @param clazz
     *              utility class to verify.
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     */
    public static void assertUtilityClassWellDefined( final Class<?> clazz )
        throws   NoSuchMethodException
               , InstantiationException
               , IllegalAccessException
               , IllegalArgumentException
               , InvocationTargetException
    {
        assertTrue( Modifier.isFinal( clazz.getModifiers() ), "class must be final" );
        assertEquals( 1
                      , clazz.getDeclaredConstructors().length
                      , "There must be only one constructor" );

        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        // if ( AccessibleObject.canAccess( constructor ) )
        // {

        // }
        // if (constructor.isAccessible() ||
        // if ( constructor.canAccess( null ) ||
        //         !Modifier.isPrivate( constructor.getModifiers()))
        if ( !Modifier.isPrivate( constructor.getModifiers() ) )
        {
            fail("constructor is not private");
        }

        constructor.setAccessible(true);
        constructor.newInstance();
        constructor.setAccessible(false);

        for ( final Method method : clazz.getMethods() ) {
            if ( !Modifier.isStatic( method.getModifiers() )
                    && method.getDeclaringClass().equals( clazz ) ) {
                fail( "there exists a non-static method:" + method );
            }
        }
    }
}
