import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * In this class the mainScreen will be created
 */
public class MainScreen extends JFrame implements ActionListener {

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
    private Enumeratie enumeratie;

    private int delay = 50;


        /**
         * this class contains all the atributes needed to create the main screen
         */

    public MainScreen(ArrayList<Drawer> drawers, ArrayList<Bin> bins, ArrayList<Packet> order){
        this.drawers = drawers;
        this.bins = bins;
        this.order = order;

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

        gretigOutput = new JTextArea();
        gretigOutput.setEditable(false);

        enumeratieOutput = new JTextArea();
        enumeratieOutput.setEditable(false);

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
        String input = "\r\n" + "----------------RESULT FROM SIMPEL GRETIG--------------------" +
                "\r\n" +
                "Left bin: " + left.getTimesEmptied() + " times emptied." +
                "\r\n" +
                "Right bin: " + right.getTimesEmptied() + " times emptied." +
                "\r\n" +
                "Capacity left in Left bin: " + left.getBinCapacityLeft() + "/" + left.getBinCapacityHeight() +
                "\r\n" +
                "Capacity left in Right bin: " + right.getBinCapacityLeft() + "/" + left.getBinCapacityHeight() +
                "\r\n" +
                "Time to simulate: " + difference + " Nano Seconds";
        System.out.println(" output: " + outputNumber);
        if(outputNumber == 1) {
            simpelOutput.append(input);
        } else if (outputNumber == 2) {
            gretigOutput.append(input);
        } else if (outputNumber == 3){
            enumeratieOutput.append(input);
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

    public int getDelay() {
        return delay;
    }

    public void addEnumeratie(Enumeratie enumeratie){
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
            SimSetup setup = new SimSetup(mainScreen);
        } else if(e.getSource() == stop){
            start.setEnabled(true);
            stop.setEnabled(false);
            simpel.stopAlgo();
        } else if(e.getSource() == clear){
            simpelOutput.setText("");
            gretigOutput.setText("");
            enumeratieOutput.setText("");

            for(Bin bin : bins) {
                bin.emptyBin();
                bin.setTimesEmptied(0);
            }
            for(Drawer draw : drawers) {
                draw.repaint();
            }
            start.setEnabled(true);
            stop.setEnabled(false);
            revalidate();
            repaint();
        }
    }
}
