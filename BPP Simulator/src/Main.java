import java.util.ArrayList;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * This class is where everything starts running
 */
public class Main {
    
    private static int binHeight = 400;
    private static int binWidth = 150;
    private static ArrayList<Bin> binArray = new ArrayList<>();
    private static ArrayList<Packet> order = new ArrayList<Packet>();
    private static ArrayList<Drawer> drawers = new ArrayList<Drawer>();

    private static SimpelGretig simpel;
    private static LookUp lookUp;
    private static EnumeratieVB enumeratie;


    public static void main(String[] args) {
        /**
         * This methode runs everything, creating and showing the window, creating bins, creating packets and putting them into
         * the bins, and drawing the bins containing those packets on the main screen. Creating the algo's.
         */

        //create bins with: capacity
        Bin bin1 = new Bin(10);
        Bin bin2 = new Bin(10);
        Bin bin3 = new Bin(10);
        Bin bin4 = new Bin(10);
        Bin bin5 = new Bin(10);
        Bin bin6 = new Bin(10);

        binArray.add(bin1);
        binArray.add(bin2);
        binArray.add(bin3);
        binArray.add(bin4);
        binArray.add(bin5);
        binArray.add(bin6);


        //retrieve all the dimensions for the to be drawn bins and packages
        //create screen and drawer
        MainScreen screen = new MainScreen(drawers, binArray, order);
        Drawer draw1 = new Drawer(screen, "Simple Greedy",bin1, bin2, order, binHeight, binWidth);
        Drawer draw2 = new Drawer(screen, "Simple Look Ahead",bin3, bin4, order, binHeight, binWidth);
        Drawer draw3 = new Drawer(screen, "Enumeration", bin5, bin6, order, binHeight, binWidth);
        drawers.add(draw1);
        drawers.add(draw2);
        drawers.add(draw3);

        screen.addToScreen(draw1);
        screen.addToScreen(draw2);
        screen.addToScreen(draw3);

        simpel = new SimpelGretig(bin1, bin2, screen, draw1, 1);
        lookUp = new LookUp(bin3, bin4, screen, draw2, 2);
        enumeratie = new EnumeratieVB(bin5, bin6, screen, draw3, 3);

        simpel.setOrder(order);
        lookUp.setOrder(order);
        enumeratie.setOrder(order);

        screen.addSimpelGretig(simpel);
        screen.addLookUp(lookUp);
        screen.addEnumeratie(enumeratie);


    }
}
