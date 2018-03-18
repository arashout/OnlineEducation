package wordnet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordNet {
    final private Map<String, List<Integer>> nounIDsMap;
    final private WordVertex[] vertexList;
    final private Digraph digraph;
    final private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        // Parse Synset file
        In in = new In(synsets);
        String[] lines = in.readAllLines();
        // Each line has one vertex
        vertexList = new WordVertex[lines.length];
        nounIDsMap = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] splitStrings = line.split(",");
            String[] splitNouns = splitStrings[1].split(" ");
            int vertexID = Integer.parseInt(splitStrings[0]);
            WordVertex wv = new WordVertex(
                    vertexID,
                    splitNouns,
                    splitStrings[2]
            );
            vertexList[i] = wv;
            for (String noun : splitNouns) {
                if (nounIDsMap.containsKey(noun)) {
                    // Get list by reference so I do not need to re-insert
                    List<Integer> list = nounIDsMap.get(noun);
                    list.add(vertexID);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(vertexID);
                    nounIDsMap.put(noun, list);
                }

            }

        }

        digraph = new Digraph(vertexList.length);
        // Parse hypernyms
        in = new In(hypernyms);
        lines = in.readAllLines();
        for (String line : lines) {
            String[] splitStrings = line.split(",");
            for (int i = 1; i < splitStrings.length; i++) {
                digraph.addEdge(Integer.parseInt(splitStrings[0]), Integer.parseInt(splitStrings[i]));
            }
        }

        sap = new SAP(digraph);

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounIDsMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return nounIDsMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        // Check if they are nouns
        if (!nounIDsMap.containsKey(nounA) || !nounIDsMap.containsKey(nounB)) {
            throw new IllegalArgumentException();
        }
        int idA = nounIDsMap.get(nounA).indexOf(0);
        int idB = nounIDsMap.get(nounB).indexOf(0);
        return sap.length(idA, idB);

    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        // Check if they are nouns
        if (!nounIDsMap.containsKey(nounA) || !nounIDsMap.containsKey(nounB)) {
            throw new IllegalArgumentException();
        }
        int idA = nounIDsMap.get(nounA).indexOf(0);
        int idB = nounIDsMap.get(nounB).indexOf(0);
        return String.join(" ", vertexList[sap.ancestor(idA, idB)].synset);
    }

    private class WordVertex {
        int vertexID;
        Set<String> synset;
        String gloss;

        WordVertex(int vertexID, String[] synset, String gloss) {
            this.vertexID = vertexID;
            this.synset = new HashSet<>(Arrays.asList(synset));
            this.gloss = gloss;
        }

        @Override
        public String toString() {
            return Integer.toString(vertexID) + " : " + synset.toString() + " : " + gloss;
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wn = new WordNet("test/synsets.txt", "test/hypernyms.txt");
    }

}