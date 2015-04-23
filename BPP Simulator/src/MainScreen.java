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
    private JTextArea output;
    private JScrollPane scroll;
    private ArrayList<Bin> binarray;
    private ArrayList<Drawer> drawers = new ArrayList<Drawer>();
    private ArrayList<Packet> order = new ArrayList<Packet>();

    private SimpelGretig simpel;

    public MainScreen(ArrayList<Bin> binarray, ArrayList<Packet> order){
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

        output = new JTextArea();
        output.setEditable(false);

        scroll = new JScrollPane(output);
        scroll.setPreferredSize(new Dimension(600, 240));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        bottom.add(scroll);

        container.add(top);
        container.add(mid);
        container.add(bottom);
        mainScreen.add(container);

        mainScreen.setVisible(true);

    }

    public void addToResult(String input){
        output.append(input);
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
                simpel.startAlgo();
            } else {
                JOptionPane.showMessageDialog(mainScreen, "You need to setup the order first!");
            }
        } else if(e.getSource() == stop){
            simpel.stopAlgo();
        } else if(e.getSource() == clear){
            output.setText("");
            revalidate();
            repaint();
        }
    }
}
