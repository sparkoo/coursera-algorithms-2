import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.DirectedDFS;

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
            DirectedDFS dfsV = new DirectedDFS(graph, v);
            DirectedDFS dfsW = new DirectedDFS(graph, w);

            return dfsV.distTo(closestAncestorId) + dfsW.distTo(closestAncestorId);
        } else {
            return -1;
        }
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        int closestAncestorId = -1;

        DirectedDFS dfsV = new DirectedDFS(graph, v);
        DirectedDFS dfsW = new DirectedDFS(graph, w);

        for (int i = 0; i < graph.V(); i++) {
            if (dfsW.marked(i) && dfsV.marked(i)) {
                if (closestAncestorId == -1) {
                    closestAncestorId = i;
                } else {
                    if (dfsV.distTo(i) < dfsV.distTo(closestAncestorId)) {
                        closestAncestorId = i;
                    }
                }
            }
        }

        return closestAncestorId;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int shortestPath = -1;

        for (int vI : v) {
            for (int wI : w) {
                // TODO
            }
        }

        return shortestPath;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return 0;
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
