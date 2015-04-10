import javax.swing.*;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * In this class the mainscreen will be created
 */
public class MainScreen extends JFrame {

    private JFrame mainscreen;

    public MainScreen(){
        mainscreen = new JFrame("BPP Simulator");
        mainscreen.setSize(1024, 768);
        mainscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainscreen.setVisible(true);
    }

    public void addToScreen(Drawer draw){
        mainscreen.add(draw);
    }

}
