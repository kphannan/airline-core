package com.delta.rm.core.flight;

import com.delta.rm.core.carrier.AirlineCode;
import com.delta.rm.core.carrier.IATAAirlineDesignator;

import lombok.Data;

@Data
public class FlightDesignator
{
    private final AirlineCode airlineCode;
    private final int         flightNumber;



    public FlightDesignator( final String carrier, int flightNumber )
    {
        this.airlineCode  = new IATAAirlineDesignator( carrier );
        this.flightNumber = flightNumber;
    }

    public FlightDesignator( AirlineCode airline, int flightNumber )
    {
        this.airlineCode  = airline;
        this.flightNumber = flightNumber;
    }
}
