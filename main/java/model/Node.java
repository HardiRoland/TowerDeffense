package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.awt.Point;

public class Node implements Comparable<Node> {
    /** 
     * Id for readability of result purposes
     */ 
    //private static int idCounter = 0;
    public Point id;

    /**
     * Parent in the path
     */
    public Node parent = null;

    public List<Edge> neighbors;

    /**
     * Evaluation functions
     */
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    
    /**
     * Hardcoded heuristic
     */
    public double h;

    Node(double h, Point p) {
        this.h = h;
        this.id = p;
        this.neighbors = new ArrayList<>();
        //idCounter++;
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    public static class Edge {
        Edge(Node node) {
            this.weight = 1;
            this.node = node;
        }

        public int weight;
        public Node node;
    }

    public void addBranch(Node node) {
        Edge newEdge = new Edge(node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristic(Node target) {
        return this.h;
    }

    public static Node aStar(Node start, Node target) {
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristic(target);
        openList.add(start);

        while (!openList.isEmpty()) {
            Node n = openList.peek();
            if (n == target) {
                return n;
            }

            for (Node.Edge edge : n.neighbors) {
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if (!openList.contains(m) && !closedList.contains(m)) {
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristic(target);
                    openList.add(m);
                } else {
                    if (totalWeight < m.g) {
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristic(target);

                        if (closedList.contains(m)) {
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static void printPath(Node target) {
        Node n = target;

        if (n == null)
            return;

        List<Point> ids = new ArrayList<>();

        while (n.parent != null) {
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);

        for (Point id : ids) {
            System.out.print(id + " ");
        }
        System.out.println("");
    }

    public static List<Point> getPath(Node target) {
        Node n = target;
        if (n == null)
            return null;
        
        List<Point> ids = new ArrayList<>();

        while (n.parent != null) {
            ids.add(n.id);
            n = n.parent;
        }

        ids.add(n.id);
        Collections.reverse(ids);

        return ids;
    }
}
