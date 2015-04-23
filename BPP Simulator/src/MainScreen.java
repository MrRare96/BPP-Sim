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
    private JButton start, stop, clear, binSetup, packetSetup;
    private JTextArea simpelOutput, gretigOutput, enumeratieOutput;
    private JScrollPane simpelScroll, gretigScroll, enumiratieScroll;
    private ArrayList<Bin> binarray;
    private ArrayList<Drawer> drawers = new ArrayList<Drawer>();
    private ArrayList<Packet> order = new ArrayList<Packet>();

    private SimpelGretig simpel;

    public MainScreen(ArrayList<Drawer> drawers, ArrayList<Bin> binarray, ArrayList<Packet> order){
        this.drawers = drawers;
        this.binarray = binarray;
        this.order = order;


        mainScreen = new JFrame("BPP Simulator");
        mainScreen.setSize(1300, 768);
        mainScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        top = new JPanel();
        mid = new JPanel();
        bottom = new JPanel();

        start = new JButton("start");
        start.addActionListener(this);

        stop = new JButton("stop");
        stop.addActionListener(this);

        binSetup = new JButton("Bin Setup");
        binSetup.addActionListener(this);

        packetSetup = new JButton("Packet Setup");
        packetSetup.addActionListener(this);

        clear = new JButton("Clear");
        clear.addActionListener(this);

        top.add(clear);
        top.add(start);
        top.add(stop);
        top.add(binSetup);
        top.add(packetSetup);

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
        } else {
            enumeratieOutput.append(input);
        }
    }


    public void addToScreen(Drawer draw){
       mid.add(draw);
       mainScreen.setVisible(true);
       drawers.add(draw);
    }

    public void addSimpelGretig(SimpelGretig simpel){
        this.simpel = simpel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == binSetup){
            BinSetup setup = new BinSetup(mainScreen, binarray, drawers);
        } else if(e.getSource() == packetSetup){
            PacketSetup psetup = new PacketSetup(mainScreen, order);
        } else if(e.getSource() == start){
            if(order.size() > 0){
                simpel.startAlgo(1);

            } else {
                JOptionPane.showMessageDialog(mainScreen, "You need to setup the order first!");
            }
        } else if(e.getSource() == stop){
            simpel.stopAlgo();
        } else if(e.getSource() == clear){
            simpelOutput.setText("");
            gretigOutput.setText("");
            enumeratieOutput.setText("");

            revalidate();
            repaint();
        }
    }
}
