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

    private MainScreen parentscreen;
    private Drawer draw;

    private boolean stop = false;

    private Bin binL, binR;

    public EnumeratieVE(Bin bin1, Bin bin2, MainScreen parentscreen, Drawer draw, int screen) {

        tempOrder = new ArrayList<Packet>();

        packetsUsed = new ArrayList<Packet>();

        packetsUsed1 = new ArrayList<Packet>();

        bestCombination = new ArrayList<ArrayList>();

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

        int target = 10;

        System.out.println("DERP ENUM STARteD");

        start:
        for(Packet pack1 : tempOrder){

            System.out.println("Derp loop 1 started");

            binCap = pack1.getPacketHeight();
            startSum = binCap + startSum;
            firstLoopCounter++;

            if(!packetsUsed.isEmpty()){
                packetsUsed.clear();
            }

            packetsUsed.add(pack1);

            int adder = 0;

            for(Packet pack2 : tempOrder){

                binCap = pack2.getPacketHeight();

                if(firstLoopCounter < secondLoopCounter){
                    adder = adder + binCap;
                    System.out.println("")
                    packetsUsed.add(pack2);

                    if(target == (adder + startSum)){
                        bestCombination.add(packetsUsed);
                        System.out.println("TARGet OFUSDFd");
                        break start;
                    }

                }
                secondLoopCounter++;
            }


        }

        for(ArrayList<Packet> packetsUsedinBC : bestCombination){
            for(Packet packinbc: packetsUsedinBC){
                System.out.println(packinbc.getPacketHeight());
            }
        }

        return true;
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
                enumeratie();
            }
        });

        runEnumeratie.start();


    }

    public void stopAlgo(){
        stop = true;
    }

}
