package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class WordNet {
    private ArrayList<Set<String>> synLists = new ArrayList<Set<String>>();//through id(index) to find nouns
    private HashSet<String> words = new HashSet<String>();
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In inS = new In(synsetFilename);
        In inH = new In(hyponymFilename);
        String line;
        int num = 0;

        while((line =inS.readLine()) != null){
            String[] parts = line.split(",");
            int index = Integer.parseInt(parts[0]);
            parts = parts[1].split(" ");
            Set<String> tmp = new HashSet<String>();
            for(int i=0;i<parts.length;++i){
                tmp.add(parts[i]);
                words.add(parts[i]);
            }
            synLists.add(tmp);
            num++;
        }

        g= new Digraph(num);
        while((line =inH.readLine()) != null){
            String[] parts = line.split(",");
            int a = Integer.parseInt(parts[0]);
            for(int i=1;i<parts.length;++i){
                g.addEdge(a, Integer.parseInt(parts[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return words.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return words;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> temp = new TreeSet<String>();
        Set<Integer> IDs = new TreeSet<Integer>();

        //add synonyms
        int size = synLists.size();
        for(int i=0;i<size;++i){
            if(synLists.get(i).contains(word)){
                IDs.add(i);
            }
        }

        //add hyponyms
        IDs = GraphHelper.descendants(g, IDs);
        for(int i: IDs){
            Set<String> set= synLists.get(i);
            temp.addAll(set);
        }

        return temp;
    }

    //test
    public static void main(String[] args) {
        WordNet wn = new WordNet("./wordnet/synsets.txt", "./wordnet/hyponyms.txt");
        System.out.println(wn.isNoun("jump"));
        System.out.println();

        System.out.println("Hypnoyms of increase:");
        for (String noun : wn.hyponyms("increase")) {
            System.out.println(noun);
        }
    }
}