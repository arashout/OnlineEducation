//import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;


/**
 * Created by arash_000 on 2016-11-29.
 */
public class FastCollinearPoints {
    private LineSegment[] lineSegs = new LineSegment[1];
    private int count;
    public FastCollinearPoints(Point[] points){
        //VALIDATION STEPS
        //Make sure argument not null
        if(points == null) throw new NullPointerException();
        //Make sure no point in points is null
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if(points[i] == null) throw new NullPointerException();
        }
        Arrays.sort(points);
        //Ensure that no points are repeated
        for (int i = 0; i < n - 1; i++) {
            if(points[i].compareTo(points[i+1]) == 0) throw new IllegalArgumentException();
        }

        //CREATE LINE SEGMENTS
        count = 0;
        int slopeCount;
        double curSlope, nextSlope;
        boolean naturalOrder;
        Point[] pointsCopy;
        Point p,q;

        for (int i = 0; i < n; i++) {
            p = points[i];
            slopeCount = 1;
            pointsCopy = points.clone();
            Arrays.sort(pointsCopy, p.slopeOrder());
            for (int j = 1; j < n - 1; j++) {//Ignore first point = itself
                q = pointsCopy[j];
                curSlope = p.slopeTo(q);
                nextSlope = p.slopeTo(pointsCopy[j+1]);
                //Ensure that points are in natural order - OTHERWISE DON'T MAKE LINE SEGMENT
                naturalOrder = p.compareTo(q) != 1;
                if(curSlope == nextSlope && naturalOrder){
                    slopeCount++;
                }
                else if(curSlope != nextSlope && slopeCount >= 3 && naturalOrder){

                    lineSegs[count] = new LineSegment(p, q);
                    count++;
                    if(count >= lineSegs.length) lineSegs = resize(lineSegs, count * 2); //Resize Array
                    slopeCount = 1;
                }
                else slopeCount = 1;
            }
        }
        lineSegs = resize(lineSegs, count); //Resize back to normal
    }
    public int numberOfSegments(){
        return count;
    }        // the number of line segments
    public LineSegment[] segments(){
        return lineSegs;
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