import edu.princeton.cs.algs4.In;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class WordNet {

    private final Map<Integer, List<String>> idToNounMap = new HashMap<>();
    private final Map<String, Integer> nounToIdMap = new HashMap<>();
    private final Digraph hypernymsGraph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        readSynsets(synsets);
        hypernymsGraph = new Digraph(idToNounMap.size());

        readHypernyms(hypernyms);


        System.out.println(idToNounMap);
        System.out.println(nounToIdMap);
    }

    private void readSynsets(String synsetsFile) {
        In in = new In(synsetsFile);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] lineParts = line.split(",");
            Integer id = Integer.parseInt(lineParts[0]);
            String[] nouns = lineParts[1].split(" ");
            for (String noun: nouns) {
                if (!idToNounMap.containsKey(id)) {
                    idToNounMap.put(id, new LinkedList<String>());
                }
                idToNounMap.get(id).add(noun);

                nounToIdMap.put(noun, id);
            }
        }
    }

    private void readHypernyms(String hypernymsFile) {

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return null;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return null;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        new WordNet(args[0], args[1]);
    }
}
