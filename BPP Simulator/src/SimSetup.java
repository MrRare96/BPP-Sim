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
    private JFrame parent;

    private JLabel delayL;

    public SimSetup(JFrame parent){
        super(parent, "Bin Setup");
        this.parent = parent;

        setResizable(false);

        simSetup = new JPanel();
        simSetup.setLayout(new GridLayout(3, 0));

        top = new JPanel();
        top.setLayout(new FlowLayout());
        bot = new JPanel();
        bot.setLayout(new FlowLayout());

        delayL = new JLabel("Set delay(min: 50):");

        delayInput = new JTextField(parent.getD);
        delayInput.setColumns(8);
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
            parent.
        }


    }
}
