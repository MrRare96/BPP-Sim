import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * In this class the mainScreen will be created
 */
public class MainScreen extends JFrame implements ActionListener, KeyListener {

    private JFrame mainScreen;
    private JPanel container, top, mid, bottom;
    private JButton start, stop, clear, binSetup, packetSetup, simSetup;
    private JTextArea simpelOutput, gretigOutput, enumeratieOutput;
    private JScrollPane simpelScroll, gretigScroll, enumiratieScroll;
    private ArrayList<Bin> bins;
    private ArrayList<Drawer> drawers = new ArrayList<Drawer>();
    private ArrayList<Packet> order = new ArrayList<Packet>();
    private SimpelGretig simpel;
    private Gretig gretig;
    private EnumeratieVE enumeratie;
    private int peterOrder[] = {80,69,84,69,82 };
    private int peterPackages[] = {3,2,3,4,5,2,3,1,2,4,5,2,3,2,4,5,6,5,4,3,2,1,2,3,4,4,3,2,4,5,4,4,1,2,3,5,4,4,3,5,4,3,4,2,3,4,5,6,1,4,1,3,6,6,4,5,3,2,3};
    private int henkOrder[] = {72,69,78,75};
    private int henkPackages[] ={9,3,4,6,4,2,1};
    private int KonamiCode[] = {38,38,40,40,37,39,37,39,66,65}, count;

    public void setKonamiCode(boolean konamiCode) {
        this.konamiCode = konamiCode;
    }

    public boolean isKonamiCode() {

        return konamiCode;
    }

    private Integer delay = 50;
    private boolean konamiCode;

        /**
         * this class contains all the atributes needed to create the main screen
         */

    public MainScreen(ArrayList<Drawer> drawers, ArrayList<Bin> bins, ArrayList<Packet> order){
        this.drawers = drawers;
        this.bins = bins;
        this.order = order;
        this.konamiCode = false;
        mainScreen = new JFrame("BPP Simulator");
        mainScreen.setSize(1300, 800);
        mainScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        top = new JPanel();
        mid = new JPanel();
        bottom = new JPanel();

        start = new JButton("start");
        start.addActionListener(this);

        stop = new JButton("stop");
        stop.setEnabled(false);
        stop.addActionListener(this);

        binSetup = new JButton("Bin Setup");
        binSetup.addActionListener(this);

        packetSetup = new JButton("Packet Setup");
        packetSetup.addActionListener(this);

        simSetup = new JButton("Sim Setup");
        simSetup.addActionListener(this);

        clear = new JButton("Clear");
        clear.addActionListener(this);

        top.add(clear);
        top.add(start);
        top.add(stop);
        top.add(binSetup);
        top.add(packetSetup);
        top.add(simSetup);

        simpelOutput = new JTextArea();
        simpelOutput.setEditable(false);
        simpelOutput.addKeyListener(this);

        gretigOutput = new JTextArea();
        gretigOutput.setEditable(false);
        gretigOutput.addKeyListener(this);

        enumeratieOutput = new JTextArea();
        enumeratieOutput.setEditable(false);
        enumeratieOutput.addKeyListener(this);

        simpelScroll = new JScrollPane(simpelOutput);
        simpelScroll.setPreferredSize(new Dimension(400, 300));
        simpelScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        gretigScroll = new JScrollPane(gretigOutput);
        gretigScroll.setPreferredSize(new Dimension(400, 300));
        gretigScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        enumiratieScroll = new JScrollPane(enumeratieOutput);
        enumiratieScroll.setPreferredSize(new Dimension(400, 300));
        enumiratieScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        bottom.add(simpelScroll);
        bottom.add(gretigScroll);
        bottom.add(enumiratieScroll);

        container.add(top);
        container.add(mid);
        container.add(bottom);
        mainScreen.add(container);

        mainScreen.setVisible(true);


    }

