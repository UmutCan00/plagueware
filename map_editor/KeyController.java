package map_editor;

import java.awt.event.*;
import java.awt.event.KeyEvent;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
public class KeyController implements KeyListener {

    MouseController mouseController;

    public KeyController (MouseController mouseController) 
    {
        this.mouseController = mouseController;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
     
        if (e.getKeyCode() == KeyEvent.VK_1)
            mouseController.setBuildingType("House");

        else if (e.getKeyCode() == KeyEvent.VK_2)
            mouseController.setBuildingType("Apartment");

        else if (e.getKeyCode() == KeyEvent.VK_3)
            mouseController.setBuildingType("Cafe");

        else if (e.getKeyCode() == KeyEvent.VK_4)
            mouseController.setBuildingType("Hospital");

        else if (e.getKeyCode() == KeyEvent.VK_5)
            mouseController.setBuildingType("Mall");

        else if (e.getKeyCode() == KeyEvent.VK_6)
            mouseController.setBuildingType("Shop");
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    
}
