package com.airline.core.flight;

import com.airline.core.location.AirportCode;
import com.airline.core.location.AirportCodeFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Value;
import lombok.experimental.NonFinal;


// Flight should be a composite of flight segments....
//                     Flight <<component>>
//                        |   get mkt/operating Carrier
//           +------------+--------------+
//           |                           |
//      Leaf<Flight>              Composite<Flight>
//                                    add(Flight)
//                                    remove(Flight)
@Value
public class Flight
{
    private final FlightDesignator   flightDesignator;
    // private       FlightDesignator   operatingFlightDesignator;

    @NonFinal
    private OriginDestination        originDestination;

    @NonFinal
    private List<FlightSegment>      segments;


    /*
     * Flight builder
     */
    public static class Builder
    {
        // private FlightDesignator     operatingFlightDesignator;
        private final transient FlightDesignator     flightDesignator;

        private transient AirportCode                origin;
        private transient AirportCode                destination;

        private final transient List<FlightSegment>  segments      = new ArrayList<>();



        public Builder( FlightDesignator marketingFlightDesignator )
        {
            this.flightDesignator = marketingFlightDesignator;
        }

        // public Builder operatedAs( FlightDesignator operatingFlightDesignator )
        // {
        //     this.operatingFlightDesignator = operatingFlightDesignator;
        //     return this;
        // }

        // public Builder origin( AirportCode location )
        // {
        //     origin = location;
        //     return this;
        // }

        // public Builder destination( AirportCode location )
        // {
        //     destination = location;
        //     return this;
        // }

        /**
         * Collect the origin and destination airports as AirportCode.
         * @param origin origin airport code
         * @param destination destination airport code
         * @return the Builder for chaining.
         */
        public Builder between( final AirportCode origin, final AirportCode destination )
        {
            this.origin      = origin;
            this.destination = destination;

            return this;
        }

        /**
         * Collect the origin and destination airports as strings which are
         * converted into the appropriate AirportCode class.
         * @param origin origin airport code
         * @param destination destination airport code
         * @return the Builder for chaining.
         */
        public Builder between( final String origin, final String destination )
        {
            this.origin      = AirportCodeFactory.build( origin );
            this.destination = AirportCodeFactory.build( destination );

            return this;
        }

        /**
         * Create a new instance of a flight segment between two airports.
         *
         * @param flightDesignator unique identifier for a flight
         * @param sequence relative sequence number of this segment
         * @param od origin and destination airports for this flight segment
         * @return the Builder for chaining.
         */
        public Builder segment(   final FlightDesignator flightDesignator
                                , final int sequence
                                , final OriginDestination od )
        {
            segments.add( new FlightSegment( flightDesignator, sequence, od ) );

            return this;
        }

        /**
         * Create a new instance of a flight segment between two airports.
         *
         * @param flightDesignator unique identifier for a flight
         * @param sequence relative sequence number of this segment
         * @param origin origin airport code
         * @param destination destination airport code
         * @return the Builder for chaining.
         */
        public Builder segment(   final FlightDesignator flightDesignator
                                , final int sequence
                                , final AirportCode origin
                                , final AirportCode destination )
        {
            segments.add( new FlightSegment(  flightDesignator
                                            , sequence
                                            , new OriginDestination( origin, destination ) ) );

            return this;
        }

        /**
         * Create a Flight object from the values collected by the builder.
         *
         * @return the newly constructed Flight object.
         */
        public Flight build()
        {
            final Flight flight = new Flight( flightDesignator );

            flight.originDestination = new OriginDestination( origin, destination );
            // flight.operatingFlightDesignator = operatingFlightDesignator;

            flight.segments = new ArrayList<>();

            // If there aren't any segments, then create 1 for the flight.
            if ( segments.isEmpty() )
            {
                flight.segments.add( new FlightSegment( flightDesignator
                                                       , 1
                                                       , flight.originDestination ) );
            }
            else
            {
                flight.segments.addAll( segments );
            }

            // TODO: Check that origin of segment 1 is flight origin and destination
            //       of the last segment is the flight destination.
            //       Possibly make sure that the segments chain from origin to destination

            return flight;
        }
    }


    public Flight( final FlightDesignator flightDesignator )
    {
        this.flightDesignator = flightDesignator;
    }

    public boolean hasSingleSegment()
    {
        return segments.size() == 1;
    }

    public int getNumberOfSegments()
    {
        return segments.size();
    }

    /**
     * Gets the segments that comprise a flight.  This list is immutable.
     *
     * @return the immutable list of flight segments.
     */
    public List<FlightSegment> getSegments()
    {
        return Collections.unmodifiableList( segments );
    }
}


