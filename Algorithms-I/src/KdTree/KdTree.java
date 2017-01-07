package KdTree;

import edu.princeton.cs.algs4.*;

public class KdTree {
    private Node root;
    private int count;

    //For nearest point search
    private Point2D nearestPoint;
    private double nearestSquaredDist;

    public KdTree() {

        root = null;
        count = 0;
    }

    private static void draw(Node n) {
        if (n == null) return;

        double x, y;
        x = n.point.x();
        y = n.point.y();
        StdDraw.setPenRadius(0.01);
        //Vertical Lines - Even number of bits needs
        if (n.isVertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x, n.rect.ymin(), x, n.rect.ymax());
        }
        //Horizontal Lines
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(n.rect.xmin(), y, n.rect.xmax(), y);
        }
        //Draw point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(n.point.x(), n.point.y());

        KdTree.draw(n.left);
        KdTree.draw(n.right);
    }

    public static void main(String[] args) {
        KdTree allPoints = new KdTree();
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point2D p = new Point2D(x, y);
            allPoints.insert(p);
            StdOut.println(allPoints.contains(p));
        }
    }// unit testing of the methods (optional)

    public void draw() {
        KdTree.draw(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return this.count;
    }

    public void insert(Point2D p) {
        insert(root, p);
    }

    private void insert(Node parent, Point2D p) {
        if (isEmpty()) {
            root = new Node(p, null);
            root.addRect(false);
            count++;
            return;
        }
        //If the tree is not empty
        int cmp = parent.comparePoint(p);
        if (cmp > 0) {
            if (parent.right == null) {
                parent.right = new Node(p, parent);
                parent.right.addRect(true);
                count++;
            } else {
                insert(parent.right, p);
            }
        } else if (cmp < 0) {
            if (parent.left == null) {
                parent.left = new Node(p, parent);
                parent.left.addRect(false);
                count++;
            } else insert(parent.left, p);
        } else {
            if (p.equals(parent.point)) return;
            else {
                if (parent.right == null) {
                    parent.right = new Node(p, parent);
                    parent.right.addRect(true);
                    count++;
                } else {
                    insert(parent.right, p);
                }
            }
        }

    }

    public boolean contains(Point2D p) {
        return search(root, p) != null;
    }

    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> points = new Stack<>();
        range(rect, root, points);
        return points;
    }

    private void range(RectHV rect, Node n, Stack<Point2D> points) {
        if (n == null) return;
        Point2D p = n.point;
        //If point is within query rectangle
        if (rect.contains(p)) {
            points.push(p);
            range(rect, n.left, points);
            range(rect, n.right, points);
        } else if (n.isVertical) {
            if (rect.xmin() <= p.x() && rect.xmax() >= p.x()) {
                range(rect, n.left, points);
                range(rect, n.right, points);
            } else if (rect.xmax() < p.x()) range(rect, n.left, points);
            else if (rect.xmin() > p.x()) range(rect, n.right, points);
        } else {
            if (rect.ymin() <= p.y() && rect.ymax() >= p.y()) {
                range(rect, n.left, points);
                range(rect, n.right, points);
            } else if (rect.ymax() < p.y()) range(rect, n.left, points);
            else if (rect.ymin() > p.y()) range(rect, n.right, points);
        }
    }

    public Point2D nearest(Point2D p) {
        //Initialize values for nearest point search
        nearestPoint = root.point;
        nearestSquaredDist = root.point.distanceSquaredTo(p);

        nearest(p, root);
        return nearestPoint;
    }

    private void nearest(Point2D p, Node n) {
        if (n == null) return;
        Point2D curP = n.point;
        double curDist = curP.distanceSquaredTo(p);
        if (curDist < nearestSquaredDist) {
            nearestPoint = curP;
            nearestSquaredDist = curDist;
            if (curP.equals(p)) return;
        }
        double straightLineSquaredDist;
        if (n.isVertical) {
            straightLineSquaredDist = curP.x() - p.x();
            straightLineSquaredDist = straightLineSquaredDist * straightLineSquaredDist;

            //Go Left First
            if (p.x() < curP.x()) {
                nearest(p, n.left);
                if (straightLineSquaredDist <= nearestSquaredDist) nearest(p, n.right);
            } else {
                nearest(p, n.right);
                if (straightLineSquaredDist <= nearestSquaredDist) nearest(p, n.left);

            }
        } else {
            //Go Down First
            straightLineSquaredDist = curP.y() - p.y();
            straightLineSquaredDist = straightLineSquaredDist * straightLineSquaredDist;
            if (p.y() < curP.y()) {
                nearest(p, n.left);
                if (straightLineSquaredDist <= nearestSquaredDist) nearest(p, n.right);
            } else {
                nearest(p, n.right);
                if (straightLineSquaredDist <= nearestSquaredDist) nearest(p, n.left);
            }
        }
    }

    private Node search(Node n, Point2D p) {
        if (n == null) return null; //No search hit, end search
        int cmp = n.comparePoint(p);
        if (cmp == 0 && n.point.equals(p)) return n; //Search hit
        else if (cmp == -1) return search(n.left, p);
        else return search(n.right, p);
    }

    private class Node {
        public Node parent;
        public Node left;
        public Node right;
        public int index;
        public Point2D point;
        public RectHV rect;
        public boolean isVertical;

        public Node(Point2D p, Node par) {
            this.left = null;
            this.right = null;
            this.parent = par;
            this.point = p;

            if (this.parent == null) isVertical = true;
            else if (parent.isVertical) isVertical = false;
            else isVertical = true;
        }

        //Method that allows you to compare where a point is relative to another point (Node)
        //Which also takes into account the orientation of the node
        public int comparePoint(Point2D point) {
            if (isVertical) {
                //If point is less than node point GO LEFT
                if (this.point.x() > point.x()) return -1;
                else if (this.point.x() < point.x()) return +1;
                else return 0;
            } else {
                //If point is less than node point GO LEFT
                if (this.point.y() > point.y()) return -1;
                else if (this.point.y() < point.y()) return +1;
                else return 0;
            }
        }

        public void addRect(boolean isRightNode) {
            //Root node
            if (parent == null) {
                this.rect = new RectHV(0, 0, 1, 1);
            }
            //Other Nodes
            //When the parent is a vertical line
            else if (parent.isVertical) {
                if (isRightNode) {
                    this.rect = new RectHV(parent.point.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
                } else
                    this.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.point.x(), parent.rect.ymax());
            }
            //When the parent is a horizontal line
            else {
                if (isRightNode) {
                    this.rect = new RectHV(parent.rect.xmin(), parent.point.y(), parent.rect.xmax(), parent.rect.ymax());
                } else
                    this.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.point.y());
            }
        }

    }
}