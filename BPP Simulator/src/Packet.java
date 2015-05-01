import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ewart en Eldin on 4/10/2015 for Windesheim Magazijn Robot KBS
 * This class is used to define a packet
 */
public class Packet{

    private int packetCapacityHeight;
    private Color color;

    public Packet(int packetCapacityHeight){

        /**
         * a packet contains a capacity parameter and a color which is randomly generated.
         */

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
