import java.util.AbstractList;

public class ArrayList61B<T> extends AbstractList<T>{

    int size;
    int length=0;
    T[] array;

    @SuppressWarnings("unchecked")
    public ArrayList61B(int initialCapacity){
        if(initialCapacity<1){
            throw new IllegalArgumentException("initial capacity less than 1");
        }
        size= initialCapacity;
        array=(T[]) new Object[size];
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList61B(){
        size=1;
        array=(T[]) new Object[size];
    }

    public  T get(int i){
        if(i<0||i>=size){
            throw new IllegalArgumentException("illegal index");
        }
        return array[i];
    }

    @SuppressWarnings("unchecked")
    public boolean add(T item){
        if(size==length){
            T[] temp=array;
            size *=2;
            array=(T[]) new Object[size];
            for(int i=0;i<length;++i){
                array[i]=temp[i];
            }
        }
        array[length]=item;
        length++;
        return true;
    }

    public int size(){
        return size;
    }

}