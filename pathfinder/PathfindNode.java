package pathfinder;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.crypto.spec.GCMParameterSpec;

import entity.Person;

public class PathfindNode extends Node implements IHeapItem<PathfindNode> {
    

    public ArrayList<Person> personOnNode;
    public int umut;
    public enum State 
    {
        NORMAL,
        END,
        OPEN,
        CLOSED,
    }

    public State state;
    private int g_cost, h_cost; //g_cost == distance passed so far , h_cost == linear distance to the target
    private PathfindNode parent;
    private int heapIndex = -1;

    public PathfindNode(Point position, State state) {
        super(position);
        this.state = state;
        personOnNode = new ArrayList<Person>();
    }


    @Override
    public int compareTo(PathfindNode o) {

        int f_cost_difference = get_F_Cost() - o.get_F_Cost();

        if (f_cost_difference != 0)
            return  f_cost_difference;

        return get_H_Cost() - o.get_H_Cost();

    }

    public void resetPathfindNodeValues() 
    {
        parent = null;
        h_cost = 0;
        g_cost = 0;
    }
    @Override
    public int getHeapIndex() {
        return heapIndex;
    }

    @Override
    public void setHeapIndex(int value) {

        heapIndex = value;
    }

    @Override
    public void resetHeapIndex() {

        heapIndex = -1;
    }
    
    public void setParent (PathfindNode p, int distance) 
    {
        parent = p;
        g_cost = p.g_cost + distance;
    }

    public PathfindNode getParent() 
    {
        return parent;
    }

    public int get_H_Cost()
    {
        return h_cost;
    }

    public void set_H_Cost (int amount)
    {
        h_cost = amount;
    }
    public int get_G_Cost() 
    {
        return g_cost;
    }

    public int get_F_Cost() 
    {
        return g_cost + h_cost; 
    }

    @Override
    public Color getColor() {
        if(color == null) {
            return Color.WHITE;
        }
        return color;
    }


    public void addPerson(Person p)
    {
        personOnNode.add(p);
    }

    public void removePerson(Person p)
    {
        personOnNode.remove(p);
    }
    public ArrayList<Person> getPersons()
    {
        return personOnNode;
    }
}
