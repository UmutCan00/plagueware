package pathfinder;
import java.util.Arrays;

public class Heap <T>  {

    private T[] heap;

    int size;
    public Heap()
    {
        heap = (T[]) new Object[3];
    }

    private void expandHeap()
    {
        heap = Arrays.copyOf(heap, 2*heap.length+ 1);
    }

    private int indexOfParentOf (int index)
    {
        if (index == 0)
            return -1;
       return (index - 1)/2;
    }

    private int[] indexesOfChildrenOf (int index)
    {
        return new int[] {2*index + 1, 2*index + 2};
    }

    private int lessChildIndex (int index)
    {
        int[] childIndexes = indexesOfChildrenOf(index);

        if (childIndexes[0] >= heap.length)
            return -1;

        boolean greaterThanFirstChild = heap[childIndexes[0]] == null ? false :
                ((Comparable<T>)heap[index]).compareTo(heap[childIndexes[0]])>0;

        boolean greaterThanSecondChild = heap[childIndexes[1]] == null ? false :
                ((Comparable<T>)heap[index]).compareTo(heap[childIndexes[1]])>0;

        if (greaterThanFirstChild && greaterThanSecondChild)
        {
           return ((Comparable<T>)heap[childIndexes[0]]).compareTo(heap[childIndexes[1]])<0 ?  childIndexes[0] :  childIndexes[1];
        }

        if (greaterThanFirstChild)
            return childIndexes[0];
        if (greaterThanSecondChild)
            return childIndexes[1];

        return -1;
    }

    public void clear() 
    {
        for (int i = 0; i < heap.length; i++) {
            
            if (heap[i] == null)
                continue;

            ((IHeapItem<T>) heap[i] ).resetHeapIndex();
            ((PathfindNode) heap[i] ).resetPathfindNodeValues();
            heap[i] = null;
        }
    }
    public void add (T elem)
    {
        if (size == 0) 
        {
            heap[size] = elem;
            size++;
            return;
        } 

        if(heap.length <= size)
            expandHeap();

        int elemIndex = size;
        heap [elemIndex] = elem;

        while (((Comparable<T>)heap[elemIndex]).compareTo(heap[indexOfParentOf(elemIndex)])<0)
        {
            swap (elemIndex, indexOfParentOf(elemIndex));
            elemIndex = indexOfParentOf(elemIndex);

            if (elemIndex == 0)
                break;
        }

        ((IHeapItem<T>) elem).setHeapIndex(elemIndex);
        size++;
    }

    public void remove (int index)
    {
        swap(index,size-1);
        ((IHeapItem<T>) heap[size-1] ).resetHeapIndex();
        heap[size-1] = null;

        int elemIndex = index;
        int lessChildIndex = lessChildIndex(elemIndex);

        while (lessChildIndex != -1)
        {
            swap (elemIndex, lessChildIndex);
            elemIndex = lessChildIndex;
            lessChildIndex = lessChildIndex(elemIndex);
        }

        size--;

    }

    public boolean contains (T elem)
    {
        return ((IHeapItem<T>)elem).getHeapIndex() >= 0;
    }
    public T get (int index)
    {
        return heap[index];
    }
    private void swap (int index1, int index2)
    {
        T item1 = heap[index1];
        T item2 = heap[index2];

        ((IHeapItem<T>)item1).setHeapIndex(index2);
        ((IHeapItem<T>)item2).setHeapIndex(index1);

        heap[index1] = item2;
        heap[index2] = item1;
    }

    public String toString() 
    {
        String result = "";
        int currentPow = 2;
        for (int i = 0; i < size; i++) {
            
            result+= heap[i] + " ";
            
            if ((i+2) % currentPow == 0) 
            {
                currentPow *=2;
                result +="\n";
            }
        
        }

        return result;
    }
}
