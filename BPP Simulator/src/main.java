
/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * This class is where everything starts running
 */
public class main {
    
    private static int binHeight = 600;
    private static int binWidth = 450;
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

        //create bins with: x, y, width, height, ContentHeight
        Bin bin1 = new Bin(binWspacing, binHspacing, binWidth, binHeight, 25);
        Bin bin2 = new Bin(((binWspacing * 2) + binWidth), binHspacing, binWidth, binHeight, 25);
        Bin bin3 = new Bin(((binWspacing * 3) + binWidth * 2), binHspacing, binWidth, binHeight, 25);
        Bin bin4 = new Bin(((binWspacing * 4) + binWidth * 3), binHspacing, binWidth, binHeight, 25);
        Bin bin5 = new Bin(((binWspacing * 5) + binWidth * 4), binHspacing, binWidth, binHeight, 25);
        Bin bin6 = new Bin(((binWspacing * 6) + binWidth * 5), binHspacing, binWidth, binHeight, 25);

        //create packets with: packetID, ContentHeight
        Packet packet1 = new Packet(1, 4);
        Packet packet2 = new Packet(2, 6);
        Packet packet3 = new Packet(3, 8);
        Packet packet4 = new Packet(4, 10);
        bin1.addPacket(packet1);
        bin1.addPacket(packet2);
        bin1.addPacket(packet4);

        bin2.addPacket(packet1);
        bin2.addPacket(packet3);

        //retrieve all the dimensions for the to be drawn bins and packages
        draw.drawingShapes(bin1.getBinVisuals());
        draw.drawingShapes(bin2.getBinVisuals());
        draw.drawingShapes(bin3.getBinVisuals());
        draw.drawingShapes(bin4.getBinVisuals());
        draw.drawingShapes(bin5.getBinVisuals());
        draw.drawingShapes(bin6.getBinVisuals());

        screen.addToScreen(draw);

    }
}
