
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

    private boolean autoEmpty = true;

    public Bin(int binCapacityHeight){

        /**
         * this method stores the data used to define a bin, such as its capacity and which packets it contains and how many times it has been emptied.
         */
        this.binCapacityHeight = binCapacityHeight;
        this.packets = new ArrayList<>();
        this.timesEmptied = 0;
    }

    public int getBinCapicityFilled() {
        /**
         * this method returns the capacity thats in use of the bin
         */
        int binCapicity = 0;
        for( Packet p: packets) {
            binCapicity += p.getPacketHeight();
        }
        return binCapicity;
    }

    public int getBinCapacityHeight() {
        return binCapacityHeight;
    }

    public int getBinCapacityLeft() {
        /**
         * this method returns the empty capacity in the bin
         */
        return binCapacityHeight - getBinCapicityFilled();
    }

    public void setTimesEmptied(int timesEmptied) {
        this.timesEmptied = timesEmptied;
    }

    public void addPacket(Packet packet) {
        /**
         * This method adds a packet to the bin, it checks if it fits, if not it empties the bin
         * and add it after it is emptied.
         */

            if((packet.getPacketHeight() <= getBinCapacityLeft())) {
                packets.add(packet);
            } else {
                System.err.println("packet cant exceed binHeight");
            }


    }

    public int getTimesEmptied() {
        return timesEmptied;
    }

    public void emptyBin(){
        /**
         * empties the bin
         */
        timesEmptied++;
        packets.clear();
    }

    public void setAutoEmpty(boolean autoEmpty) {
        this.autoEmpty = autoEmpty;
    }

    public void setBinCapacityHeight(int binCapacityHeight) {
        this.binCapacityHeight = binCapacityHeight;
    }

    public ArrayList<Packet> getPackets() {
        return packets;
    }
}
