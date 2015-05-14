import java.util.ArrayList;

/**
 * Created by Eldin on 4/9/2015.
 * This class contains all the atributes needed for a bin.
 */
public class Bin {

    private ArrayList<Packet> packets;

    private int binCapacityHeight, timesEmptied;;
    private int emptiedHeightLeft[];
    private boolean autoEmpty = true;

    public Bin(int binCapacityHeight){

        /**
         * this method stores the data used to define a bin, such as its capacity and which packets it contains and how many times it has been emptied.
         */
        this.binCapacityHeight = binCapacityHeight;
        this.packets = new ArrayList<>();
        this.timesEmptied = 0;
        this.emptiedHeightLeft = new int[binCapacityHeight];
    }

    public int[] getEmptiedHeightLeft() {
        return emptiedHeightLeft;
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
    public void resetEmptiedHeightLeft() {
        this.emptiedHeightLeft = null;
        this.emptiedHeightLeft = new int[binCapacityHeight];
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

    public void addPacket(Packet packet, String algo) {
        /**
         * This method adds a packet to the bin, it checks if it fits, if not it empties the bin
         * and add it after it is emptied.
         */

            if((packet.getPacketHeight() <= getBinCapacityLeft())) {
                packets.add(packet);
            } else {
                System.err.print("Packet cant exceed binHeight");
                System.err.println(", Algo :" + algo);
            }


    }

    public int getTimesEmptied() {
        return timesEmptied;
    }

    public void addBinLeftover(int capicityLeft) {
        emptiedHeightLeft[capicityLeft]++;
    }
    public void emptyBin(){
        /**
         * empties the bin
         */
        try{
            addBinLeftover(getBinCapacityLeft());
        } catch (Exception e){

        }
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
