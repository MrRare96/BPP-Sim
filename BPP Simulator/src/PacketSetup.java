import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.activation.ActivationID;
import java.util.ArrayList;

/**
 * Created by Eldin on 4/22/2015.
 */
public class PacketSetup extends JDialog implements ActionListener{

    private JPanel packetSetup, top1, top2, mid, bot, top15, top25;
    private JLabel addPacketL, packetCapacityL, packetAmountL, genRandomPacketsL, orderL;
    private JTextArea orderOutput;
    private JTextField packetCapIn, amountOfPacketsIn;
    private JButton save, clear, generate, add;
    private JFrame parent;

    private ArrayList<Packet> order;

    public PacketSetup(JFrame parent, ArrayList<Packet> order){
        super(parent, "Bin Setup");
        this.parent = parent;
        this.order = order;

        packetSetup = new JPanel();
        packetSetup.setLayout(new GridLayout(5, 0, 0, 0));

        top1 = new JPanel();
        top1.setLayout(new GridLayout(2, 0, 0, 0));
        top15 = new JPanel();
        top15.setLayout(new FlowLayout());
        top2 = new JPanel();
        top2.setLayout(new GridLayout(2, 0, 0, 0));
        top25 = new JPanel();
        top25.setLayout(new FlowLayout());
        mid = new JPanel();
        mid.setLayout(new GridLayout(2, 0, 0, 0));
        bot = new JPanel();
        bot.setLayout(new FlowLayout());

        addPacketL = new JLabel("Add Packet:");
        packetCapacityL = new JLabel("Packet Capacity:");
        packetAmountL = new JLabel("Amount of Packets:");
        genRandomPacketsL = new JLabel("Generate Packets:");
        orderL = new JLabel("Order:");

        add = new JButton("Add");
        add.addActionListener(this);
        generate = new JButton("Generate");
        generate.addActionListener(this);
        clear = new JButton("clear");
        clear.addActionListener(this);
        save = new JButton("save");
        save.addActionListener(this);

        orderOutput = new JTextArea();
        orderOutput.setPreferredSize(new Dimension(500, 300));

        packetCapIn = new JTextField();
        packetCapIn.setColumns(15);
        amountOfPacketsIn = new JTextField();
        amountOfPacketsIn.setColumns(15);

        top1.add(addPacketL);
        top15.add(packetCapacityL);
        top15.add(packetCapIn);
        top15.add(add);
        top1.add(top15);

        top2.add(genRandomPacketsL);
        top25.add(packetAmountL);
        top25.add(amountOfPacketsIn);
        top25.add(generate);
        top2.add(top25);

        mid.add(orderL);
        mid.add(orderOutput);

        bot.add(clear);
        bot.add(save);

        packetSetup.add(top1);
        packetSetup.add(top2);
        packetSetup.add(mid);
        packetSetup.add(bot);

        getContentPane().add(packetSetup);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

    }
}
