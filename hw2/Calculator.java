import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eList=new EquationList("None",0,null);
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carry = x & y;
        int result = x ^ y;
        if(carry==0){
            return result;
        }
        else{
            return add(carry<<1, result);
        }
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        if(x==0||y==0){
            return 0;
        }

        boolean flag=(x^y)<0; // if flag==true x*y<0;otherwise, x*y>0
        if(x<0){
            x=add(~x,1);
        }
        if(y<0){
            y=add(~y,1);
        }

        int product=0;
        for(int i=0;i<x;++i){
            product=add(product,y);
        }
        if(flag){
            product=add(~product,1);
        }
        return product;
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        if(eList.equation.equals("None")){
            eList.equation=equation;
            eList.result=result;
        }
        else{
            EquationList temp= eList;
            eList=new EquationList(equation,result,temp);
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        EquationList temp=eList;
        
        while(temp!=null){
            System.out.println(temp.equation + " = "+Integer.toString(temp.result));
            temp=temp.next;
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp=eList;
        for(int i=1;i<n;++i){
            temp = temp.next;
        }
        System.out.println(temp.equation + " = "+Integer.toString(temp.result));
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eList=eList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while(eList!=null){
            eList=eList.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum=0;
        EquationList temp= eList;
        while(temp != null){
            sum += temp.result;
           temp = temp.next;
        }
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int product=0;
        EquationList temp= eList;
        while(temp != null){
            product *= temp.result;
            temp = temp.next;
        }
        return product;
    }
}