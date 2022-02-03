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
/**
 * Representation of a flight.
 */
@Value
public class Flight
{
    private final FlightDesignator   designator;

    @NonFinal
    private OriginDestination        originDestination;

    @NonFinal
    private List<FlightSegment>      segments;


    /*
     * Flight builder
     */
    @SuppressWarnings( { "PMD.BeanMembersShouldSerialize" } )
    public static class Builder
    {
        private final FlightDesignator     designator;

        private AirportCode                origin;
        private AirportCode                destination;

        private final List<FlightSegment>  segments      = new ArrayList<>();



        /**
         * Create a Flight builder that requires a flight designator.
         *
         * @param designator unique identifier of the flight.
         */
        public Builder( final FlightDesignator designator )
        {
            this.designator = designator;
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
                                , final OriginDestination originDestination )
        {
            segments.add( new FlightSegment( flightDesignator, sequence, originDestination ) );

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
            final Flight flight = new Flight( designator );

            flight.originDestination = new OriginDestination( origin, destination );

            flight.segments = new ArrayList<>();

            // If there aren't any segments, then create 1 for the flight.
            if ( segments.isEmpty() )
            {
                flight.segments.add( new FlightSegment( designator
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


    /**
     * Constructor is private to force use of the Builder.
     *
     * @param flightDesignator
     */
    private Flight( final FlightDesignator designator )
    {
        this.designator = designator;
    }

    /**
     * Verifies the flight is comprised of only a single segment.
     *
     * @return true if a single segment flight, false otherwise.
     */
    public boolean hasSingleSegment()
    {
        return segments.size() == 1;
    }

    /**
     * Conventiently get the total number of segments in this flight.
     * A flight must have at least one segment.
     *
     * @return total number of segments in the flight, with a minimum of one.
     */
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


