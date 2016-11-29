package CollinearPoints;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
/**
 * Created by arash on 2016-11-27.
 */

public class BruteCollinearPoints {
    private int count;
    private LineSegment[] theSegments;
    public BruteCollinearPoints(Point[] points){
        //Declare variables
        count = 0;
        theSegments = new LineSegment[points.length];
        //Validate input
        for (int i = 0; i < points.length; i++) {
            if(points[i] == null) throw new java.util.NoSuchElementException();
        }
        Arrays.sort(points);
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        //Determine slopes
                        double slope1 = points[i].slopeTo(points[j]);
                        double slope2 = points[i].slopeTo(points[k]);
                        double slope3 = points[i].slopeTo(points[l]);
                        //Throw exceptions for repeated points
                        if(slope1 == Double.NEGATIVE_INFINITY || slope2 == Double.NEGATIVE_INFINITY || slope3 == Double.NEGATIVE_INFINITY){
                            throw new java.lang.IllegalArgumentException();
                        }
                        //Compare slopes
                        if(slope1 == slope2 && slope2 == slope3 && slope1 == slope3){
                            theSegments[count] = new LineSegment(points[i], points[l]);
                            count++;
                        }
                    }
                }
            }
        }
        theSegments = resize(theSegments, count);
    }
    public int numberOfSegments(){
        return count;
    }        // the number of line segments
    public LineSegment[] segments(){
        return theSegments;
    }
    private LineSegment[] resize(LineSegment[] arr, int newCapacity) {
        LineSegment[] newArr = new LineSegment[newCapacity];
        int index = 0;
        // Expand or shrink array
        int terminator;
        if (newCapacity > arr.length) terminator = arr.length;
        else terminator = newCapacity;
        for (int i = 0; i < terminator; i++) {
            newArr[index] = arr[i];
            index++;
        }
        return newArr;
    }
}