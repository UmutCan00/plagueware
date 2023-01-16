package pathfinder;
public interface IHeapItem<T> extends Comparable<T> {

    int getHeapIndex();
    void setHeapIndex (int value);
    void resetHeapIndex();
}