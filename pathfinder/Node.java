package pathfinder;
import java.awt.Point;
import java.awt.Color;

public abstract class Node {

    private final Point POSITION;
    protected Color color;
    
    public Node(Point position) {
        this.POSITION = position;
        color = getColor();
    }

    public Point getPosition() 
    {
        return POSITION;
    }

    public abstract Color getColor();
    public void setColor(Color c) {
        color = c;
    }

}
