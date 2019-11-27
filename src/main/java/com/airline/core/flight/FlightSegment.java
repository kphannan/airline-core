package com.delta.rm.core.flight;

import com.delta.rm.core.location.AirportCode;

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