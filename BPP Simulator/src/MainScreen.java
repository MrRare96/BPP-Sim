import javax.swing.*;
import java.awt.*;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * In this class the mainScreen will be created
 */
public class MainScreen extends JFrame {

    private JFrame mainScreen;
    private JPanel topButtons, anddereDingen, gretig, simpelGretig, enummeratie;


    public MainScreen(){
        mainScreen = new JFrame("BPP Simulator");
        mainScreen.setSize(1024, 768);
        mainScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainScreen.setVisible(true);


    }

    public void addToScreen(Drawer draw){
        mainScreen.add(draw);
    }

}
