import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eldin on 4/22/2015.
 * in this class you can create/generate packets
 */
public class PacketGenerator extends JDialog implements ActionListener{

    private JPanel packetSetup, top1, top2, mid, bot;
    private JLabel addPacketL, orderL, genRandomPacketsL;
    private JTextArea orderOutput;
    private JTextField packetCapIn, amountOfPacketsIn, maxCapIn;
    private JScrollPane scroll;
    private JButton ok, clear, generate, add;
    private JFrame parent;
    private ArrayList<Packet> order;
    private ArrayList<Bin> bins;
    private int x = 0;

    public PacketGenerator(JFrame parent, ArrayList<Packet> order, ArrayList<Bin> bins){
        /**
         * in here the screen will be created.
         */

        super(parent, "Packet Generator");
        this.parent = parent;
        this.order = order;
        this.bins = bins;

        packetSetup = new JPanel();
        packetSetup.setLayout(new BoxLayout(packetSetup, BoxLayout.PAGE_AXIS));

        top1 = new JPanel();
        top1.setLayout(new FlowLayout());
        top2 = new JPanel();
        top2.setLayout(new FlowLayout());
        mid = new JPanel();
        mid.setLayout(new FlowLayout());
        bot = new JPanel();
        bot.setLayout(new FlowLayout());

        addPacketL = new JLabel("Add Packet:");
        genRandomPacketsL = new JLabel("Generate Packets:");
        orderL = new JLabel("Order:");

        add = new JButton("Add");
        add.addActionListener(this);
        generate = new JButton("Generate");
        generate.addActionListener(this);
        clear = new JButton("clear");
        clear.addActionListener(this);
        ok = new JButton("ok");
        ok.addActionListener(this);

        orderOutput = new JTextArea();
        orderOutput.setEditable(false);
        scroll = new JScrollPane(orderOutput);
        scroll.setPreferredSize(new Dimension(400, 240));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        if(order.size() > 0){

            for(Packet pack : order){
                orderOutput.append("Packet #" + x + " with capacity:  " + pack.getPacketHeight() + " in order!" + "\r\n");
                x++;
            }
        }

        packetCapIn = new JTextField();
        packetCapIn.setColumns(15);
        packetCapIn.setText("Packet capacity");
        packetCapIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    Integer.parseInt(packetCapIn.getText());
                } catch (Exception ex) {
                    super.mouseClicked(e);
                    packetCapIn.setText("");
                    repaint();
                    revalidate();
                }
            }
        });

        amountOfPacketsIn = new JTextField();
        amountOfPacketsIn.setColumns(15);
        amountOfPacketsIn.setText("Amount of packets");
        amountOfPacketsIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    Integer.parseInt(amountOfPacketsIn.getText());
                } catch (Exception ex) {
                    super.mouseClicked(e);
                    amountOfPacketsIn.setText("");
                    repaint();
                    revalidate();
                }

            }
        });
        maxCapIn = new JTextField();
        maxCapIn.setColumns(8);
        maxCapIn.setText("Max capacity");
        maxCapIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    Integer.parseInt(maxCapIn.getText());
                } catch (Exception ex) {
                    super.mouseClicked(e);
                    maxCapIn.setText("");
                    repaint();
                    revalidate();
                }

            }
        });
        

        top1.add(addPacketL);
        top1.add(packetCapIn);
        top1.add(add);

        top2.add(genRandomPacketsL);
        top2.add(amountOfPacketsIn);
        top2.add(maxCapIn);
        top2.add(generate);

        mid.add(orderL);
        mid.add(scroll);

        bot.add(ok);
        bot.add(clear);

        packetSetup.add(top1, BorderLayout.NORTH);
        packetSetup.add(top2, BorderLayout.NORTH);
        packetSetup.add(mid, BorderLayout.NORTH);
        packetSetup.add(bot, BorderLayout.NORTH);

        getContentPane().add(packetSetup);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == add){
            try{
                x++;
                int capacity = Integer.parseInt(packetCapIn.getText());
                Packet pack = new Packet(capacity);
                order.add(pack);
                orderOutput.append("Packet #" + x + " with capacity:  " + capacity + " added!" + "\r\n");

            } catch (Exception ex){
                JOptionPane.showMessageDialog(parent, "You need to fill in a number!");
            }
        } else if(e.getSource() == generate){
            try{
                order.clear();
                int y = 0;
                int rounds = Integer.parseInt(amountOfPacketsIn.getText());
                int maxCap = Integer.parseInt(maxCapIn.getText());
                if(maxCap <= bins.get(0).getBinCapacityHeight() || maxCap <= bins.get(1).getBinCapacityHeight()){
                    while(y != rounds){
                        Random rand = new Random();
                        int capacity = rand.nextInt(maxCap - 1) + 1;
                        Packet pack = new Packet(capacity);
                        order.add(pack);
                        orderOutput.append("Packet #" + x + " with capacity:  " + capacity + " added!"  + "\r\n");
                        y++;
                        x++;
                    }
                } else {
                    JOptionPane.showMessageDialog(parent, "A packet cannot be bigger then the max capacity of the bins!");
                }

            } catch(Exception ex){
                JOptionPane.showMessageDialog(parent, "You need to fill in a number!");
            }
        } else if(e.getSource() == clear){
            order.clear();
            orderOutput.setText("");
            x = 0;
            validate();
            repaint();
        } else if(e.getSource() == ok){
            setVisible(false);
            dispose();
        }

    }
}
