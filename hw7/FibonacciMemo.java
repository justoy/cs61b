import java.util.HashMap; // Import Java's HashMap so we can use it

public class FibonacciMemo {

    /**
     * The classic recursive implementation with no memoization. Don't care
     * about graceful error catching, we're only interested in performance.
     * 
     * @param n
     * @return The nth fibonacci number
     */
    public static int fibNoMemo(int n) {
        if (n <= 1) {
            return n;
        }
        return fibNoMemo(n - 2) + fibNoMemo(n - 1);
    }

    /**
     * Your optimized recursive implementation with memoization. 
     * You may assume that n is non-negative.
     * 
     * @param n
     * @return The nth fibonacci number
     */
    public static HashMap<Integer, Integer> fibMap = new HashMap<Integer, Integer>(53);
    public static int fibMemo(int n) {
        if(n<=1){
            return n;
        }
        if(fibMap.containsKey(n)){
            return fibMap.get(n);
        }
        int fibN=fibMemo(n-1)+fibMemo(n-2);
        fibMap.put(n, fibN);
        return fibN;//!! may exceed the range of int
    }

    /**
     * Answer the following question as a returned String in this method:
     * Why does even a correctly implemented fibMemo not return 2,971,215,073
     * as the 47th Fibonacci number?
     */
    public static String why47() {
        String answer = "A number of Integer has its range";
        answer += ", " + answer + " and the 47th Fibonacci number is beyond the range";
        return answer;
    }

    public static void main(String[] args) {
        // Optional testing here        
        String m = "Fibonacci's real name was Leonardo Pisano Bigollo.";
        m += "\n" + "He was the son of a wealthy merchant.\n";
        System.out.println(m);
        System.out.println("0: " + FibonacciMemo.fibMemo(0));
        System.out.println("1: " + FibonacciMemo.fibMemo(1));
        System.out.println("2: " + FibonacciMemo.fibMemo(2));
        System.out.println("3: " + FibonacciMemo.fibMemo(3));
        System.out.println("4: " + FibonacciMemo.fibMemo(4));
        System.out.println("46: " + FibonacciMemo.fibMemo(46));
        System.out.println("47: " + FibonacciMemo.fibMemo(47));

        // 46th Fibonacci = 1,836,311,903
        // 47th Fibonacci = 2,971,215,073
        //the trivial one runs about 22s, the optimized one runs 0.1s
    }
}
