
/**
 * Created by Eldin on 4/10/2015 for Windesheim Magazijn Robot KBS
 */
public class Packet{

    private int packetContentHeight = 0;
    private int packetID;

    public Packet(int packetID, int packetContentHeight){
        this.packetContentHeight = packetContentHeight;
        this.packetID = packetID;
    }

    public int getPacketContentHeight() {
        return packetContentHeight;
    }

    public int getPacketID() {
        return packetID;
    }

}
