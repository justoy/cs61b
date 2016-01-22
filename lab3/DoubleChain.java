/**
 * Created by lsj on 1/22/16.
 */

public class DoubleChain {

    private DNode head;

    public DoubleChain(double val) {
		/* your code here. */
        head=new DNode(-19002);
        head.next = new DNode(null, val, null);
    }

    public DNode getFront() {
        return head.next;
    }

    /** Returns the last item in the DoubleChain. */
    public DNode getBack() {
		/* your code here */
        DNode temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        return temp;
    }

    /** Adds D to the front of the DoubleChain. */
    public void insertFront(double d) {
		/* your code here */
        head.next=new DNode(null, d, head.next);
        head.next.next.prev=head.next;
    }

    /** Adds D to the back of the DoubleChain. */
    public void insertBack(double d) {
		/* your code here */
        DNode temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=new DNode(temp, d, null);
    }

    /** Removes the last item in the DoubleChain and returns it.
     * This is an extra challenge problem. */
    public DNode deleteBack() {
		/* your code here */
        DNode temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.prev.next=null;
        return temp;
    }

    /** Returns a string representation of the DoubleChain.
     * This is an extra challenge problem. */
    public String toString() {
		/* your code here */
        DNode temp= head;
        String s="[";
        while(temp.next!=null){
            temp=temp.next;
            s=s.concat(String.valueOf(temp.val)).concat(", ");
        }
        s=s.substring(0, s.lastIndexOf(","))+"]";
        return s;
    }

    public static class DNode {
        public DNode prev;
        public DNode next;
        public double val;

        private DNode(double val) {
            this(null, val, null);
        }

        private DNode(DNode prev, double val, DNode next) {
            this.prev = prev;
            this.val = val;
            this.next =next;
        }
    }

}

