import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Eldin on 4/25/2015.
 */
public class EnumeratieVE implements Algoritme{

    private ArrayList<Packet> order = new ArrayList<Packet>();
    private ArrayList<Packet> packetsUsed;
    private ArrayList<Packet> packetsUsed1;

    private ArrayList<Packet> tempOrder;
    private ArrayList<ArrayList> bestCombination;

    private ArrayList<Packet> used;

    private MainScreen parentscreen;
    private Drawer draw;

    private boolean stop = false;

    private Bin binL, binR;

    public EnumeratieVE(Bin bin1, Bin bin2, MainScreen parentscreen, Drawer draw, int screen) {

        tempOrder = new ArrayList<Packet>();

        packetsUsed = new ArrayList<Packet>();

        packetsUsed1 = new ArrayList<Packet>();

        bestCombination = new ArrayList<ArrayList>();

        used = new ArrayList<Packet>();

        this.parentscreen = parentscreen;
        this.draw = draw;

        binL = bin1;
        binR = bin2;

    }

    public boolean enumeratie(){

        int binCap;
        int startSum = 0;

        int firstLoopCounter = 0;
        int secondLoopCounter = 0;

        int target = binL.getBinCapacityHeight();

        boolean found = false;

        System.out.println("DERP ENUM STARteD");

        int x = 0;
        while(x != tempOrder.size()) {

            bestCombination.clear();
            packetsUsed.clear();

            start:
            for (Packet pack1 : tempOrder) {

                found = false;

                System.out.println("Derp loop 1 started");

                binCap = pack1.getPacketHeight();
                startSum = binCap + startSum;
                firstLoopCounter++;

                if (!packetsUsed.isEmpty()) {
                    packetsUsed.clear();
                }

                packetsUsed.add(pack1);

                int adder = 0;

                for (Packet pack2 : tempOrder) {

                    binCap = pack2.getPacketHeight();

                    if (firstLoopCounter <= secondLoopCounter) {
                        adder = adder + binCap;
                        System.out.println("Start: " + startSum);
                        System.out.println(adder + startSum);
                        packetsUsed.add(pack2);

                        if (target == (adder + startSum)) {
                            bestCombination.add(packetsUsed);
                            System.out.println("TARGet OFUSDFd");
                            found = true;
                            break start;
                        } else {
                            found = false;
                        }

                    }
                    secondLoopCounter++;
                    adder = 0;
                }

            }

            if(!found){
                target = target - 1;
                System.out.println("target one down");
                if(target <= 0){
                    break;
                }
            } else {
                break;
            }
        }

        for (ArrayList<Packet> packetsUsedinBC : bestCombination) {
            for (Packet packinbc : packetsUsedinBC) {
                System.out.println("RESULT: " + packinbc.getPacketHeight());
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

                for(Packet pInOrder : order){
                    tempOrder.add(pInOrder);
                }


                int x = 0;
                boolean found = enumeratie();

                long lStartTime = System.nanoTime();
                for(Packet pack : order){
                    if(stop){
                        break;
                    }
                    if(found){
                        for(ArrayList<Packet> packetsUsedinBC : bestCombination){
                            if(packetsUsedinBC.contains(pack)){
                                binL.addPacket(pack);
                            } else {
                                binR.addPacket(pack);
                            }
                        }

                        if(bestCombination.get(bestCombination.size() - 1).size() == x){
                            bestCombination.clear();
                            x = 0;
                            found = enumeratie();
                        }
                    } else {
                        System.out.println("enumeration did not find anything");
                        break;
                    }

                    if(parentscreen.getDelay() >= 50){
                        parentscreen.delay();
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                draw.repaint();
                            }
                        });
                    }
                    tempOrder.remove(pack);
                    x++;
                }

                if(parentscreen.getDelay() == 0){
                    draw.repaint();
                }

                long lEndTime = System.nanoTime();
                long difference = lEndTime - lStartTime;

                parentscreen.addToResult(outputNumber, binL, binR, difference);


            }
        });

        runEnumeratie.start();

        stop = false;
    }

    public void stopAlgo(){
        stop = true;
    }

}
