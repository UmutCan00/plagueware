package map_editor;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;


import main.DrawManager;
import main.Map;
public class MapDisplayer{

    public JFrame frame;
    public Map map; 
    public DrawManager drawManager;
    public MapDisplayer(Map map) 
    {
        this.map = map;
        drawManager = new DrawManager();
        drawManager.addDrawable(map);
        drawManager.setBackground(Color.BLACK);


        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        frame.add(drawManager);
        frame.setVisible(true);
        frame.repaint();

        MouseController mouseController = new MouseController(this);
        KeyController keyController = new KeyController(mouseController);
        
        frame.addMouseListener(mouseController);
        frame.addKeyListener(keyController);
        
    }

    public static void main(String[] args) {
        new MapDisplayer(new Map(175, 95, 10, 1));   
    }
}
