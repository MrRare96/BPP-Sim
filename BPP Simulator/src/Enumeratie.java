    import javax.swing.*;
    import java.util.ArrayList;
    import java.util.Random;
    /**
     * Created by ewart on 23-4-2015.
     */
public class Enumeratie implements Algoritme{

    private ArrayList<Packet> order, bestCombination, leftArray;
    private Bin bin1, bin2;
    private Drawer draw;
    private MainScreen parentscreen;
    private boolean stop = false;
    private long difference;
    private int bestStackId;

    public Enumeratie(Bin bin1, Bin bin2, MainScreen parentscreen, Drawer draw, int screen) {
        this.bin1 = bin1;
        this.bin2 = bin2;
        this.parentscreen = parentscreen;
        this.draw = draw;
        difference = 0;
        this.bestCombination = new ArrayList<Packet>();
        this.bestStackId = 100;
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
                while(handledCount > order.size()) {
                    populateSubset(order, handledCount, stack, 0, bin1.getBinCapacityHeight());

                    handledCount += bestStackId;
                }



                for(Packet packet : order){



                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            draw.repaint();
                        }
                    });
                }

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
                               final int target) {



        if (calculateStackHeight(stack) == target && stackId < bestStackId) {
            this.bestCombination = stack;
            this.bestStackId = stackId;
        }

        while (fromIndex < data.size() && data.get(fromIndex).getPacketHeight() > bin1.getBinCapacityLeft()) {
            // take advantage of sorted data.
            // we can skip all values that are too large.
            fromIndex++;
        }
        while (fromIndex < data.size() && fromIndex > bestStackId) {
            // take advantage of sorted data.
            // we can skip all values that are worse then the current best solution
            fromIndex++;
        }
        while (fromIndex < data.size() && data.get(fromIndex).getPacketHeight()+ calculateStackHeight(stack) <= target) {
            // stop looping when we run out of data, or when we overflow our target.
            stack.add(order.get(fromIndex));
            stackId = fromIndex;

            populateSubset(data, fromIndex + 1, stack, stackId, target);
            fromIndex++;
        }
    }
}
