
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Eldin on 4/9/2015.
 * This class contains all the atributes needed for a bin.
 */
public class Bin {

    private ArrayList<Integer> binDimensions;
    private ArrayList<ArrayList> visuals;

    private int bheight;
    private int bwidth;

    private int binContentHeight;
    private int binContentHeightSteps;

    private int packetPosY = 0;
    private int amountOfPackages = 0;

    public Bin(int posX, int posY, int bwidth, int bheight, int binContentHeight){
        binDimensions = new ArrayList<Integer>();
        visuals = new ArrayList<ArrayList>();

        this.bheight = bheight;
        this.bwidth = bwidth;
        this.binContentHeight = binContentHeight;

        binContentHeightSteps = bheight / binContentHeight;

        binDimensions.add(binContentHeight);
        binDimensions.add(posX);
        binDimensions.add(posY);
        binDimensions.add(bwidth);
        binDimensions.add(bheight);
        binDimensions.add(0);
        visuals.add(binDimensions);

        System.out.println("added bin " + posX + "|" + binDimensions.get(1));
    }

    public void addPacket(Packet packet){

        int packetContentHeight = packet.getPacketContentHeight();

        if (packetContentHeight < binContentHeight){
            binContentHeight = binContentHeight - packetContentHeight;
        }

        int packetPixelWidth = bwidth - 20;
        int packetPixelHeight = binContentHeightSteps * packetContentHeight;
        int packetPosX = binDimensions.get(1) + 10;
        if(packetPosY == 0){
            packetPosY = bheight + binDimensions.get(2) - packetPixelHeight;
        } else {
            packetPosY = packetPosY  - packetPixelHeight;
        }


        ArrayList<Integer> packetDimensions = new ArrayList<Integer>();
        packetDimensions.add(packetContentHeight);
        packetDimensions.add(packetPosX);
        packetDimensions.add(packetPosY);
        packetDimensions.add(packetPixelWidth);
        packetDimensions.add(packetPixelHeight);
        packetDimensions.add(1);

        visuals.add(packetDimensions);

        amountOfPackages++;
    }

    public int getAmountOfPackages() {
        return amountOfPackages;
    }

    public ArrayList<ArrayList> getBinVisuals() {
        return visuals;
    }
}
