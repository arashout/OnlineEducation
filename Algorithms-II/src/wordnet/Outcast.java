package wordnet;

public class Outcast {
    private final WordNet wn;
    public Outcast(WordNet wordnet){
        wn = wordnet;
    }
    public String outcast(String[] nouns){
        int[] distances = new int[nouns.length];
        for (int i = 0; i < nouns.length; i++) {
            for (String noun : nouns) {
                if(!nouns[i].equals(noun)){
                    distances[i] += wn.distance(nouns[i], noun);
                }
            }
        }

        int maxIndex = -1;
        int max = -1;
        for (int i = 0; i < distances.length; i++) {
            if(distances[i] > max){
                maxIndex = i;
            }
        }

        return nouns[maxIndex];

    }
    public static void main(String[] args){}
}
