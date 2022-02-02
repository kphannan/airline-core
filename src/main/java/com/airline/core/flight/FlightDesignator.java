package com.airline.core.flight;

import com.airline.core.carrier.AirlineCode;
import com.airline.core.carrier.IATAAirlineDesignator;
import lombok.Data;

@Data
public class FlightDesignator
{
    private final AirlineCode airlineCode;
    private final int         flightNumber;



    public FlightDesignator( final String carrier, final int flightNumber )
    {
        this.airlineCode  = new IATAAirlineDesignator( carrier );
        this.flightNumber = flightNumber;
    }

    public FlightDesignator( final AirlineCode airline, final int flightNumber )
    {
        this.airlineCode  = airline;
        this.flightNumber = flightNumber;
    }
}
