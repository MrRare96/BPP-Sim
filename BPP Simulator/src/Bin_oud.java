//import java.util.ArrayList;
//
///**
// * Created by Eldin on 4/9/2015.
// * This class contains all the atributes needed for a bin.
// */
//public class Bin_oud {
//
//    private ArrayList<Integer> binDimensions;
//    private ArrayList<ArrayList> visuals;
//    private ArrayList<Packet> packets;
//
//    private int bheight;
//    private int bwidth;
//
//    private int binCapacityHeight;
//    private int binCapacityHeightSteps;
//
//    private int packetPosY = 0;
//    private int amountOfPackages = 0;
//
//    public Bin_oud(int posX, int posY, int bwidth, int bheight, int binCapacityHeight){
//
//        /**
//         * this method stores the data used to define a bin, including its position on the screen and its capacity
//         */
//
//        //these two arrays are needed to store the dimensions for the bin and stores the bin in a visuals container, which include
//        //packets
//        binDimensions = new ArrayList<Integer>();
//        visuals = new ArrayList<ArrayList>();
//
//        this.bheight = bheight;
//        this.bwidth = bwidth;
//        this.binCapacityHeight = binCapacityHeight;
//
//        binCapacityHeightSteps = bheight / binCapacityHeight;
//
//        binDimensions.add(binCapacityHeight);
//        binDimensions.add(posX);
//        binDimensions.add(posY);
//        binDimensions.add(bwidth);
//        binDimensions.add(bheight);
//        binDimensions.add(0);
//        visuals.add(binDimensions);
//
//    }
//
//    public int getBinCapicity() {
//        int binCapicity = 0;
//        for( Packet p: packets) {
//            binCapicity += p.getPacketHeight();
//        }
//        return binCapicity;
//    }
//
//    public void addPacket(Packet packet){
//        /**
//         * This method adds a packet to the bin, it also defines its position for drawing on the screen, so that it looks like that its inside of the bin
//         */
//        int packetCapacityHeight = packet.getPacketHeight();
//
//        //this counts the max capacity of the bin, if its higher it wont calculate any further
//        if (packetCapacityHeight < getBinCapicity()){
//            binCapacityHeight = binCapacityHeight - packetCapacityHeight;
//        }
//
//        //defines the position relative to the bin
//        int packetPixelWidth = bwidth - 20;
//        int packetPixelHeight = binCapacityHeightSteps * packetCapacityHeight;
//        int packetPosX = binDimensions.get(1) + 10;
//        if(packetPosY == 0){
//            packetPosY = bheight + binDimensions.get(2) - packetPixelHeight;
//        } else {
//            packetPosY = packetPosY  - packetPixelHeight;
//        }
//
//        //adds those dimensions in a temporary array that wiill be added to the visuals array
//        ArrayList<Integer> packetDimensions = new ArrayList<Integer>();
//        packetDimensions.add(packetCapacityHeight);
//        packetDimensions.add(packetPosX);
//        packetDimensions.add(packetPosY);
//        packetDimensions.add(packetPixelWidth);
//        packetDimensions.add(packetPixelHeight);
//        packetDimensions.add(1);
//
//        visuals.add(packetDimensions);
//
//        //counts the total amount of packages stored inside the bin, could be useful in the future
//        amountOfPackages++;
//
//    }
//
//    public int getAmountOfPackages() {
//        return amountOfPackages;
//    }
//
//    public ArrayList<ArrayList> getBinVisuals() {
//        return visuals;
//    }
//}
