
/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * This class is where everything starts running
 */
public class Main {
    
    private static int binHeight = 400;
    private static int binWidth = 250;
    private static int binHspacing = 25;
    private static int binWspacing = 25;

    public static void main(String[] args) {
        /**
         * This methode runs everything, creating and showing the window, creating bins, creating packets and putting them into
         * the bins, and drawing the bins containing those packets on the main screen.
         */

        //create screen and drawer
        MainScreen screen = new MainScreen();
        Drawer draw = new Drawer();

        Bin bin = new Bin(25, 25, 250, 400, 25);
        Bin bin2 = new Bin(300, 25, 250, 400, 25);
        Packet packet = new Packet(5, 6);
        Packet packet2 = new Packet(6, 10);

        bin.addPacket(packet);
        bin2.addPacket(packet2);

        draw.drawingShapes(bin.getBinVisuals());
        draw.drawingShapes(bin2.getBinVisuals());

        screen.addToScreen(draw);


    }
}
