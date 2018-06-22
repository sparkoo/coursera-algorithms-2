import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {

    private final Digraph graph;


    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.graph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        int closestAncestorId = ancestor(v, w);

        if (closestAncestorId > -1) {
            BreadthFirstDirectedPaths bfVPath = new BreadthFirstDirectedPaths(graph, v);
            BreadthFirstDirectedPaths bfWPath = new BreadthFirstDirectedPaths(graph, w);

            System.out.println(bfVPath.distTo(closestAncestorId));
            System.out.println(bfWPath.distTo(closestAncestorId));

            return bfVPath.distTo(closestAncestorId) + bfWPath.distTo(closestAncestorId);
        } else {
            return -1;
        }
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        int closestAncestorId = -1;

        BreadthFirstDirectedPaths bfVPath = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfWPath = new BreadthFirstDirectedPaths(graph, w);

        for (int i = 0; i < graph.V(); i++) {
            if (bfVPath.hasPathTo(i) && bfWPath.hasPathTo(i)) {
                if (closestAncestorId == -1) {
                    closestAncestorId = i;
                } else {
                    if (bfVPath.distTo(i) < bfWPath.distTo(closestAncestorId)) {
                        closestAncestorId = i;
                    }
                }
            }
        }

        return closestAncestorId;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int closestPathLength = -1;

        for (int vI : v) {
            for (int wI : w) {
                int length = length(vI, wI);
                if (length != -1 && (closestPathLength == -1 || length < closestPathLength)) {
                    closestPathLength = length;
                }
            }
        }

        return closestPathLength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int closestAncestorId = -1;
        int closestPathLength = -1;

        for (int vI: v) {
            for (int wI: w) {
                int ancestor = ancestor(vI, wI);
                int length = length(vI, wI);
                if (length != -1 && (length < closestPathLength || closestPathLength == -1)) {
                    closestAncestorId = ancestor;
                    closestPathLength = length;
                }
            }
        }

        return closestAncestorId;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
