
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Eldin on 4/9/2015.
 * This class contains all the atributes needed for a bin.
 */
public class Bin {

    private ArrayList<Packet> packets;
    private int binCapacityHeight;


    public Bin(int binCapacityHeight){

        /**
         * this method stores the data used to define a bin, including its position on the screen and its capacity
         */
        this.binCapacityHeight = binCapacityHeight;
        this.packets = new ArrayList<Packet>();
    }

    public int getBinCapicity() {
        int binCapicity = 0;
        for( Packet p: packets) {
            binCapicity += p.getPacketHeight();
        }
        return binCapicity;
    }

    public int getBinCapacityHeight() {
        return binCapacityHeight;
    }

    public void addPacket(Packet packet){
        /**
         * This method adds a packet to the bin, it also defines its position for drawing on the screen, so that it looks like that its inside of the bin
         */
        if((packet.getPacketHeight() + getBinCapicity()) < binCapacityHeight){
            packets.add(packet);
        } else {
            System.err.println("can't add packet to bin.");
            System.err.println("packet cant exceed bincapicityheight.");
        }
        
    }

    public int getAmountOfPackages() {
        return packets.size();
    }

    public ArrayList<Packet> getPackets() {
        return packets;
    }
}
