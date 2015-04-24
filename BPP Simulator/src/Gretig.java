
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
    /**
     * Created by ewart on 23-4-2015.
     */
public class Gretig implements Algoritme{

    private ArrayList<Packet> order;
    private Bin bin1, bin2;
    private Drawer draw;
    private MainScreen parentscreen;

    private boolean stop = false;
    private long difference;


    public Gretig(Bin bin1, Bin bin2, MainScreen parentscreen, Drawer draw, int screen) {
        this.bin1 = bin1;
        this.bin2 = bin2;
        this.parentscreen = parentscreen;
        this.draw = draw;
        difference = 0;
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

                for(Packet packet : order){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(stop){
                        break;
                    }

                    if(bin1.getBinCapacityLeft() <= packet.getPacketHeight()) {
                        if(bin2.getBinCapacityLeft() <= packet.getPacketHeight()) {
                            bin1.addPacket(packet);
                            bin2.emptyBin();
                        }
                            bin2.addPacket(packet);

                    } else {
                        bin1.addPacket(packet);
                    }

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

}
