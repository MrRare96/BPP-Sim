import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Eldin on 4/24/2015.
 */
public class SimSetup extends JDialog implements ActionListener{
    private JPanel simSetup, top, bot;
    private JButton ok, cancel;

    private JTextField delayInput;
    private MainScreen parent;

    private JLabel delayL;

    public SimSetup(MainScreen parent){
        super(parent, "Bin Setup");
        this.parent = parent;

        setResizable(false);

        simSetup = new JPanel();
        simSetup.setLayout(new GridLayout(3, 0));

        top = new JPanel();
        top.setLayout(new FlowLayout());
        bot = new JPanel();
        bot.setLayout(new FlowLayout());

        delayL = new JLabel("Set delay in ms:");

        delayInput = new JTextField(parent.getDelay());
        delayInput.setColumns(8);
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


        cancel = new JButton("cancel");
        cancel.addActionListener(this);
        ok = new JButton("ok");
        ok.addActionListener(this);

        top.add(delayL);
        top.add(delayInput);

        bot.add(ok);
        bot.add(cancel);

        simSetup.add(top);
        simSetup.add(bot);

        getContentPane().add(simSetup);
        pack();
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ok){
            try{

                Integer delay = Integer.parseInt(delayInput.getText());
                if(delay < 50 && delay != 0){
                    JOptionPane.showMessageDialog(parent, "Delay can only be set to 0 (direct result) or higher then 50 in ms!");
                } else {
                    if(delay == 0){
                        JOptionPane.showMessageDialog(parent, "Delay off, direct result!: ");

                    }
                    JOptionPane.showMessageDialog(parent, "Delay set to: " + delay + " ms");

                    parent.setDelay(delay);
                    setVisible(false);
                    dispose();
                }

            } catch(Exception yo){
                JOptionPane.showMessageDialog(parent, "You need to use a number, no string or special characters allowed!");
            }

        } else if(e.getSource() == cancel) {
            setVisible(false);
            dispose();
        }


    }
}
