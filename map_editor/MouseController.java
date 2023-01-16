package map_editor;

import java.awt.event.MouseEvent;
import main.Map;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import entity.Buildings;

import java.awt.MouseInfo;
import java.awt.Point;
public class MouseController extends MouseInputAdapter {
    
    MapDisplayer mapDisplayer;
    String buildingName = "House";
    public MouseController (MapDisplayer mapDisplayer) 
    {
        this.mapDisplayer = mapDisplayer;
    }

    private void instantiateBuilding (String name) 
    {
        Point mouseLoc= MouseInfo.getPointerInfo().getLocation();
        Point frameLoc = mapDisplayer.frame.getLocationOnScreen();
        
        Point p = mapDisplayer.map.getNodeAtPosition(new Point(mouseLoc.x - frameLoc.x, mouseLoc.y - frameLoc.y-32)).getPosition();

        try {
            if (name == "House") 
                new Buildings.House(p.x, p.y, mapDisplayer.map);

            else if (name == "Apartment")
                new Buildings.Apartment(p.x, p.y, mapDisplayer.map);

            else if (name == "Cafe")
                new Buildings.Cafe(p.x, p.y, mapDisplayer.map);

            else if (name == "Hospital")
                new Buildings.Hospital(p.x, p.y, mapDisplayer.map);

            else if (name == "Mall")
                new Buildings.Mall(p.x, p.y, mapDisplayer.map);

            else if (name == "Shop")
                new Buildings.Shop(p.x, p.y, mapDisplayer.map);

            System.out.printf("new Buildings.%s(%d, %d);\n",name,p.x,p.y);
            mapDisplayer.frame.repaint();
        } catch (Exception e1) {
            e1.printStackTrace();
        }     
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if (e.getButton() == MouseEvent.BUTTON1) 
        {
            instantiateBuilding(buildingName);
        }
        
    }

    public void setBuildingType (String name) 
    {
        buildingName = name;
    }
}
