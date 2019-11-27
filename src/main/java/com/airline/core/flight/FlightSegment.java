package com.airline.core.flight;

import com.airline.core.location.AirportCode;

import lombok.Data;

@Data
public class FlightSegment
{
    private AirportCode origin;
    private AirportCode destination;


    public FlightSegment( AirportCode origin, AirportCode destination )
    {
        this.origin      = origin;
        this.destination = destination;
    }

}