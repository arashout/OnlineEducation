import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;

public class PointSET {
    private SET<Point2D> set;
    public PointSET(){
        set = new SET<Point2D>();
    }                               // construct an empty set of points
    public boolean isEmpty(){
        return set.isEmpty();
    }                      // is the set empty?
    public int size(){
        return set.size();
    }                         // number of points in the set
    public void insert(Point2D p){
        set.add(p);
    }              // add the point to the set (if it is not already in the set)
    public boolean contains(Point2D p){
        return set.contains(p);
    }            // does the set contain point p?
    public void draw(){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for(Point2D point : set){
            StdDraw.point(point.x(), point.y());
        }
    }         // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect){
        Stack<Point2D> iter = new Stack<>();
        for(Point2D point : set){
            if(point.x() >= rect.xmin() && point.x() <= rect.xmax() &&
                    point.y() >= rect.ymin() && point.y() <= rect.ymax())
                        iter.push(point);
        }
        return iter;
    }             // all points that are inside the rectangle
    public Point2D nearest(Point2D p){
        if(set.isEmpty()) return null;

        Point2D curClosest = set.min(); //Just until the for loop
        double closest = Double.POSITIVE_INFINITY;
        double distance;
        for(Point2D point : set){
            distance = point.distanceTo(p);
            if(distance < closest) {
                closest = distance;
                curClosest = point;
            }
        }
        return curClosest;
    }             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args){
        PointSET allPoints = new PointSET();
        while(!StdIn.isEmpty()){
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point2D p = new Point2D(x,y);
            allPoints.insert(p);
            allPoints.draw();
        }
    }                  // unit testing of the methods (optional)
}