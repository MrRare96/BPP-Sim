
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
/**
 * Created by Ewart en Eldin on 4/17/2015.
 * This class creates a binsetup window containing options for setting the capicity for the left and right bin.
 */
public class setup extends JDialog implements ActionListener{

    private JPanel binSetup, top, mid, lowMid, bot;
    private JLabel binCap1Label, binCap2Label, delayL;
    private JTextField binCapacity1, binCapacity2;
    private JButton save, cancel;
    private MainScreen parent;
    private JTextField delayInput;


    private int KonamiCode[] = {38,38,40,40,37,39,37,39,66,65 }, count;
    private int binCap1, binCap2, delay;

    private ArrayList<Bin> binarray;
    private ArrayList<Drawer> drawers;

    public setup(MainScreen parent, ArrayList<Bin> binarray, ArrayList<Drawer> drawers){
        super(parent, "Setup");
        this.parent = parent;
        this.binarray = binarray;
        this.drawers = drawers;
        this.count = 0;
        setResizable(false);

        binSetup = new JPanel();
        binSetup.setLayout(new GridLayout(4, 0));

        top = new JPanel();
        top.setLayout(new FlowLayout());
        mid = new JPanel();
        mid.setLayout(new FlowLayout());
        lowMid = new JPanel();
//        lowMid.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        lowMid.setLayout(new FlowLayout());
        bot = new JPanel();
        bot.setLayout(new FlowLayout());



        binCap1Label = new JLabel("Left bin capacity:");
        binCap2Label = new JLabel("Right bin capacity:");

        binCapacity1 = new JTextField("" + binarray.get(0).getBinCapacityHeight());
        binCapacity1.setColumns(5);

        binCapacity2 = new JTextField("" + binarray.get(1).getBinCapacityHeight());
        binCapacity2.setColumns(5);


        delayL = new JLabel("Set delay in ms:");

        delayInput = new JTextField(parent.getDelay());
        delayInput.setColumns(5);
        delayInput.setText(parent.getDelay().toString());
        delayInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                delayInput.setText("");
                repaint();
                revalidate();
            }
        });
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        save = new JButton("Save");
        save.addActionListener(this);

        top.add(binCap1Label);
        top.add(binCapacity1);

        mid.add(binCap2Label);
        mid.add(binCapacity2);

        lowMid.add(delayL);
        lowMid.add(delayInput);
        bot.add(save);
        bot.add(cancel);

        binSetup.add(top);
        binSetup.add(mid);
        binSetup.add(lowMid);
        binSetup.add(bot);

        getContentPane().add(binSetup);
        pack();
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel){
            setVisible(false);
            dispose();
        } else if(e.getSource() == save) {
            String input1 = binCapacity1.getText();
            String input2 = binCapacity2.getText();
            String input3 = delayInput.getText();
            try{
                binCap1 = Integer.parseInt(input1);
                binCap2 = Integer.parseInt(input2);
                delay = Integer.parseInt(input3);
                if(binCap1 != binarray.get(0).getBinCapacityHeight() || binCap2 != binarray.get(1).getBinCapacityHeight()) {
                    if (binCap1 < 5 || binCap2 < 5) {
                        JOptionPane.showMessageDialog(parent, "Number needs to be higher then 5");
                    } else {
                        int x = 0;

                        for (Bin bin : binarray) {
                            if ((x % 2) == 0) {
                                bin.setBinCapacityHeight(binCap1);
                            } else {
                                bin.setBinCapacityHeight(binCap2);
                            }
                            x++;
                        }

                        for (Drawer draw : drawers) {
                            draw.repaint();
                        }

                    }
                }
                if(delay != parent.getDelay()) {
                        if(delay < 50 && delay != 0) this.delay = 0;
                        parent.setDelay(delay);

                }
                setVisible(false);
                dispose();
            } catch (Exception ex){
                JOptionPane.showMessageDialog(parent, "You need to fill in a number!");
            }



        }
    }
}
