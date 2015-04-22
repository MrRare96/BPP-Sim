import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Eldin on 4/17/2015.
 */
public class BinSetup extends JDialog implements ActionListener, KeyListener{

    private JPanel binSetup, top, mid, bot;
    private JLabel binCap1Label, binCap2Label;
    private JTextField binCapacity1, binCapacity2;
    private JButton save, cancel;
    private JFrame parent;

    private int KonamiCode[] = {38,38,40,40,37,39,37,39,66,65 }, count;
    private int binCap1, binCap2;

    private ArrayList<Bin> binarray;
    private ArrayList<Drawer> drawers;

    public BinSetup(JFrame parent, ArrayList<Bin> binarray, ArrayList<Drawer> drawers){
        super(parent, "Bin Setup");
        this.parent = parent;
        this.binarray = binarray;
        this.drawers = drawers;
        this.count = 0;
        setResizable(false);

        binSetup = new JPanel();
        binSetup.setLayout(new GridLayout(3, 0));

        top = new JPanel();
        top.setLayout(new FlowLayout());
        mid = new JPanel();
        mid.setLayout(new FlowLayout());
        bot = new JPanel();
        bot.setLayout(new FlowLayout());

        binCap1Label = new JLabel("Set capacity bin1");
        binCap2Label = new JLabel("Set capacity bin2");

        binCapacity1 = new JTextField("" + binarray.get(0).getBinCapacityHeight());
        binCapacity1.setColumns(15);

        binCapacity1.addKeyListener(this);
        binCapacity2 = new JTextField("" + binarray.get(1).getBinCapacityHeight());
        binCapacity2.setColumns(15);

        binCapacity2.addKeyListener(this);

        cancel = new JButton("cancel");
        cancel.addActionListener(this);
        save = new JButton("save");
        save.addActionListener(this);

        top.add(binCap1Label);
        top.add(binCapacity1);

        mid.add(binCap2Label);
        mid.add(binCapacity2);

        bot.add(cancel);
        bot.add(save);

        binSetup.add(top);
        binSetup.add(mid);
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


            try{
                binCap1 = Integer.parseInt(input1);
                binCap2 = Integer.parseInt(input2);
                if(binCap1 < 5 || binCap2 < 5){
                    JOptionPane.showMessageDialog(parent, "You need to fill in a number!");
                } else {
                    int x = 0;

                    for(Bin bin : binarray){
                        if((x%2) == 0){
                            bin.setBinCapacityHeight(binCap1);
                        } else {
                            bin.setBinCapacityHeight(binCap2);
                        }
                        x++;
                    }


                    for(Drawer draw : drawers){
                        draw.repaint();
                    }

                    int check = JOptionPane.showConfirmDialog(parent, "Succesfully changed the capacity of the bins!", "Succes!", JOptionPane.DEFAULT_OPTION);

                    if(check == JOptionPane.OK_OPTION){

                        setVisible(false);
                        dispose();
                    }
                }

            } catch (Exception ex){
                JOptionPane.showMessageDialog(parent, "You need to fill in a number!");
            }



        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key  =  e.getKeyCode();
        System.out.println(key);
        if(key == KonamiCode[count]) {
            if(count < 9 ) {
                count++;
            } else {
                //add action
                for(Drawer d : drawers ) {
                    d.setKonami();
                    d.repaint();
                }
            }
        } else{
            count = 0;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
