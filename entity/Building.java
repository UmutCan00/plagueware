package entity;
import java.awt.Point;
import java.util.ArrayList;

import main.*;
import pathfinder.*;
import java.awt.Color;

public abstract class Building {
    
    
    
    private Point position;
    private ImpassibleNode[][] nodes;
    private Map map;
    
    ArrayList<Person> persons;
    public Building(int x, int y) throws Exception {
        this(x,y,GameData.map);
        GameData.buildings.add(this);

    }

    public Building(int x, int y, Map map) throws Exception {
        this.position = new Point(x, y);
        this.map = map;
        persons = new ArrayList<Person>();
        nodes = new ImpassibleNode[getNodeHeight()][getNodeWidth()];
        if(x + getNodeWidth() > map.getNodeWidth() || x < 0 ||
                y + getNodeHeight() > map.getNodeHeight() || y < 0 || getEnterNode() == null) {
            throw new Exception("Invalid Building Placement");
        }
        for(int i = 0; i < getNodeHeight(); i++) {
            for (int j = 0; j < getNodeWidth(); j++) {
                Node node = map.getNodeAtRowColumn(new Point(x + j, y + i));
                if(node instanceof ImpassibleNode) {
                    for (ImpassibleNode[] ins : nodes) {
                        for (ImpassibleNode in : ins) {
                            map.setNodePassible(in.getPosition());
                        }
                    }
                    throw new Exception("Invalid Building Placement");
                }
                map.setNodeImpassible(node.getPosition());
                nodes[i][j] = (ImpassibleNode) map.getNodeAtRowColumn(new Point(x + j, y + i));
                nodes[i][j].setColor(getColor());
            }
        }
       

    }





    public abstract int getNodeWidth(); 

    public abstract int getNodeHeight();
    
    public Node getEnterNode() {
        Node pos = map.getNodeAtRowColumn(position);
        int x = pos.getPosition().x + getNodeWidth() / 2;
        int y = pos.getPosition().y - 1;
        Node node = map.getNodeAtRowColumn(new Point(x, y));
        if(node instanceof PathfindNode) {
            return node;
        }
        y = pos.getPosition().y + getNodeHeight();
        node = map.getNodeAtRowColumn(new Point(x, y));
        if(node instanceof PathfindNode) {
            return node;
        }
        y = pos.getPosition().y + getNodeHeight() / 2;
        x = pos.getPosition().x - 1;
        node = map.getNodeAtRowColumn(new Point(x, y));
        if(node instanceof PathfindNode) {
            return node;
        }
        x = pos.getPosition().x + getNodeWidth();
        node = map.getNodeAtRowColumn(new Point(x, y));
        if(node instanceof PathfindNode) {
            return node;
        }

        return null;
    }

    public void enter(Person p) {
        persons.add(p);
        p.setLeaveMinute(GameData.time.getTotalMinutes() + 300); //leavr the building in 5 hour
    }
    public void exit(Person p) {
        persons.remove(p);
        p.location = map.getPositionOfNode(getEnterNode());
        GameData.chooseBuilding(p);
    }
    public ArrayList<Person> getPeople() {
        return (ArrayList<Person>)persons.clone();
    }
    public abstract Color getColor();
    public abstract int getBuildingType();
}