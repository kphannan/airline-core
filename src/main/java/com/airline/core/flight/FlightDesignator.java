package com.airline.core.flight;

import com.airline.core.carrier.AirlineCode;
import com.airline.core.carrier.IATAAirlineDesignator;
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
