import edu.princeton.cs.algs4.In;

import java.util.List;
import java.util.ArrayList;

public class Outcast {

    private final WordNet wordnet;

    /** constructor takes a WordNet object */
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    /** given an array of WordNet nouns, return an outcast */
    public String outcast(String[] nouns) {
        int maxDistance = Integer.MIN_VALUE;
        String outcastString = null;
        for (int i = 0; i < nouns.length; i++) {
            int distanceI = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (j == i) {
                    continue;
                }
                distanceI += wordnet.distance(nouns[i], nouns[j]);
                // System.out.println("[ " + nouns[i] + " -> " + nouns[j] + " ] = [" + wordnet.distance(nouns[i], nouns[j]) + "] " + wordnet.sap(nouns[i], nouns[j]));
            }
            if (distanceI > maxDistance) {
                maxDistance = distanceI;
                outcastString = nouns[i];
            }
        }
        return outcastString;
    }

    /** see test client below */
    public static void main(String[] args) {
        Outcast outcast = new Outcast(new WordNet(args[0], args[1]));

        for (int i = 2; i < args.length; i++) {
            In in = new In(args[i]);
            List<String> words = new ArrayList<>();
            while (!in.isEmpty()) {
                words.add(in.readLine());
            }
            // System.out.println(outcast.outcast(words.toArray(new String[words.size()])));
        }
    }
}
