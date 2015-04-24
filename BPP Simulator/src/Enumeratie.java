    import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

    import javax.swing.*;
    import java.util.ArrayList;
    import java.util.Random;
    /**
     * Created by ewart on 23-4-2015.
     */
public class Enumeratie implements Algoritme{

    private ArrayList<Packet> order;//, bestCombination, leftArray;
    private Bin bin1, bin2;
    private Drawer draw;
    private MainScreen parentscreen;
    private boolean stop = false;
    private long difference;
    private ArrayList<Packet> bestCombination = new ArrayList<Packet>();
    private int bestStackId = 10000;
    private ArrayList<Packet> leftArray = new ArrayList<Packet>();

    public Enumeratie(Bin bin1, Bin bin2, MainScreen parentscreen, Drawer draw, int screen) {
        this.bin1 = bin1;
        this.bin2 = bin2;
        this.parentscreen = parentscreen;
        this.draw = draw;
        difference = 0;
//        this.bestCombination = new ArrayList<Packet>();
//        this.bestStackId = 100;
//        this.leftArray = new ArrayList<Packet>();
    }

    public void setOrder(ArrayList<Packet> order){
        this.order = order;
    }

    public void startAlgo(final int outputNumber){

        /**
         * this method contains a thread, this thread runs a for loop over a order array which contains packets
         * a random number generator and a equation to check if the number is even or odd is used to determine
         * if the packet in the the order array is supposed to go left or right.
         */

        Thread algoritm = new Thread(new Runnable() {
            @Override
            public void run() {


                long lStartTime = System.nanoTime();
                ArrayList<Packet> stack = new ArrayList<Packet>();
                int handledCount = 0;


                while(handledCount < order.size()) {
                    populateSubset(order, handledCount, stack, 0, bin1.getBinCapacityHeight(), 0);
                    System.out.println("combo: " + calculateStackHeight(bestCombination) + ", StackId: " + bestStackId);
//                    for(Packet p: bestCombination) {
//                        leftArray.add(p);
//                    }
//                    bestCombination.clear();


                    for( int x = handledCount; x <= bestStackId; x++) {
                        parentscreen.delay();
                        if(bestCombination.contains(order.get(x))) {
                            bin1.addPacket(order.get(x));
                        } else {
                            bin2.addPacket(order.get(x));
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                draw.repaint();
                            }
                        });
                    }
                    handledCount = bestStackId + 1;
                    bestStackId = 10000;

                }

//                for(Packet packet : order){
//                    parentscreen.delay();
//
//                    if(leftArray.contains(packet)) {
//                        bin1.addPacket(packet);
//                    } else {
//                        bin2.addPacket(packet);
//                    }
//
//
//                    SwingUtilities.invokeLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            draw.repaint();
//                        }
//                    });
//                }

                long lEndTime = System.nanoTime();
                difference = lEndTime - lStartTime;

                parentscreen.addToResult(outputNumber, bin1, bin2, difference);

            }
        });

        algoritm.start();

        stop = false;
    }

    public void stopAlgo(){
        stop = true;
    }

    public int calculateStackHeight(ArrayList<Packet> stack){
        int stackHeight = 0;
        for( Packet p: stack) {
            stackHeight += p.getPacketHeight();
        }
        return stackHeight;
    }
    public void populateSubset(ArrayList<Packet> data, int fromIndex,
                               ArrayList<Packet> stack, int stackId,
                               final int target, int wrongBin) {

        while(fromIndex < bin1.getBinCapacityHeight()) {
            //check if combo is equal or under bincapicity, if current combo is equal or higher then the best combo. if the last Id of combie is lower then the best Combo


            while (fromIndex < data.size()
                    && data.get(fromIndex).getPacketHeight() + calculateStackHeight(stack) > target) {
                // skip packages higher then capacity Left
                fromIndex++;
                wrongBin += data.get(fromIndex).getPacketHeight();

                if(wrongBin > target){
                    fromIndex = data.size();
                }
            }
            while (fromIndex < data.size() && fromIndex > bestStackId) {
                //skip packages when Id is higher then Best ID
                fromIndex++;

            }
            while (fromIndex < data.size() && data.get(fromIndex).getPacketHeight() + calculateStackHeight(stack) <= target) {
                // stop looping when we run out of data, or when we overflow our target.
                stack.add(order.get(fromIndex));
                stackId = fromIndex;

                populateSubset(data, fromIndex + 1, stack, stackId, target, wrongBin);
                fromIndex++;
            }
            if (    calculateStackHeight(stack) <= target
                    && calculateStackHeight(stack) >= calculateStackHeight(bestCombination)
                    && stackId < bestStackId
                    && wrongBin <= target) {
                System.out.println(calculateStackHeight(stack) + " | " + calculateStackHeight(bestCombination) + " | " +  stackId);
//                this.bestCombination.clear();
                this.bestCombination = stack;
                this.bestStackId = stackId;
            }

        }

    }

//    public void binPlacer(){
//        for(Packet pack : order){
//            if(leftArray.contains(pack)) {
//                bin1.addPacket(pack);
//            } else {
//                if(bin2.getBinCapacityLeft() < pack.getPacketHeight()){
//                    bin1.emptyBin();
//                }
//                bin2.addPacket(pack);
//            }
//        }
//    }
}
