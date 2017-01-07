package KdTree; /******************************************************************************
 *  Compilation:  javac KdTree.RangeSearchVisualizer.java
 *  Execution:    java KdTree.RangeSearchVisualizer input.txt
 *  Dependencies: KdTree.PointSET.java KdTree.KdTree.java
 *
 *  Read points from a file (specified as a command-line arugment) and
 *  draw to standard draw. Also draw all of the points in the rectangle
 *  the user selects by dragging the mouse.
 *
 *  The range search results using the brute-force algorithm are drawn
 *  in red; the results using the kd-tree algorithms are drawn in blue.
 *
 ******************************************************************************/

import KdTree.KdTree;
import KdTree.PointSET;

import edu.princeton.cs.algs4.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RangeSearchVisualizer {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(new File("C:\\Users\\arash\\Github\\OnlineEducation\\Algorithms-I\\tests\\kdtree\\circle100.txt"));

            StdDraw.enableDoubleBuffering();

            // initialize the data structures with N points from standard input
            PointSET brute = new PointSET();
            KdTree kdtree = new KdTree();
            while (sc.hasNext()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                Point2D p = new Point2D(x, y);
                kdtree.insert(p);
                brute.insert(p);
            }

            double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
            double x1 = 0.0, y1 = 0.0;      // current location of mouse
            boolean isDragging = false;     // is the user dragging a rectangle

            // draw the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            brute.draw();
            StdDraw.show();

            while (true) {

                // user starts to drag a rectangle
                if (StdDraw.mousePressed() && !isDragging) {
                    x0 = StdDraw.mouseX();
                    y0 = StdDraw.mouseY();
                    isDragging = true;
                    continue;
                }

                // user is dragging a rectangle
                else if (StdDraw.mousePressed() && isDragging) {
                    x1 = StdDraw.mouseX();
                    y1 = StdDraw.mouseY();
                    continue;
                }

                // mouse no longer pressed
                else if (!StdDraw.mousePressed() && isDragging) {
                    isDragging = false;
                }


                RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                        Math.max(x0, x1), Math.max(y0, y1));
                // draw the points
                StdDraw.clear();
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.01);
                brute.draw();

                // draw the rectangle
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius();
                rect.draw();

                // draw the range search results for brute-force data structure in red
                StdDraw.setPenRadius(0.03);
                StdDraw.setPenColor(StdDraw.RED);
                for (Point2D p : brute.range(rect))
                    p.draw();

                // draw the range search results for kd-tree in blue
                StdDraw.setPenRadius(.02);
                StdDraw.setPenColor(StdDraw.BLUE);
                for (Point2D p : kdtree.range(rect))
                    p.draw();

                StdDraw.show();
                StdDraw.pause(40);
            }
        }
        catch (FileNotFoundException e){
            StdOut.println("File not found cuh");
        }
    }
}
