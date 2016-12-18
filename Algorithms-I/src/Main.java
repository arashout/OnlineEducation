import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import CollinearPoints.FastCollinearPoints;
import CollinearPoints.LineSegment;
import CollinearPoints.Point;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class Main {
    public static void main(String[] args) {

        // read the n points from a file
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\arash\\Github\\OnlineEducation\\Algorithms-I\\tests\\collinear\\input40.txt"));
            int n = sc.nextInt();
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                points[i] = new Point(x, y);
            }
            // draw the points
            StdDraw.enableDoubleBuffering();
            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);
            StdDraw.setPenRadius(0.015);
            for (Point p : points) {
                p.draw();
            }
            StdDraw.show();

            // print and draw the line segments
            StdDraw.setPenRadius(0.005);
            StdOut.println("Fast");
            FastCollinearPoints collinear = new FastCollinearPoints(points);
            for (LineSegment segment : collinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
            StdDraw.show();
            /*
            StdOut.println("Brute");
            CollinearPoints.BruteCollinearPoints bcollinear = new CollinearPoints.BruteCollinearPoints(points);
            for (CollinearPoints.LineSegment segment : bcollinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
            */
        }
        catch (FileNotFoundException e){
            StdOut.println("File not found cuh");
        }

    }
}
