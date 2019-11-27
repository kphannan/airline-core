package com.airline.core.flight;

import java.util.List;

import com.airline.core.location.AirportCode;
import com.airline.core.location.AirportCodeFactory;

import lombok.Data;


// Flight should be a composite of flight segments....
//                     Flight <<component>>
//                        |   get mkt/operating Carrier
//           +------------+--------------+
//           |                           |
//      Leaf<Flight>              Composite<Flight>
//                                    add(Flight)
//                                    remove(Flight)
@Data
public class Flight
{
    private final FlightDesignator   marketingFlightDesignator;
    private       FlightDesignator   operatingFlightDesignator;

    private OriginDestination        originDestination = null;

    private List<FlightSegment>      segments          = null;


    /*
     * Flight builder
     */
    public static class Builder
    {
        private FlightDesignator     operatingFlightDesignator;
        private FlightDesignator     marketingFlightDesignator;
    
        private AirportCode          origin;
        private AirportCode          destination;

        public Builder( FlightDesignator marketingFlightDesignator )
        {
            this.marketingFlightDesignator = marketingFlightDesignator;
        }

        public Builder operatedAs( FlightDesignator operatingFlightDesignator )
        {
            this.operatingFlightDesignator = operatingFlightDesignator;
            return this;
        }

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
        public Builder between( AirportCode origin, AirportCode destination )
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
        public Builder between( String origin, String destination )
        {
            this.origin      = AirportCodeFactory.build( origin );
            this.destination = AirportCodeFactory.build( destination );

            return this;
        }

        /**
         * Create a Flight object from the values collected by the builder.
         * 
         * @return the newly constructed Flight object.
         */
        public Flight build()
        {
            Flight flight = new Flight( marketingFlightDesignator );
           
            flight.originDestination = new OriginDestination(origin, destination);
            flight.operatingFlightDesignator = operatingFlightDesignator;

            return flight;
        }
    }


    public Flight( final FlightDesignator marketingFlightDesignator )
    {
        this.marketingFlightDesignator = marketingFlightDesignator;        
    }
}


