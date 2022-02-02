package com.airline.core.flight;

import com.airline.core.location.AirportCode;
import lombok.Value;

@Value
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
    public FlightSegment(   final FlightDesignator flightDesignator
                          , final int segmentNumber
                          , final String origin
                          , final String destination )
    {
        this( flightDesignator, segmentNumber, new OriginDestination( origin, destination ) );
    }



    /**
     * Create a new instance of a flight segment between two airports.
     *
     * @param flightDesignator unique identifier for a flight
     * @param segmentNumber relative sequence number of this flight segment
     * @param origin origination airport of this flight segment
     * @param destination destination airport of this flight segment
     */
    public FlightSegment(   final FlightDesignator flightDesignator
                          , final int segmentNumber
                          , final AirportCode origin
                          , final AirportCode destination )
    {
        this( flightDesignator, segmentNumber, new OriginDestination( origin, destination ) );
    }

    /**
     * Create a new instance of a flight segment between two airports.
     *
     * @param flightDesignator unique identifier for a flight
     * @param segmentNumber relative sequence number of this flight segment
     * @param originDestination origin and destination airports for this flight segment
     */
    public FlightSegment(  final FlightDesignator flightDesignator
                          ,final int segmentNumber
                          ,final OriginDestination originDestination )
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