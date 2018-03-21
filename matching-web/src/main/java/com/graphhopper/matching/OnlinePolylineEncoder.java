package com.graphhopper.matching;

import com.graphhopper.util.GPXEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a utility
 * For streaming we need a polyline line encoder to encode stream growing polyline
 *
 */
public class OnlinePolylineEncoder {

  private static int floor1e5(double coordinate) {
    return (int) Math.floor(coordinate * 1e5);
  }

  private static String encodeSignedNumber(int num) {
    int sgn_num = num << 1;
    if (num < 0) {
      sgn_num = ~(sgn_num);
    }
    return (encodeNumber(sgn_num));
  }

  private static String encodeNumber(int num) {

    StringBuffer encodeString = new StringBuffer();

    while (num >= 0x20) {
      int nextValue = (0x20 | (num & 0x1f)) + 63;
      encodeString.append((char) (nextValue));
      num >>= 5;
    }

    num += 63;
    encodeString.append((char) (num));

    return encodeString.toString();
  }


  private static String encodeSignedNumber(long num) {
    long sgn_num = num << 1;
    if (num < 0) {
      sgn_num = ~(sgn_num);
    }
    return (encodeNumber(sgn_num));
  }

  private static String encodeNumber(long num) {

    StringBuffer encodeString = new StringBuffer();

    while (num >= 0x20) {
      long nextValue = (0x20 | (num & 0x1f)) + 63;
      encodeString.append((char) (nextValue));
      num >>= 5;
    }

    num += 63;
    encodeString.append((char) (num));

    return encodeString.toString();
  }


  /**
   * Extend a encoded polyline string by adding a point to it
   * @param lineString
   * @param lastLat
   * @param lastLng
   * @param newLat
   * @param newLng
   * @return
   */
  public static String extendPolyline(String lineString , double lastLat , double lastLng , double newLat , double newLng){

    int lastlate5 = floor1e5(lastLat);
    int lastlnge5 = floor1e5(lastLng);

    int late5 = floor1e5(newLat);
    int lnge5 = floor1e5(newLng);

    int dlat = late5 - lastlate5;
    int dlng = lnge5 - lastlnge5;

    lineString = lineString +  (encodeSignedNumber(dlat).toString() + encodeSignedNumber(dlng).toString());
    return lineString;
  }



  /**
   * Merge two given polyline
   * * @return
   */
  public static  String pointsToPoly(List<Double[]> points){




    String pl = "";

    Double[] last = new Double[]{ 0D , 0D};

    for (Double[] point : points){

      pl = extendPolyline(pl,last[1],last[0], point[1],point[0]);
      last = point;
    }

    return pl;
  }







}
