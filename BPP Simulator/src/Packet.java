import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eldin on 4/10/2015 for Windesheim Magazijn Robot KBS
 */
public class Packet{

    private int packetCapacityHeight = 0;
    private Color color;
    private ArrayList<Color> colorList;
    public Packet(int packetCapacityHeight){
        this.packetCapacityHeight = packetCapacityHeight;
        Random rand = new Random();

        int  rc = rand.nextInt(255);
        int  gc = rand.nextInt(255);
        int bc = rand.nextInt(255);
        this.colorList = new ArrayList<Color>();
        colorList.add(Color.red);
        colorList.add(Color.yellow);
        colorList.add(Color.green);
        colorList.add(Color.blue);
        colorList.add(Color.cyan);
        colorList.add(Color.magenta);

        int randColor = rand.nextInt(colorList.size());

//        this.color = colorList.get(randColor);
        this.color = new Color(rc, gc, bc);
    }

    public Color getColor() {
        return color;
    }

    public int getPacketHeight() {
        return packetCapacityHeight;
    }


}
