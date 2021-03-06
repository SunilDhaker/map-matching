package com.graphhopper.matching;

import com.graphhopper.util.GPXEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdhaker on 21/07/17.
 */


/**
 * Port to Java of Mark McClures Javascript OnlinePolylineEncoder :
 * http://facstaff.unca.edu/mcmcclur/GoogleMaps/EncodePolyline/decode.js
 */

public class PolylineDecoder {
  private static final double DEFAULT_PRECISION = 1E5;


  public List<GPXEntry> decodeTA(String encoded) {
    return decodeTimeAwarePolylineInPositions(encoded, DEFAULT_PRECISION);
  }





  /**
   * Precision should be something like 1E5 or 1E6. For OSRM routes found precision was 1E6, not the original default
   * 1E5.
   *
   * @param encoded
   * @param precision
   * @return
   */
  public List<GPXEntry> decodeTimeAwarePolylineInPositions(String encoded, double precision) {

    List<GPXEntry> track = new ArrayList<GPXEntry>();
    int index = 0;
    int lat = 0, lng = 0 ;
    long time = 0 ,lb, lresult = 0;

    while (index < encoded.length()) {
      int b, shift = 0, result = 0;
      do {
        b = encoded.charAt(index++) - 63;
        result |= (b & 0x1f) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
      lat += dlat;

      shift = 0;
      result = 0;
      do {
        b = encoded.charAt(index++) - 63;
        result |= (b & 0x1f) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
      lng += dlng;


      shift = 0;
      lresult = 0; // lresult used for long data type result

      do {
        lb = encoded.charAt(index++) - 63;
        lresult |= (lb & 0x1f) << shift;
        shift += 5;
      } while (lb >= 0x20);

      long dtime =  ((lresult & 1) != 0 ? ~(lresult >> 1) : (lresult >> 1));
      time += dtime;
      GPXEntry p = new GPXEntry((double) lat / precision, (double) lng / precision ,time );
      track.add(p);
    }
    return track;
  }



  public List<GPXEntry> decode(String encoded) {

    return decode(encoded, DEFAULT_PRECISION);
  }
    public List<GPXEntry> decode(String encoded, double precision) {

    List<GPXEntry> track = new ArrayList<>();

    int index = 0;
    int lat = 0, lng = 0;

    while (index < encoded.length()) {
      int b, shift = 0, result = 0;
      do {
        b = encoded.charAt(index++) - 63;
        result |= (b & 0x1f) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
      lat += dlat;

      shift = 0;
      result = 0;
      do {
        b = encoded.charAt(index++) - 63;
        result |= (b & 0x1f) << shift;
        shift += 5;
      } while (b >= 0x20);
      int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
      lng += dlng;

      GPXEntry p = new GPXEntry((double) lat / precision, (double) lng / precision , 0);
      track.add(p);
    }
    return track;
  }



}
