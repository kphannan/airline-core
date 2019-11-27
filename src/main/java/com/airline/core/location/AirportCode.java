package com.airline.core.location;



// LocationIdentifier      // https://en.wikipedia.org/wiki/IATA_airport_code
//    - IATAStationCode    // 2 char letter/number  
//    - IATA Station Code  // 3 letters  https://en.wikipedia.org/wiki/IATA_airport_code
//    - ICAO Station Code  // 4 letters  https://en.wikipedia.org/wiki/International_Civil_Aviation_Organization


public interface AirportCode
{
    public String getAirportCode();
}
