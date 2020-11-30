package DIT953.shapes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Niklas on 2016-02-14.
 */
public abstract class Shape extends JComponent {
    private Point centerPoint;
    private int scaleX = 10;
    private int scaleY = 10;
    private double rotation = 0; // Degrees clockwise

    public Point getCenterPoint() {
        return centerPoint;
    }
    public int getScaleX() {
        return scaleX;
    }
    public int getScaleY() {
        return scaleY;
    }
    public double getRotation() {
        return rotation;
    }

    private Shape(Point center){
        this.centerPoint = center;
   }
    public Shape(int x, int y){
        this(new Point(x,y));
    }

    public void translate(int x, int y){
        centerPoint.move(centerPoint.x + x,centerPoint.y + y);
    }
    public void scale(int x, int y){
        this.scaleX = scaleX * x;
        this.scaleY = scaleY * y;
    }
    public void rotate(double angle){
        this.rotation = rotation + angle;
    }

    public abstract void paint(Graphics g);

}