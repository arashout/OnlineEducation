import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] lineSegs = new LineSegment[1];
    private int count;
    public BruteCollinearPoints(Point[] points){
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

        Point p, q, r, s; //Check if these points are on a line
        count = 0;
        for (int i = 0; i < n - 3; i++) {
            p = points[i];
            for (int j = i + 1; j < n - 2; j++) {
                q = points[j];
                for (int k = j + 1; k < n - 1; k++) {
                    r = points[k];
                    for (int l = k + 1; l < n; l++) {
                        s = points[l];
                        //Check Slopes
                        if(p.slopeTo(q) == p.slopeTo(r)
                                && p.slopeTo(r) == p.slopeTo(s)){
                            lineSegs[count] = new LineSegment(p, s);
                            count++;
                            if(count >= lineSegs.length) lineSegs = resize(lineSegs, count * 2); //Resize Array
                        }
                    }
                }
            }
        }
        lineSegs = resize(lineSegs, count); //Resize back to normal
    }
    public int numberOfSegments(){
        return count;
    }
    public LineSegment[] segments(){
        return lineSegs;
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
