
/**
 * Created by Eldin on 4/10/2015 for Windesheim Magazijn Robot KBS
 */
public class Packet{

    private int packetCapacityHeight = 0;
    private int packetID;

    public Packet(int packetID, int packetCapacityHeight){
        this.packetCapacityHeight = packetCapacityHeight;
        this.packetID = packetID;
    }

    public int getPacketHeight() {
        return packetCapacityHeight;
    }

    public int getPacketID() {
        return packetID;
    }

}
