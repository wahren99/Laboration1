package DIT953.shapes;

/**
 * Created by Niklas on 2016-02-14.
 */
public class Rectangle extends Polygon {

    public Rectangle(int x, int y, int sizeX, int sizeY, double rotation){
        super(x,y);
        this.scale(sizeX,sizeY);
        this.rotate(rotation);
    }
    public Rectangle(int x, int y, int sizeX, int sizeY){
        super(x,y);
        this.scale(sizeX,sizeY);
    }
    public Rectangle(int x, int y, double rotation){
        this(x,y,1,1,rotation);
    }
    public Rectangle(int x, int y){
        this(x,y,1,1);
    }

    protected int[][] getOffsets() {
        int xOffset = getScaleX() / 2;
        int yOffset = getScaleY() / 2;
        int[][] offsets = {
                {-xOffset, -yOffset},
                { xOffset, -yOffset},
                { xOffset,  yOffset},
                {-xOffset,  yOffset}
        };
        return offsets;
    }

}
