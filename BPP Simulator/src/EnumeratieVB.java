import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

    /**
     * Created by ewart on 23-4-2015.
     */
public class EnumeratieVB implements Algoritme{

    private ArrayList<Packet> order;//, bestCombination, leftArray;
    private Bin bin1, bin2;
    private Drawer draw;
    private MainScreen parentscreen;
    private boolean stop = false;
    private long difference;
    private ArrayList<Packet> bestCombination;
    private int screen;


        private Stack<Packet> stack = new Stack<>();

        /** Store the sum of current elements stored in stack */

    public EnumeratieVB(Bin bin1, Bin bin2, MainScreen parentscreen, Drawer draw, int screen) {
        this.bin1 = bin1;
        this.bin2 = bin2;
        this.parentscreen = parentscreen;
        this.draw = draw;
        difference = 0;
        this.bestCombination = new ArrayList<>();
        this.screen = screen;
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


//                long lStartTime = System.nanoTime();
                long lStartTime = System.currentTimeMillis();
                ArrayList<Packet> tempOrder = new ArrayList<>();

                for(Packet pInOrder : order){
                    tempOrder.add(pInOrder);
                }
                Bin highestBin;
                Bin lowestBin;
                while(!tempOrder.isEmpty()) {
                    if (bin1.getBinCapacityHeight() >= bin2.getBinCapacityHeight()) {
                        highestBin = bin1;
                        lowestBin = bin2;
                    } else {
                        highestBin = bin2;
                        lowestBin = bin1;

                    }
                    BestCombo bo = new BestCombo(tempOrder.size(), highestBin.getBinCapacityHeight());
                    bestCombination = bo.calculateTarget(tempOrder, 0, tempOrder.size());
                    System.out.print(" Beste StackId: " + bo.getBestStackId());
                    System.out.print(" combo: {");
                    for(Packet p: bestCombination) {
                        System.out.print(p.getPacketHeight() + ",");
                    }
                    System.out.println("}");

                    if(calculateStackHeight(highestBin.getPackets()) == highestBin.getBinCapacityHeight() && calculateStackHeight(bestCombination) == highestBin.getBinCapacityHeight()) highestBin.emptyBin();
                    for (int x = 0; x < bo.getBestStackId();x++ ) {
                        if(parentscreen.getDelay() >= 50){
                            parentscreen.delay(false);
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    draw.repaint();
                                }
                            });
                        }
                        if(bestCombination.contains(tempOrder.get(x))) {
                            highestBin.addPacket(tempOrder.get(x), "Enumeratie");
                        } else {
                            if(lowestBin.getBinCapacityLeft() < tempOrder.get(x).getPacketHeight()) {
                                lowestBin.emptyBin();
                            }
                            lowestBin.addPacket(tempOrder.get(x), "Enumeratie");
                        }

                    }
                    for(int x = 0; x < bo.getBestStackId();x++) {
                        tempOrder.remove(0);

                    }

                }
                long lEndTime = System.currentTimeMillis();
                difference = lEndTime - lStartTime;

                parentscreen.addToResult(screen, bin1, bin2, difference);

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


}
