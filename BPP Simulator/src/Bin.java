
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Eldin on 4/9/2015.
 * This class contains all the atributes needed for a bin.
 */
public class Bin {

    private ArrayList<Packet> packets;

    private int binCapacityHeight, timesEmptied;;


    public Bin(int binCapacityHeight){

        /**
         * this method stores the data used to define a bin, including its position on the screen and its capacity
         */
        this.binCapacityHeight = binCapacityHeight;
        this.packets = new ArrayList<Packet>();
        this.timesEmptied = 0;
    }

    public int getBinCapicityFilled() {
        int binCapicity = 0;
        for( Packet p: packets) {
            binCapicity += p.getPacketHeight();
        }
        return binCapicity;
    }

    public int getBinCapacityHeight() {
        return binCapacityHeight;
    }

    public int getBinCapacityLeft() { return binCapacityHeight - getBinCapicityFilled();}

    public void addPacket(Packet packet){
        /**
         * This method adds a packet to the bin, it also defines its position for drawing on the screen, so that it looks like that its inside of the bin
         */
        if((packet.getPacketHeight() + getBinCapicityFilled()) < binCapacityHeight){
            packets.add(packet);
        } else {
            emptyBin();
            packets.add(packet);
        }
        
    }

    public int getTimesEmptied() {
        return timesEmptied;
    }

    public void emptyBin(){

        timesEmptied++;
        packets.clear();
    }

    public void setBinCapacityHeight(int binCapacityHeight) {
        this.binCapacityHeight = binCapacityHeight;
    }

    public ArrayList<Packet> getPackets() {
        return packets;
    }
}
