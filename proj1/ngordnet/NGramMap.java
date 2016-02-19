package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class NGramMap {
    TimeSeries<Long> historyData = new TimeSeries<Long>();
    HashMap<Integer, YearlyRecord> wordsData = new HashMap<Integer, YearlyRecord>();

    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {
        In cIn = new In(countsFilename);
        In wIn = new In(wordsFilename);

        String line=null;
        while((line=cIn.readLine())!=null){
            String[] parts = line.split(",");
            int year = Integer.parseInt(parts[0]);
            Long count = Long.parseLong(parts[1]);
            historyData.put(year, count);
        }

        while((line=wIn.readLine())!=null){
            String[] parts = line.split("\t");
            String word = parts[0];
            int year = Integer.parseInt(parts[1]);
            int count = Integer.parseInt(parts[2]);
            if(wordsData.containsKey(year)){
                wordsData.get(year).put(word, count);
            }
            else{
                YearlyRecord newData = new YearlyRecord();
                newData.put(word,count);
                wordsData.put(year,newData);
            }
        }
    }

    /**
     * Returns the absolute count of WORD in the given YEAR. If the word did not
     * appear in the given year, return 0.
     */
    public int countInYear(String word, int year) {
        YearlyRecord data = wordsData.get(year);
        return data.count(word);
    }

    /** Returns a defensive copy of the YearlyRecord of WORD. */
    public YearlyRecord getRecord(int year) {
        YearlyRecord newYR = new YearlyRecord();
        YearlyRecord oldYR = wordsData.get(year);
        Collection<String> words = oldYR.words();
        Collection<Number> counts =oldYR.counts();
        Iterator<String> wordIte = words.iterator();
        Iterator<Number> countIte = counts.iterator();
        while(wordIte.hasNext()&&countIte.hasNext()){
            String word = wordIte.next();
            Number count = countIte.next();
            newYR.put(word,count.intValue());
        }
       return newYR;
    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory() {
        return new TimeSeries<Long>(historyData);
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear) {
        TimeSeries<Integer> ts = new TimeSeries<Integer>();
        for(int i=startYear;i<=endYear;++i){
            if(wordsData.containsKey(i)){
                YearlyRecord data = wordsData.get(i);
                ts.put(i,data.count(word));
            }
        }
        return ts;
    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word) {
        TimeSeries<Integer> ts = new TimeSeries<Integer>();
        for(int i:wordsData.keySet()){
            YearlyRecord data = wordsData.get(i);
            ts.put(i,data.count(word));
        }
        return ts;
    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear) {
        TimeSeries<Integer> tsWord = countHistory(word, startYear, endYear);
        TimeSeries<Long>tsAll = new TimeSeries<Long>(historyData,startYear,endYear);
        TimeSeries<Double> results = tsWord.dividedBy(tsAll);
        return results;
    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word) {
        TimeSeries<Integer> tsWord = countHistory(word);
        return tsWord.dividedBy(historyData);
    }

    /**
     * Provides the summed relative frequency of all WORDS between STARTYEAR and
     * ENDYEAR.
     */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words,int startYear, int endYear) {
        TimeSeries<Double> sum = new TimeSeries<Double>();
        TimeSeries<Long>tsAll = new TimeSeries<Long>(historyData,startYear,endYear);
        for(String word: words){
            sum = sum.plus(countHistory(word,startYear,endYear));
        }
        return sum.dividedBy(tsAll);
    }

    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        TimeSeries<Double> sum = new TimeSeries<Double>();
        for(String word:words){
            sum = sum.plus(countHistory(word));
        }
        return sum.dividedBy(historyData);
    }

    /**
     * Provides processed history of all words between STARTYEAR and ENDYEAR as
     * processed by YRP.
     */
    public TimeSeries<Double> processedHistory(int startYear, int endYear, YearlyRecordProcessor yrp) {
        TimeSeries<Double> ts = new TimeSeries<Double>();
        for(int i=startYear;i<=endYear;++i){
            if(wordsData.containsKey(i)){
                ts.put(i, yrp.process(getRecord(i)));
            }
        }
        return ts;
    }

    /** Provides processed history of all words ever as processed by YRP. */
    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
        TimeSeries<Double> ts = new TimeSeries<Double>();
        for(int i:wordsData.keySet()){
            ts.put(i, yrp.process(getRecord(i)));
        }
        return ts;
    }
}