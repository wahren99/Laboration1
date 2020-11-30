package DIT953.shapes;

/**
 * Created by Niklas on 2016-02-14.
 */
public class Triangle extends Polygon {
    public Triangle(int x, int y, int sizeX, int sizeY, double rotation){
        super(x,y);
        this.scale(sizeX,sizeY);
        this.rotate(rotation);
    }
    public Triangle(int x, int y, int sizeX, int sizeY){
        super(x,y);
        this.scale(sizeX,sizeY);
    }
    public Triangle(int x, int y, double rotation){
        super(x,y);
        this.rotate(rotation);
    }
    public Triangle(int x, int y){
        super(x,y);
    }

    protected int[][] getOffsets() {
        int xOffset = getScaleX() / 2;
        int yOffset = getScaleY() / 3;
        int[][] offsets = {
                { 0,       -yOffset*2},
                {-xOffset,  yOffset  },
                { xOffset,  yOffset  }
        };
        return offsets;
    }
}
