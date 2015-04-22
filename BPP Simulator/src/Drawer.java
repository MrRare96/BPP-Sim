import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * drawer draws all the what you put in array(contains coordinates and size of shape)
 */

public class Drawer extends JPanel {

    private ArrayList<Bin> bins;
    private int binHeight, binWidth;
    private boolean bin;
    public Drawer(Bin left, Bin right, int binHeight, int binWidth) {
        this.bins = new ArrayList<Bin>();
        bins.add(left);
        bins.add(right);
        this.binHeight = binHeight;
        this.binWidth = binWidth;
    }

    public Dimension getPreferredSize()
    {
        return (new Dimension(768, 750));
    }

    public int packagesSteps(int binCapacity) {
        return binHeight/binCapacity;
    }

    public void paintComponent(Graphics g){
        /**
         * In this method the shapes defined in the array retrieved from the drawingShapes method are being
         * drawn to the JPanel
         */

        super.paintComponent(g);
        //for loop in for loop gets dimension for the specific shape
            int x = 0;
        for(Bin bin: bins) {
            int y = 0;
            boolean drawBin = false;
            for (Packet packet : bin.getPackets()) {
                if(!drawBin) {
                    g.setColor(Color.BLUE);
                    g.drawString("test", x, y);
                    drawBin = true;
                    g.fillRect(x, y, binWidth, binHeight);
                    y += binHeight;
                    g.setColor(Color.black);
                    g.drawString("" + bin.getBinCapicity() + "/" + bin.getBinCapacityHeight() +
                            " "+ (bin.getBinCapicity()*100)/bin.getBinCapacityHeight()+ "%",
                            x+ 200, 410);
                }
                Random rand = new Random();

                int  rc = rand.nextInt(255);
                int  gc = rand.nextInt(255);
                int bc = rand.nextInt(255);

//                g.drawLine(x, y, binWidth, binHeight);

                g.setColor(new Color(rc, gc, bc));
                g.fillRect(x, y, binWidth, packet.getPacketHeight() * -packagesSteps(bin.getBinCapacityHeight()));
                y -= packet.getPacketHeight() * packagesSteps(bin.getBinCapacityHeight());
                System.out.println("Bin:" + bin.getAmountOfPackages() + " current y: " + y);
                System.out.println("-------------------------------");
                System.out.println((bin.getBinCapicity()*100)/bin.getBinCapacityHeight()+ "%");
            }
            x+= binWidth *2;
        }
        repaint();

    }


}
