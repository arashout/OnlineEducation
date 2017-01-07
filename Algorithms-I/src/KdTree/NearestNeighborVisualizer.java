package KdTree; /******************************************************************************
 *  Compilation:  javac KdTree.NearestNeighborVisualizer.java
 *  Execution:    java KdTree.NearestNeighborVisualizer input.txt
 *  Dependencies: KdTree.PointSET.java KdTree.KdTree.java
 *
 *  Read points from a file (specified as a command-line argument) and
 *  draw to standard draw. Highlight the closest point to the mouse.
 *
 *  The nearest neighbor according to the brute-force algorithm is drawn
 *  in red; the nearest neighbor using the kd-tree algorithm is drawn in blue.
 *
 ******************************************************************************/

import KdTree.KdTree;
import KdTree.PointSET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NearestNeighborVisualizer {

    public static void main(String[] args) {
        try {

            Scanner sc = new Scanner(new File("C:\\Users\\arash\\Github\\OnlineEducation\\Algorithms-I\\tests\\kdtree\\circle100.txt"));

            StdDraw.enableDoubleBuffering();

            // initialize the two data structures with point from standard input
            PointSET brute = new PointSET();
            KdTree kdtree = new KdTree();
            while (sc.hasNext()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                Point2D p = new Point2D(x, y);
                kdtree.insert(p);
                brute.insert(p);
            }

            while (true) {

                // the location (x, y) of the mouse
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                Point2D query = new Point2D(x, y);

                // draw all of the points
                StdDraw.clear();
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.01);
                brute.draw();

                // draw in red the nearest neighbor (using brute-force algorithm)
                StdDraw.setPenRadius(0.03);
                StdDraw.setPenColor(StdDraw.RED);
                brute.nearest(query).draw();
                StdDraw.setPenRadius(0.02);

                // draw in blue the nearest neighbor (using kd-tree algorithm)
                StdDraw.setPenColor(StdDraw.BLUE);
                kdtree.nearest(query).draw();
                StdDraw.show();
                StdDraw.pause(40);
            }
        }
        catch (FileNotFoundException e){
            StdOut.println("File not found cuh");
        }
    }
}
