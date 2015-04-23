import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eldin on 4/22/2015.
 */
public class SimpelGretig implements Algoritme{

    private ArrayList<Packet> order;
    private Bin bin1, bin2;
    private MainScreen parentscreen;
    private boolean stop = false;
    private Drawer draw;
    private long difference;


    public SimpelGretig(Bin bin1, Bin bin2, MainScreen parentscreen, Drawer draw) {
        this.bin1 = bin1;
        this.bin2 = bin2;
        this.parentscreen = parentscreen;
        this.draw = draw;
    }

    public void setOrder(ArrayList<Packet> order){
        this.order = order;
    }

    public void startAlgo(){

        Thread test = new Thread(new Runnable() {
            @Override
            public void run() {

                long lStartTime = System.nanoTime();

                for(Packet packet : order){

//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    if(stop){
                        break;
                    }

                    Random rand = new Random();
                    int random = rand.nextInt(2);
                    if((random % 2) == 0){
                        bin1.addPacket(packet);
                    } else {
                        bin2.addPacket(packet);
                    }

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
//                            draw.repaint();
                        }
                    });

                }
                long lEndTime = System.nanoTime();

                difference = lEndTime - lStartTime;

                parentscreen.addToResult("\r\n" + "----------------RESULT FROM SIMPEL GRETIG--------------------" +
                        "\r\n" +
                        "Left bin: " + bin1.getTimesEmptied() + " times emptied." +
                        "\r\n" +
                        "Right bin: " + bin2.getTimesEmptied() + " times emptied." +
                        "\r\n" +
                        "Capacity left in Left bin: " + bin1.getBinCapacityLeft() + "/" + bin1.getBinCapacityHeight() +
                        "\r\n" +
                        "Capacity left in Right bin: " + bin2.getBinCapacityLeft() + "/" + bin2.getBinCapacityHeight() +
                        "\r\n" +
                        "Time to simulate: " + difference + " Nano Seconds");
            }
        });

        test.start();

    }

    public void stopAlgo(){
        stop = true;
    }

    public void replaceBin(Bin bin){
       bin.emptyBin();
    }
}
