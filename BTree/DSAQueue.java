import java.util.*;

public class DSAQueue
{
    //Class constant
    public static final int DEFAULT_CAPACITY = 100;

    //Class field
    Object [] queue;
    int count;

    //Default constructor
    public DSAQueue ()
    {
        queue = new Object [DEFAULT_CAPACITY];
        count = 0;
    }

    //Alternate constructor IMPORT maxCapacity (integer)
    public DSAQueue (int maxCapacity)
    {
        queue = new Object [maxCapacity];
        count = 0;
    }

    //ACCESSOR getCount IMPORT none EXPORT count
    public int getCount ()
    {
        return count;
    }

    //ACCESSOR isEmppty IMPORT none EXPORT empty (boolean)
    public boolean isEmpty ()
    {
        boolean empty = false;
        return empty = (count == 0);
    }

    //ACCESSOR isFull IMPORT none EXPORT full (boolean)
    public boolean isFull ()
    {
        boolean full = false;
        return full = (count == queue.length);
    }

    //MUTATOR enqueue IMPORT value EXPORT none
    public void enqueue (Object value)
    {
        if (isFull())
        {
            throw new IllegalArgumentException ("Queue is full!");
        }
        else
        {
            queue[count] = value;
            count++;
        }
    }

    //MUTATOR dequeue IMPORT none EXPORT frontVal
    public Object dequeue ()
    {
        Object frontVal = peek();
        for (int i = 1; i < count; i++)
        {
            queue[i-1] = queue[i];
        }
        count--;
        return frontVal;
    }

    //ACCESSOR peek IMPORT none EXPORT frontVal
    public Object peek ()
    {
        Object topVal;
        if (isEmpty())
        { 
            throw new IllegalArgumentException ("Queue is empty!");
        }
        else
        {
            topVal = queue[0];
        }
        return topVal;
    }    
}
