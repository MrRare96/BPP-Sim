import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Eldin on 4/25/2015.
 * Enumeration algoritm, using a set order of packets
 */
public class EnumeratieVE implements Algoritme{

    //creates all the arraylist needed to calculate the order in which packets should be added to the bins using enumeration.
    private ArrayList<Packet> order = new ArrayList<Packet>();
    private ArrayList<Packet> packetsUsedStartSum;
    private ArrayList<Packet> packetsUsedAdderSum;
    private ArrayList<Packet> tempOrder;
    private ArrayList<Packet> bestCombination;

    //needed to draw a visual simulation on the mainscreen and starting / stopping the alogoritm using the buttons
    private MainScreen parentscreen;
    private Drawer draw;

    private boolean stop = false;

    //defines the bins
    private Bin binL, binR;

    public EnumeratieVE(Bin bin1, Bin bin2, MainScreen parentscreen, Drawer draw, int screen) {

        /*
         * CONSTRUCTOR
         */

        //initiates the arraylists on initiation of this class
        tempOrder = new ArrayList<>();
        packetsUsedStartSum = new ArrayList<>();
        packetsUsedAdderSum = new ArrayList<>();
        bestCombination = new ArrayList<>();

        //sets the screen so it can visualize the simulation
        this.parentscreen = parentscreen;
        this.draw = draw;

        //sets the bins
        binL = bin1;
        binR = bin2;

    }

    public boolean enumeratie(){

        /*
         * This method contains the logics for calculating the order of packets using enumerations.
         */

        boolean found = false;

        //sets the variables needed to calculate
        int packCap;
        int startSum = 0;

        int firstLoopCounter = 0;
        int secondLoopCounter;

        //target is the optimal capacity number, which equals to the left bin capacity
        int target = binL.getBinCapacityHeight();

        //used to keep track of the amount of loops has been done
        int x = 0;

        //loops until in one way it found the best possible solution
        while(x != tempOrder.size()) {

            if(stop) break;

            //if no solution is found, it needs to reset all the containers.
            bestCombination.clear();
            packetsUsedStartSum.clear();

            start:
            for (Packet pack1 : tempOrder) {

                found = false;

                packCap = pack1.getPacketHeight();

                startSum = packCap + startSum;

                //adds the packet used in the startsum to starstum array, later to be combined with the addersum array
                packetsUsedStartSum.add(pack1);

                firstLoopCounter++;

                //resets the adder
                int adder = 0;

                //keeps track of second loop
                secondLoopCounter = 0;

                for (Packet pack2 : tempOrder) {

                    packCap = pack2.getPacketHeight();

                    if (firstLoopCounter <= secondLoopCounter) {

                        //adds the packCap to the adder
                        adder = adder + packCap;

                        //adds used pack in the adder to a temporary array
                        packetsUsedAdderSum.add(pack2);

                        /*
                         the adder and teh startsum will be summed up and compared to the target
                         when those are equal the packets used in the startsum array and in the adder array
                         will be added to the bestcombination array
                         */
                        if (target == (adder + startSum)) {

                            for(Packet packetu1 : packetsUsedAdderSum){
                                packetsUsedStartSum.add(packetu1);
                            }

                            bestCombination = packetsUsedStartSum;

                            packetsUsedAdderSum.clear();

                            found = true;
                            //it will end both for loops
                            break start;
                        } else {
                            //if nothing == target
                            found = false;
                        }

                    }

                    secondLoopCounter++;

                    //resets adder
                    adder = 0;

                }

                //clears the addersum array
                packetsUsedAdderSum.clear();

            }

            /*
            when no combinations are found, the target number will be adjusted (-1) until it finds something
            when it does not find anything it will break the loop. if there is a combination found, this loop will
            also end.
             */
            if(!found){
                target = target - 1;
                if(target <= 0){
                    break;
                }
            } else {
                break;
            }

        }

        return found;
    }

    public void setOrder(ArrayList<Packet> order){
        this.order = order;
    }

    public void startAlgo(final int outputNumber){

        Thread runEnumeratie = new Thread(new Runnable() {
            @Override
            public void run() {

                //this will add all packets to a temporary order list(so things can be removed)
                for(Packet pInOrder : order){
                    tempOrder.add(pInOrder);
                }

                //starts the first enumeratie loop
                boolean found = enumeratie();

                //sets start time in nano seconds
                long lStartTime = System.nanoTime();

                //loop counter
                int x = 0;
                int y = 0;

                for(Packet pack : order){

                    if(stop) break;

                    /*
                     * When a combination has been found, for each packet in the order a for loop runs over bestcombination
                     * array, when the packet exists in the bestcombination arraylist, the packet will be added to the left
                     * bin, if not, it will be added to the right bin. When the loop counter equals the size of the
                     * bestcombination array, means all packets in best combination are added to the left bin,
                     * enumeration will start again with the remaining packets, every packet added to a bin will be removed
                     * from the temporder arraylist.
                     */
                    if(found){


                            if(bestCombination.contains(pack)){
                                binL.addPacket(pack);
                                x++;
                            } else {
                                if(bestCombination.size() == x || binR.getBinCapacityLeft() < pack.getPacketHeight()){
                                    x = y;
                                    System.out.println("enum started");
                                    bestCombination.clear();
                                    y++;
                                    found = enumeratie();
                                }

                                binR.addPacket(pack);
                            }




                    } else {
                        break;
                    }

                    //removes pack from tempOrder, since it has been added to a bin
                    tempOrder.remove(pack);

                    //repaints every time a pack has been added.
                    if(parentscreen.getDelay() >= 50) {
                        parentscreen.delay();
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                draw.repaint();
                            }
                        });
                    }
                }

                //when delay has been turned off, it will only repaint when everything has been finishd.
                if(parentscreen.getDelay() == 0){
                    draw.repaint();
                }

                //sets end time and calculates how much times has been passed
                long lEndTime = System.nanoTime();
                long difference = lEndTime - lStartTime;

                //prints the result
                parentscreen.addToResult(outputNumber, binL, binR, difference);

            }
        });

        //starts the algoritm
        runEnumeratie.start();

        stop = false;

    }

    public void stopAlgo(){
        stop = true;
    }

}