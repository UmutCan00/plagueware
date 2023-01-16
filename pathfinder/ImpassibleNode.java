package pathfinder;
import java.awt.Color;
import java.awt.Point;
public class ImpassibleNode extends Node {
    
    public ImpassibleNode(Point position) 
    {
        super(position);
    }

    @Override
    public Color getColor() {

        return color;
    }

}
