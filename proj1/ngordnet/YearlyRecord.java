package ngordnet;

import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.*;

public class YearlyRecord {
    private ArrayList<String> wordList;
    private HashMap<String, Integer> recordMap;
    private HashMap<String,Integer>rankMap ;
    boolean sorted = false;

    /** Creates a new empty YearlyRecord. */
    public YearlyRecord() {
        wordList = new ArrayList<String>();
        recordMap = new HashMap<String, Integer>();
    }
    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap) {
        wordList = new ArrayList<String>();
        recordMap = new HashMap<String,Integer>(otherCountMap);
        for(String k : otherCountMap.keySet()){
            int count = otherCountMap.get(k);
            wordList.add(k);
        }

    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        int count = recordMap.containsKey(word)? recordMap.get(word) : 0;
        return count;
    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {
        recordMap.put(word,count);
        wordList.add(word);
        sorted = false;
    }

    /** Returns the number of words recorded this year. */
    public int size() {
        return recordMap.size();
    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() {
        sort();
        int size = wordList.size();
        Collection<String> s = new ArrayList<String>(size);
        for(int i=0;i<size;++i){
            s.add(wordList.get(i));
        }
        return s;
    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {
        sort();
        int size = wordList.size();
        Collection<Number> countSet = new ArrayList<Number>(size);
        for(String s:wordList){
            countSet.add(recordMap.get(s));
        }
        return countSet;
    }

    /**
     * Returns rank of WORD. Most common word is rank 1. If two words have the
     * same rank, break ties arbitrarily. No two words should have the same
     * rank.
     */
    public int rank(String word) {
        sort();
        return wordList.size()-rankMap.get(word);
    }

    private class MyComparator implements Comparator<String>{
            @Override
            public int compare(String s1, String s2) {
                int c1 = recordMap.get(s1);
                int c2 = recordMap.get(s2);
                return c1-c2;
            }
    }

    private void sort(){
       if(sorted == false){
            int size = wordList.size();
            Collections.sort(wordList, new MyComparator());
            rankMap = new HashMap<String,Integer>(size);
            for(int i=0;i<size;++i){
                rankMap.put(wordList.get(i), i);
            }
            sorted=true;
        }
    }
}