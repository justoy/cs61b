import java.util.Formatter;

/**
 * SortedComparableList.java
 * A list of Comparables in ascending order.
 */
public class SortedComparableList {
    /** First element of list. */
    public Comparable head;
    /** Remaining elements of list. */
    public SortedComparableList tail;

    /** A list with head HEAD0 and tail TAIL0. */
    public SortedComparableList(Comparable head0, SortedComparableList tail0) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        head = head0;
        tail=tail0;
    }

    /** A list with null tail, and head = 0. */
    public SortedComparableList(){
        // REPLACE THIS LINE WITH YOUR SOLUTION
        head=0;
        tail=null;
    }

    /** Inserts Comparable c into its correct location in this list. */
    public void insert(Comparable c) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        SortedComparableList temp= this;
        while(c.compareTo(temp.head)>0){
            if(temp.tail!=null){
                temp=temp.tail;
            }
            else{
                temp.tail= new SortedComparableList(c, null);
                return;
            }
        }
        Comparable t=temp.head;
        temp.head=c;
        temp.tail=new SortedComparableList(t, temp.tail);
    }

    /** Returns the i-th int in this list.
     *  The first element, which is in location 0, is the 0th element.
     *  Assume i takes on the values [0, length of list - 1]. */
    public Comparable get(int i) {
        SortedComparableList temp=this;
        for(int j=0;j<i;++j){
            temp=temp.tail;
            if(temp==null){
                return null;
            }
        }
        return temp.head;
    }

    /** Adds every item in THAT to this list. */
    public void extend(SortedComparableList that) {
        SortedComparableList temp=that;
        while(temp!=null){
            insert(temp.head);
            temp=temp.tail;
        }
    }

    /** Returns a list consisting of the elements of L starting from
      * position START, and going all the way to the END. The head of the
      * list L is the 0th element of the list.
      *
      * This method should NOT modify L. */
    public static SortedComparableList subTail(SortedComparableList L, int start) {
        SortedComparableList l=L;
        for(int i=0;i<start;++i){
            l=l.tail;
        }
        SortedComparableList newL= new SortedComparableList(l.head, null);
        while(l.tail!=null){
            l=l.tail;
            newL.insert(l.head);
        }
        return newL;
        
    }

    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item START (where the first item is 0).
     *
     *  Does not modify the original list elements.
     *  Assume START and END are >= 0.
     */
    public static SortedComparableList sublist(SortedComparableList L, int start, int len) {
        SortedComparableList l=L;
        for(int i=0;i<start;++i){
            l=l.tail;
        }
        len--;
        SortedComparableList newL= new SortedComparableList(l.head, null);
        for(int i=0;i<len;++i){
            l=l.tail;
            newL.insert(l.head);
        }
        return newL;
    }

    /** Removes items from L at position len+1 and later. */
    public static void expungeTail(SortedComparableList L, int len) {
        for(int i=0;i<len;++i){
            L=L.tail;
        }
        L.tail=null;
    }

    /**
     *  Takes this list and, wherever two or more consecutive items are
     *  equal, it removes duplicates so that only one consecutive copy
     *  remains. No two consecutive items in this list are equals at the
     *  end of this method.
     *
     *  You can assume the list is in sorted order when this method is
     *  called.
     *
     *  For example, if the input list is [ 0 0 0 0 1 1 3 3 3 4 ], the
     *  output list is [ 0 1 3 4 ].
     **/
    public void squish() {
        SortedComparableList l=this;
        while(l!=null&&l.tail!=null){
            if(l.head==l.tail.head){
                l.tail=l.tail.tail;
            }
            else{
                l=l.tail;
            }
        }
    }

    /** Duplicates each Comparable so that for every original
     *  Comparable, there will end up being two consecutive Comparables.
     *
     *  You can assume the list is in sorted order when this method is
     *  called.
     *
     *  For example, if the input list is [ 2 3 4 7 ], the
     *  output list is [ 2 2 3 3 4 4 7 7 ].
     *
     *  NOTE: Do not try to make copies of the Comparables. Set
     *  the HEAD variable equal to the HEAD variable you are trying to
     *  duplicate.
     **/
    public void twin() {
        SortedComparableList l=this;
        while(l!=null){
            l.insert(l.head);
            l=l.tail.tail;
        }
    }

    /** Returns NULL if no cycle exists, else returns cycle location. */
    private int detectCycles(SortedComparableList A) {
        SortedComparableList tortoise = A;
        SortedComparableList hare = A;
        if (A == null) {
            return 0;
        }
        int cnt = 0;
        while (true) {
            cnt++;
            if (hare.tail != null) {
                hare = hare.tail.tail;
            }
            else{
                return 0;
            }
            tortoise = tortoise.tail;
            if (tortoise == null || hare == null) {
                return 0;
            }
            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    /** Returns true iff X is a SortedComparableList containing the
     *  same sequence of Comparables as THIS. Cannot handle cycles. */
    public boolean equals(Object x) {
        if (!(x instanceof SortedComparableList)) {
            return false;
        }
        SortedComparableList L = (SortedComparableList) x;
        SortedComparableList p;
        for (p = this; p != null && L != null; p = p.tail, L = L.tail) {
            if (p.head != L.head) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;
        for (SortedComparableList p = this; p != null; p = p.tail) {
            out.format("%s%d", sep, p.head);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}