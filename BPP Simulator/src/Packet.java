import java.awt.*;
import java.util.Random;

/**
 * Created by Eldin on 4/10/2015 for Windesheim Magazijn Robot KBS
 */
public class Packet{

    private int packetCapacityHeight = 0;
    private Color color;
    public Packet(int packetCapacityHeight){
        this.packetCapacityHeight = packetCapacityHeight;
        Random rand = new Random();

        int  rc = rand.nextInt(255);
        int  gc = rand.nextInt(255);
        int bc = rand.nextInt(255);
        this.color = new Color(rc, gc, bc);
    }

    public Color getColor() {
        return color;
    }

    public int getPacketHeight() {
        return packetCapacityHeight;
    }


}
