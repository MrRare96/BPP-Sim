
/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * This class is where everything starts running
 */
public class Main {
    
    private static int binHeight = 400;
    private static int binWidth = 150;
    private static int binHspacing = 25;
    private static int binWspacing = 25;

    public static void main(String[] args) {
        /**
         * This methode runs everything, creating and showing the window, creating bins, creating packets and putting them into
         * the bins, and drawing the bins containing those packets on the main screen.
         */

        //create screen and drawer
        MainScreen screen = new MainScreen();



        //create bins with: x, y, width, height, ContentHeight
        Bin bin1 = new Bin(20);
        Bin bin2 = new Bin(10);
        Bin bin3 = new Bin(10);
        Bin bin4 = new Bin(10);
        Bin bin5 = new Bin(10);
        Bin bin6 = new Bin(10);
//        Bin bin6 = new Bin(((binWspacing * 6) + binWidth * 5), binHspacing, binWidth, binHeight, 10);

        //create packets with: packetID , ContentHeight
        Packet packet1 = new Packet(1, 3);
        Packet packet2 = new Packet(2, 2);
        Packet packet3 = new Packet(3, 3);
        Packet packet4 = new Packet(4, 4);
        Packet packet5 = new Packet(5, 4);
        Packet packet6 = new Packet(6, 3);
        Packet packet7 = new Packet(7, 4);
        Packet packet8 = new Packet(8, 3);
        Packet packet9 = new Packet(9, 6);

        bin1.addPacket(packet1);
        bin1.addPacket(packet2);
        bin1.addPacket(packet4);

        bin2.addPacket(packet3);
        bin2.addPacket(packet5);

        bin3.addPacket(packet2);
        bin3.addPacket(packet4);

        bin4.addPacket(packet6);
        bin4.addPacket(packet7);

        bin5.addPacket(packet1);
        bin5.addPacket(packet3);

        bin6.addPacket(packet8);
        bin6.addPacket(packet9);

//
//
//        bin6.addPacket(packet2);
//        bin6.addPacket(packet3);
//        bin6.addPacket(packet5);

        Drawer draw1 = new Drawer("Simpel gretig",bin1, bin2, binHeight, binWidth);
        Drawer draw2 = new Drawer("Gretig",bin3, bin4, binHeight, binWidth);
        Drawer draw3 = new Drawer("Volledige masturbatie Algoritme",bin5, bin6, binHeight, binWidth);
//        Drawer draw2 = new Drawer(bin3, bin4);
        //retrieve all the dimensions for the to be drawn bins and packages

        screen.addToScreen(draw1);
        screen.addToScreen(draw2);
        screen.addToScreen(draw3);

    }
}
