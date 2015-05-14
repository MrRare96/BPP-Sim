import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Eldin on 4/25/2015.
 * Enumeration algoritm, using a set order of packets
 */
public class LookUp implements Algoritme{

    //creates all the arraylist needed to calculate the order in which packets should be added to the bins using enumeration.
    private ArrayList<Packet> order = new ArrayList<Packet>();

    //needed to draw a visual simulation on the mainscreen and starting / stopping the alogoritm using the buttons
    private MainScreen parentscreen;
    private Drawer draw;

    private boolean stop = false;

    //defines the bins
    private Bin binL, binR;

    public LookUp(Bin bin1, Bin bin2, MainScreen parentscreen, Drawer draw, int screen) {

        /*
         * CONSTRUCTOR
         */
        //sets the screen so it can visualize the simulation
        this.parentscreen = parentscreen;
        this.draw = draw;

        //sets the bins
        binL = bin1;
        binR = bin2;

    }


    public void setOrder(ArrayList<Packet> order){
        this.order = order;
    }

    public void startAlgo(final int outputNumber){

        Thread runEnumeratie = new Thread(new Runnable() {
            @Override
            public void run() {

                long lStartTime = System.currentTimeMillis();
                for(Packet pack : order){

                    if(stop) break;

                    if(parentscreen.getDelay() >= 50) {
                        parentscreen.delay(false);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                draw.repaint();
                            }
                        });
                    }



                   if(binL.getBinCapacityLeft() >= pack.getPacketHeight()){


                        if(binL.getBinCapacityHeight() >=  binR.getBinCapacityHeight()){
                            if(binL.getBinCapacityLeft() - pack.getPacketHeight() > binR.getBinCapacityLeft() - pack.getPacketHeight()){
                                if(binL.getBinCapacityLeft() < pack.getPacketHeight()){
                                    binL.emptyBin();
                                }
                                binL.addPacket(pack, "LookUP");
                            } else {
                                if(binR.getBinCapacityLeft() < pack.getPacketHeight()){
                                    binR.emptyBin();
                                }
                                binR.addPacket(pack, "LookUP");

                            }
                        } else {
                            if(binR.getBinCapacityLeft() - pack.getPacketHeight() > binL.getBinCapacityLeft() - pack.getPacketHeight()){
                                if(binR.getBinCapacityLeft() < pack.getPacketHeight()){
                                    binR.emptyBin();
                                }
                                binR.addPacket(pack, "LookUP");
                            } else {
                                if(binL.getBinCapacityLeft() < pack.getPacketHeight()){
                                    binL.emptyBin();
                                }
                                binL.addPacket(pack, "LookUP");

                            }
                        }

                    } else {
                        if(binR.getBinCapacityLeft() < pack.getPacketHeight()){
                            binR.emptyBin();
                            if(binL.getBinCapacityLeft() < 2){
                                binL.emptyBin();
                            }
                        }
                        binR.addPacket(pack, "LookUP");
                    }




                }

                //when delay has been turned off, it will only repaint when everything has been finishd.
                if(parentscreen.getDelay() == 0){
                    draw.repaint();
                }

                //sets end time and calculates how much times has been passed
                long lEndTime = System.currentTimeMillis();
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