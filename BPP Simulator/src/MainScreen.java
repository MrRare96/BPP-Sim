import javax.swing.*;
import java.awt.*;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * In this class the mainScreen will be created
 */
public class MainScreen extends JFrame {

    private JFrame mainScreen;
    private JPanel topButtons, bins, parent;


    public MainScreen(){
        mainScreen = new JFrame("BPP Simulator");
        mainScreen.setSize(1024, 768);
        mainScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainScreen.setVisible(true);

        topButtons = new JPanel();
        topButtons.setPreferredSize(new Dimension(50, 50));
        bins = new JPanel();
        bins.setPreferredSize(new Dimension(600,600));
        parent = new JPanel();
        parent.setLayout(new GridLayout(3, 0));
        parent.setPreferredSize(new Dimension(700, 700));

        topButtons.add(new JButton("test"));
        parent.add(topButtons);
       //remove parent to change it to old setup
    }

    public void addToScreen(Drawer draw){
         //you can't put it directly in the JFrame because we also need buttons etc. in here.

        bins.add(draw);
        parent.add(bins);
        parent.revalidate();
        mainScreen.add(parent);


    }

}
