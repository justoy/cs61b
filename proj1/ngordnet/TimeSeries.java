package ngordnet;

import java.util.Collection;
import java.util.TreeMap;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.ArrayList;


public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {

    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
    }

    /**
     * Returns the years in which this time series is valid. Doesn't really need
     * to be a NavigableSet. This is a private method and you don't have to
     * implement it if you don't want to.
     */
    private NavigableSet<Integer> validYears(TimeSeries<T> ts, int startYear, int endYear) {
        NavigableSet<Integer> nSet = new TreeSet<Integer>();
        for(int i=startYear; i<= endYear; ++i){
            if(ts.containsKey(i)){
                nSet.add(i);
            }
        }
        return nSet;
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR. inclusive
     * of both end points.
     */
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear) {
        NavigableSet<Integer> validKeys = validYears(ts,startYear,endYear);
        for(int i:validKeys){
            put(i, ts.get(i));
        }
    }

    /** Creates a copy of TS. */
    public TimeSeries(TimeSeries<T> ts) {
        for(int i : ts.keySet()){
            put(i, ts.get(i));
        }
    }

    /**
     * Returns the quotient of this time series divided by the relevant value in
     * ts. If ts is missing a key in this time series, return an
     * IllegalArgumentException.
     */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> results = new TimeSeries<Double>();
        for(int i:keySet()){
            if(ts.containsKey(i)){
                results.put(i,get(i).doubleValue()/ts.get(i).doubleValue());
            }
            else{
                throw new IllegalArgumentException();
            }
        }
        return results;
    }

    /**
     * Returns the sum of this time series with the given ts. The result is a a
     * Double time series (for simplicity).
     */
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> results = new TimeSeries<Double>();
        TreeSet<Integer>yearSet = new TreeSet<Integer>(keySet());
        yearSet.addAll(ts.keySet());
        for(int i:yearSet){
            double a = containsKey(i) ? get(i).doubleValue() : 0;
            double b = ts.containsKey(i) ? ts.get(i).doubleValue() : 0;
            results.put(i,a+b);
        }
        return results;
    }

    /** Returns all years for this time series (in any order). */
    public Collection<Number> years() {
        Collection<Number> results = new ArrayList<Number>(size());
        for(int i:keySet()){
            results.add(i);
        }
        return results;
    }

    /**
     * Returns all data for this time series. Must be in the same order as
     * years().
     */
    public Collection<Number> data() {
        Collection<Number> results = new ArrayList<Number>(size());
        for(int i:keySet()){
            results.add(get(i));
        }
        return results;
    }
}