package main;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawManager extends JPanel{
    
    private ArrayList<Drawable> drawables;
    private ArrayList<Drawable> requestedDrawables;

    public DrawManager() 
    {
        drawables = new ArrayList<Drawable>();
        requestedDrawables = new ArrayList<Drawable>();
    }

    public void addDrawable (Drawable drawable) 
    {
        requestedDrawables.add(drawable);
    }


    @Override
    public void paint(Graphics g) {
        
        super.paint(g);

        for (Drawable drawable : drawables) {
            drawable.paint(g);
        }

        drawables.addAll(requestedDrawables);
        requestedDrawables.clear();

    }


}
