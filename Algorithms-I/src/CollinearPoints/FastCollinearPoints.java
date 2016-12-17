package CollinearPoints;

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
            if(points[i] == points[i + 1]) throw new IllegalArgumentException();
        }

        //CREATE LINE SEGMENTS
        count = 0;

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