    public void addToResult(int outputNumber, Bin left, Bin right, long difference){
        String input = "\r\n" +
                "Left bin: " + left.getTimesEmptied() + " times emptied." +
                "\r\n" +
                "Right bin: " + right.getTimesEmptied() + " times emptied." +
                "\r\n" +
                "TOTAL: " + (right.getTimesEmptied()+ left.getTimesEmptied()) + " times emptied." +
                "\r\n" +
                "Capacity left in Left bin: " + left.getBinCapacityLeft() + "/" + left.getBinCapacityHeight() +
                "\r\n" +
                "Capacity left in Right bin: " + right.getBinCapacityLeft() + "/" + left.getBinCapacityHeight() +
                "\r\n" +
                "Time to simulate: " + difference + " Nano Seconds";
        System.out.println(" output: " + outputNumber);
        if(outputNumber == 1) {
            simpelOutput.append("\r\n" + "----------------SIMPEL GRETIG--------------------" + input);
        } else if (outputNumber == 2) {
            gretigOutput.append("\r\n" + "----------------GRETIG--------------------" + input);
        } else if (outputNumber == 3){
            enumeratieOutput.append("\r\n" + "----------------ENUMERATIE--------------------" + input);
        }
        start.setEnabled(true);
        stop.setEnabled(false);
    }


    public void addToScreen(Drawer draw){
        /**
         * this method adds the drawer to the main screen(see Drawer.class)
         */
        mid.add(draw);
        drawers.add(draw);
    }

    public void addSimpelGretig(SimpelGretig simpel){
        this.simpel = simpel;
    }
    public void addGretig(Gretig gretig){
        this.gretig = gretig;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Integer getDelay() {
        return delay;
    }

    public void addEnumeratie(EnumeratieVE enumeratie){
        this.enumeratie = enumeratie;
    }

    public void delay() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == binSetup){
            BinSetup setup = new BinSetup(mainScreen, bins, drawers);
        } else if(e.getSource() == packetSetup){
            PacketSetup psetup = new PacketSetup(mainScreen, order, bins);
        } else if(e.getSource() == start) {
            if (order.size() > 0) {
                start.setEnabled(false);
                stop.setEnabled(true);
                simpel.startAlgo(1);
                gretig.startAlgo(2);
                enumeratie.startAlgo(3);

            } else {
                JOptionPane.showMessageDialog(mainScreen, "You need to setup the order first!");
            }
        } else if(e.getSource() == simSetup){
            SimSetup setup = new SimSetup(this);
        } else if(e.getSource() == stop){
            start.setEnabled(true);
            stop.setEnabled(false);
            simpel.stopAlgo();
            gretig.stopAlgo();
            enumeratie.stopAlgo();
        } else if(e.getSource() == clear){
            simpelOutput.setText("");
            gretigOutput.setText("");
            enumeratieOutput.setText("");
            simpel.stopAlgo();
            gretig.stopAlgo();
            for(Bin bin : bins) {
                bin.emptyBin();
                bin.setTimesEmptied(0);
            }
            for(Drawer draw : drawers) {
                draw.repaint();
            }
            simpel.stopAlgo();
            gretig.stopAlgo();
            start.setEnabled(true);
            stop.setEnabled(false);
            revalidate();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.print((char)key + "|");
        System.out.print(key + "|");
        if(key == KonamiCode[count]) {
//            System.out.print((char)key + "|");
            if(count < 9 ) {
                count++;
            } else {
                count = 0;
                //add action
                System.out.println("konami Activated");
                for(final Drawer d : drawers ) {

                    d.setKonami();
                    java.util.Timer konamiTimer = new java.util.Timer();
                    konamiTimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            d.repaint();
                        }
                    }, 50, 50);
                }
            }
        } else if(key == peterOrder[count]) {
//            System.out.print((char)key + "|");
            if(count < peterOrder.length - 1 ) {
                count++;
            } else {
                count = 0;
                //add action
                System.out.println("Peter Activated");

                order.clear();
                for(int x = 0; x < peterPackages.length; x++) {
                    order.add(new Packet(peterPackages[x]));
                }
            }
        } else if(key == henkOrder[count]) {
//            System.out.print((char)key + "|");
            if(count < henkOrder.length - 1 ) {
                count++;
            } else {
                count = 0;
                //add action
                System.out.println("Henk Activated");

                order.clear();
                for(int x = 0; x < henkPackages.length; x++) {
                    order.add(new Packet(henkPackages[x]));
                }
            }
        } else{
            System.out.print("\n");
            count = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
