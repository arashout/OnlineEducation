package CollinearPoints;

import java.util.Arrays;

/**
 * Created by arash_000 on 2016-11-29.
 */
public class FastCollinearPoints {
    private LineSegment[] theLineSegments;
    private int count;
    public FastCollinearPoints(Point[] points){
        theLineSegments = new LineSegment[points.length];
        count = 0;
        Point[] newPoints = points.clone();
        Point p;
        double prevSlope, curSlope;
        int curCount;
        for (int i = 0; i < points.length; i++) {
            p = points[i];
            Arrays.sort(newPoints, p.SLOPE_ORDER);
            curCount = 1;
            prevSlope = p.slopeTo(newPoints[0]);
            for (int j = 1; j < newPoints.length; j++) {
                //Account for last point being on line segment?
                curSlope = p.slopeTo(newPoints[j]);
                //Find co-linear points
                if(prevSlope == curSlope){
                    curCount++;
                    prevSlope = curSlope;
                }
                //Create a line segment if there are 4 or more co-linear
                else if(curCount >= 3){
                    theLineSegments[count] = new LineSegment(p, points[j]);
                    count++;
                    curCount = 1;
                    prevSlope = curSlope;
                }
                //Otherwise continue
                else{
                    curCount = 1;
                    prevSlope = curSlope;
                }
            }

        }
        theLineSegments = resize(theLineSegments, count);
    }     // finds all line segments containing 4 or more points
    public int numberOfSegments(){
        return count;
    }        // the number of line segments
    public LineSegment[] segments(){
        return theLineSegments;
    }                // the line segments
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