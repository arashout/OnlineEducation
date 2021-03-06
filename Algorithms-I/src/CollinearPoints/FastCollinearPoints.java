package CollinearPoints;//import edu.princeton.cs.algs4.StdOut;

import CollinearPoints.LineSegment;

import java.util.Arrays;


/**
 * Created by arash_000 on 2016-11-29.
 */
public class FastCollinearPoints {
    private LineSegment[] lineSegs = new LineSegment[1];
    private int count;

    public FastCollinearPoints(Point[] realPoints) {
        //Immutability
        Point[] points = realPoints.clone();
        //VALIDATION STEPS
        //Make sure argument not null
        if (points == null) throw new NullPointerException();
        int n = points.length;
        Arrays.sort(points);
        //Ensure that no points are repeated
        for (int i = 0; i < n - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) throw new IllegalArgumentException();
        }

        //CREATE LINE SEGMENTS
        count = 0;
        int slopeCount;
        double curSlope, nextSlope;
        boolean naturalOrder;
        Point[] pointsCopy;

        Point p, q;

        for (int i = 0; i < n; i++) {
            p = points[i];
            slopeCount = 1;
            pointsCopy = points.clone();
            Arrays.sort(pointsCopy, p.slopeOrder());
            for (int j = 0; j < n - 1; j++) {
                q = pointsCopy[j];
                curSlope = p.slopeTo(q);
                nextSlope = p.slopeTo(pointsCopy[j + 1]);
                //Ensure that points are in natural order - OTHERWISE DON'T MAKE LINE SEGMENT
                naturalOrder = p.compareTo(q) != 1;
                if (curSlope == nextSlope && naturalOrder) {
                    slopeCount++;
                    //This will handle vertical lines with slope of infinity that always ends up sorted at end
                    if (j == n - 2 && slopeCount >= 3 && naturalOrder) { //If this is last iteration
                        lineSegs[count] = new LineSegment(p, pointsCopy[j + 1]); //Add p with last point
                        count++;
                        if (count >= lineSegs.length) lineSegs = resize(lineSegs, count * 2); //Resize Array
                    }
                //When slope changes - if enough points collected add line segment
                } else if (curSlope != nextSlope && slopeCount >= 3 && naturalOrder) {
                    lineSegs[count] = new LineSegment(p, q);
                    count++;
                    if (count >= lineSegs.length) lineSegs = resize(lineSegs, count * 2); //Resize Array
                    slopeCount = 1;
                } else if ( curSlope == nextSlope && !naturalOrder){ //This is for segments that contain more than 4
                    int k = j + 1; //Next next point
                    while(true){ //Basically ensures that I only ever go
                        nextSlope = p.slopeTo(pointsCopy[k]);
                        if(curSlope != nextSlope || k == n - 1){
                            break;
                        }
                        k++;
                    }
                    j = k - 1;
                    slopeCount = 1;
                }
                else slopeCount = 1;
            }
        }
        lineSegs = resize(lineSegs, count); //Resize back to normal
    }

    public int numberOfSegments() {
        return count;
    }        // the number of line segments

    public LineSegment[] segments() {
        return lineSegs.clone();
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