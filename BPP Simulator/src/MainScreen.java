import javax.swing.*;
import java.awt.*;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * In this class the mainScreen will be created
 */
public class MainScreen extends JFrame {

    private JFrame mainScreen;
    private JPanel topButtons, bins;

    public MainScreen(){
        mainScreen = new JFrame("BPP Simulator");
        mainScreen.setSize(1024, 768);
        mainScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainScreen.setVisible(true);
        JPanel topButtons = new JPanel();
        JPanel bins = new JPanel();
        this.bins = bins;
        JPanel parent = new JPanel();
        parent.setLayout(new BoxLayout(parent, BoxLayout.PAGE_AXIS));
        topButtons.add(new JButton("test"));
        parent.add(topButtons);
        parent.add(bins);
        mainScreen.add(parent); //remove parent to change it to old setup
    }

    public void addToScreen(Drawer draw){
        bins.add(draw); //you can't put it directly in the JFrame because we also need buttons etc. in here.
        //mainScreen.add(draw);
    }

}
