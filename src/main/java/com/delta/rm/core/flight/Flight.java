package com.delta.rm.core.flight;

import java.util.List;

import com.delta.rm.core.location.AirportCode;
import com.delta.rm.core.location.IATAAirportCode;

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

        public Builder between( AirportCode origin, AirportCode destination )
        {
            this.origin      = origin;
            this.destination = destination;

            return this;
        }

        public Builder between( String origin, String destination )
        {
            this.origin      = new IATAAirportCode( origin );
            this.destination = new IATAAirportCode( destination );

            return this;
        }

        public Flight build()
        {
            Flight flight = new Flight( marketingFlightDesignator );
           
            flight.originDestination = new OriginDestination(origin, destination);
            flight.operatingFlightDesignator = operatingFlightDesignator;

            return flight;
        }
    }



    // public Flight( final FlightDesignator marketingFlightDesignator
    //               ,final FlightDesignator operatingFlightDesignator
    //               ,final AirportCode      origin
    //               ,final AirportCode      destination )
    // {
    //     this.marketingFlightDesignator = marketingFlightDesignator;
    //     this.operatingFlightDesignator = Optional
    //     originDestination              = new OriginDestination(origin, destination);
    // }

    // public Flight( final FlightDesignator marketingFlightDesignator
    //               ,final AirportCode      origin
    //               ,final AirportCode      destination )
    // {
    //     this.marketingFlightDesignator = marketingFlightDesignator;
    //     originDestination              = new OriginDestination(origin, destination);
    // }

    public Flight( final FlightDesignator marketingFlightDesignator )
    {
        this.marketingFlightDesignator = marketingFlightDesignator;        
    }

    // public Flight( final FlightDesignator marketingFlightDesignator, OriginDestination originDestination )
    // {
    //     this.marketingFlightDesignator = marketingFlightDesignator;
    //     this.originDestination         = originDestination;        
    // }
}


