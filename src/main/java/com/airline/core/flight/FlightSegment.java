package com.airline.core.flight;

import com.airline.core.location.AirportCode;

import lombok.Data;

@Data
public class FlightSegment
{
    private FlightDesignator  flightDesignator;
    private int               segmentNumber;
    private OriginDestination originDestination;


    public FlightSegment( FlightDesignator flightDesignator, int segmentNumber, AirportCode origin, AirportCode destination )
    {
        this.flightDesignator = flightDesignator;
        this.segmentNumber    = segmentNumber;
        this.originDestination = new OriginDestination( origin, destination );
    }

    public FlightSegment( FlightDesignator flightDesignator, int segmentNumber, OriginDestination originDestination )
    {
        this.flightDesignator = flightDesignator;
        this.segmentNumber    = segmentNumber;
        this.originDestination = originDestination;
    }

    public AirportCode getOrigin()
    {
        return originDestination.getOrigin();
    }

    public AirportCode getDestination()
    {
        return originDestination.getDestination();
    }

}