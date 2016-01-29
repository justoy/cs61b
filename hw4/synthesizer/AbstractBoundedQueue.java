package synthesizer;

public abstract class AbstractBoundedQueue implements BoundedQueue{

    protected int fillCount;
    protected int capacity;

    public int capacity(){
        return capacity;
    }
    public int fillCount(){
        return fillCount;
    }
    public boolean isEmpty(){
        if(fillCount ==0){
            return true;
        }
        return false;
    }
    public boolean isFull(){
        if(fillCount==capacity){
            return true;
        }
        return false;
    }

    public abstract double peek();
    public abstract double dequeue();
    public abstract void enqueue(double x);
}