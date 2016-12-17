package CollinearPoints;

import java.util.Arrays;

/**
 * Created by arash_000 on 2016-11-29.
 */
public class FastCollinearPoints {
    private LineSegment[] theLineSegments;
    private int count;
    public FastCollinearPoints(Point[] points){
    }
    public int numberOfSegments(){
        return count;
    }        // the number of line segments
    public LineSegment[] segments(){
        return theLineSegments;
    }                // the line segments
    private LineSegment[] resize(LineSegment[] arr, int newCapacity) {return new LineSegment[5];}
}