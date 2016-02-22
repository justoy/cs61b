/* Radix.java */

package radix;

import java.util.Random;
/**
 * Sorts is a class that contains an implementation of radix sort.
 * @author
 */
public class Sorts {


    /**
     *  Sorts an array of int keys according to the values of <b>one</b>
     *  of the base-16 digits of each key. Returns a <b>NEW</b> array and
     *  does not modify the input array.
     *  
     *  @param key is an array of ints.  Assume no key is negative.
     *  @param whichDigit is a number in 0...7 specifying which base-16 digit
     *    is the sort key. 0 indicates the least significant digit which
     *    7 indicates the most significant digit
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys sorted according to the chosen digit.
     **/
    private static final int exp=16;

    public static int[] countingSort(int[] keys, int whichDigit) {
        int base = exp-1; // 0000 0000 0000 ... 0000 1111;
        int[] newKeys = new int[keys.length];
        int[] counts = new int[exp];
        int[] startPos = new int[exp];
        for(int i=0;i<keys.length;++i){
            int x= base & (keys[i]>>whichDigit);
            counts[x]++;
        }
        for(int i=1;i<=exp-1;++i){
            startPos[i] = counts[i-1]+startPos[i-1];
        }
        for(int i=0;i<keys.length;++i){
            int x = base & (keys[i]>>whichDigit);
            int pos = startPos[x];
            newKeys[pos]=keys[i];
            startPos[x]++;
        }
        return newKeys;
    }

    /**
     *  radixSort() sorts an array of int keys (using all 32 bits
     *  of each key to determine the ordering). Returns a <b>NEW</b> array
     *  and does not modify the input array
     *  @param key is an array of ints.  Assume no key is negative.
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys in sorted order.
     **/
    public static int[] radixSort(int[] keys) {
        int[] newKeys = keys.clone();

        for(int i=0;i<=7;++i){
            newKeys=countingSort(newKeys,i*4);
        }
        return newKeys;
    }

    public static void main(String[] args){
        final int SIZE = 100000;
        Random r = new Random();
        int[] test = new int[SIZE];
        for (int i = 0; i < SIZE; i++){
            test[i] = r.nextInt(Integer.MAX_VALUE);
        }
        test = radixSort(test);
        /*
        for (Integer i : test){
            System.out.println(i);
        }
        */
    }

}
