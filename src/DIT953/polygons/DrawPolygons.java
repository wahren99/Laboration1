package DIT953.polygons;

import DIT953.polygons.polygon.*;
// import static DIT953.polygons.polygon.PolygonFactory.*;
import static DIT953.polygonshapes.PolygonFactory.*;

import javax.swing.*;
import java.awt.Graphics;
import java.util.*;

public class DrawPolygons extends JComponent{
    public ArrayList<Polygon> polygons;
    public boolean direction = true;
    public int ticker = 0;
    public JFrame frame;

    public DrawPolygons(){
        polygons = new ArrayList<>(10);

        polygons.add(createSquare(50,50));
        polygons.add(createTriangle(100,100));
        polygons.add(createRectangle(50,150));

    }//constructor

    public void update(){
        ticker++;
        int value = direction ? 10 : -10;
        for (Polygon p: polygons){
            p.setCenter(p.getCenter().x + value, p.getCenter().y+value);
        }
        if (ticker > 10) {
            direction = !direction;
            ticker = 0;
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        for (Polygon currentPolygon : polygons) {
            currentPolygon.paint(g);
        }
    }//paint

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        DrawPolygons polygons = new DrawPolygons();
        polygons.frame = frame;

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(30,30,300,300);
        frame.getContentPane().add(polygons);
        frame.setVisible(true);


        try {
            while (true) {
                Thread.sleep(500);
                polygons.update();
            }
        } catch (InterruptedException e) {}

    }//main

}//DIT953.polygons.DrawPolygons