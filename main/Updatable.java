package main;
public interface Updatable extends Runnable
{
    boolean hasFullyUpdated();
    void reset();
}