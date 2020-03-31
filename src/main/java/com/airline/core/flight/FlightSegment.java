package com.airline.core.flight;

import com.airline.core.location.AirportCode;
import lombok.Data;

@Data
public class FlightSegment
{
    private FlightDesignator  flightDesignator;
    private int               segmentNumber;
    private OriginDestination originDestination;


    /**
     * Create a new instance of a flight segment between two airports.
     * 
     * @param flightDesignator unique identifier for a flight
     * @param segmentNumber relative sequence number of this flight segment
     * @param origin origination airport of this flight segment
     * @param destination destination airport of this flight segment
     */
    public FlightSegment( FlightDesignator flightDesignator
                          ,int segmentNumber
                          ,AirportCode origin
                          ,AirportCode destination )
    {
        this.flightDesignator  = flightDesignator;
        this.segmentNumber     = segmentNumber;
        this.originDestination = new OriginDestination( origin, destination );
    }

    /**
     * Create a new instance of a flight segment between two airports.
     * 
     * @param flightDesignator unique identifier for a flight
     * @param segmentNumber relative sequence number of this flight segment
     * @param originDestination origin and destination airports for this flight segment
     */
    public FlightSegment( FlightDesignator flightDesignator
                          ,int segmentNumber
                          ,OriginDestination originDestination )
    {
        this.flightDesignator  = flightDesignator;
        this.segmentNumber     = segmentNumber;
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