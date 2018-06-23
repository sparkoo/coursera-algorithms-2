import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class WordNet {

    private final Map<Integer, String> idToNounMap = new HashMap<>();
    private final Map<String, List<Integer>> nounToIdMap = new HashMap<>();
    private final Digraph hypernymsGraph;
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        readSynsets(synsets);
        hypernymsGraph = new Digraph(idToNounMap.size());

        readHypernyms(hypernyms);

        validateOnlyOneRoot(hypernymsGraph);
        validateCycle(hypernymsGraph);

        sap = new SAP(hypernymsGraph);

        // System.out.println(idToNounMap);
        // System.out.println(nounToIdMap);
        // System.out.println(hypernymsGraph);
        // System.out.println(distance("a", "f"));
    }

    private void readSynsets(String synsetsFile) {
        In in = new In(synsetsFile);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] lineParts = line.split(",");

            int id = Integer.parseInt(lineParts[0]);
            idToNounMap.put(id, lineParts[1]);

            String[] nouns = lineParts[1].split(" ");
            for (String noun: nouns) {
                if (!nounToIdMap.containsKey(noun)) {
                    nounToIdMap.put(noun, new ArrayList<>());
                }
                nounToIdMap.get(noun).add(id);
            }
        }
    }

    private void readHypernyms(String hypernymsFile) {
        In in = new In(hypernymsFile);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] lineParts = line.split(",");
            int id = Integer.parseInt(lineParts[0]);
            for (int i = 1; i < lineParts.length; i++) {
                hypernymsGraph.addEdge(id, Integer.parseInt(lineParts[i]));
            }
        }
    }

    private void validateOnlyOneRoot(Digraph graph) {
        boolean foundRoot = false;
        for (int i = 0; i < graph.V(); i++) {
            if (graph.outdegree(i) == 0) {
                if (!foundRoot) {
                    foundRoot = true;
                } else {
                    throw new IllegalArgumentException("graph has more than 1 root");
                }
            }
        }
    }

    private void validateCycle(Digraph graph) {
        if (new DirectedCycle(graph).hasCycle()) {
            throw new IllegalArgumentException("graph has a directed cycle");
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounToIdMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("word can't be null");
        }
        return nounToIdMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException("nounA nor nounB can't be null");
        }

        return sap.length(nounToIdMap.get(nounA), nounToIdMap.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException("nounA nor nounB can't be null");
        }

        int ancestorId = sap.ancestor(nounToIdMap.get(nounA), nounToIdMap.get(nounB));
        return idToNounMap.get(ancestorId);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        new WordNet(args[0], args[1]);
    }
}
