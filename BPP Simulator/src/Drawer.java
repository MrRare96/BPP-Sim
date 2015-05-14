import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * drawer draws all the what you put in array(contains coordinates and size of shape)
 */

public class Drawer extends JPanel {

    private ArrayList<Bin> bins;
    private ArrayList<Packet> order;
    private int binHeight, binWidth, binSpacing, binLines;
    private boolean bin;
    private String algo;
    private boolean konami;
    private MainScreen parent;

    public Drawer(MainScreen parent, String algo, Bin left, Bin right,ArrayList<Packet> order, int binHeight, int binWidth) {
        this.bins = new ArrayList<>();
        bins.add(left);
        bins.add(right);
        this.order = order;
        this.binHeight = binHeight;
        this.binWidth = binWidth;
        this.binSpacing = 50;
        this.algo = algo;
        this.binLines = 10;
        this.konami = false;
        this.parent = parent;
    }

    public Dimension getPreferredSize()
    {
        return (new Dimension(binWidth*2+binSpacing+binLines*2 +30, binHeight +150));
    }

    public int packagesSteps(int binCapacity) {
        return binHeight/binCapacity;
    }

    public void setKonami() {
        this.konami = true;
    }

    public void paintComponent(Graphics g){
        /**
         * In this method the shapes defined in the array retrieved from the drawingShapes method are being
         * drawn to the JPanel
         */

        super.paintComponent(g);
        int x = 10;
        //outline bins
        g.drawRect(0,0,binWidth*2 + binSpacing + 40, binHeight + 40);

        g.drawString(algo,10,12);

        for(Bin bin: bins) {
            int y = 20;

            //Draw packetheights in bin
            g.drawLine(x, y, x, y + binHeight);
            for(int line = 0; line < bin.getBinCapacityHeight(); line++) {
                  g.drawLine(x,y + line * packagesSteps(bin.getBinCapacityHeight()), x + binLines,y + line * packagesSteps(bin.getBinCapacityHeight()));
            }
            x+=binLines;
            //draw gray bin
            g.setColor(new Color(192, 192, 192));
            g.fillRect(x, y, binWidth, binHeight + 1);
            y += binHeight;
            g.setColor(Color.black);
            g.drawString(" Emptied: " + bin.getTimesEmptied(), x, 430);
                    g.drawString(bin.getBinCapicityFilled() + "/" + bin.getBinCapacityHeight() +//  5/10
                            " "+ (bin.getBinCapicityFilled()*100)/bin.getBinCapacityHeight()+ "%",// 50%
                            x + 100, 430);

            for (Packet packet : bin.getPackets()) {

                if(konami) {
                    Random rand = new Random();

                    int rc = rand.nextInt(255);
                    int gc = rand.nextInt(255);
                    int bc = rand.nextInt(255);

                    g.setColor(new Color(rc,gc,bc));
                } else {
                    g.setColor(packet.getColor());
                }
                //draw packet
                g.fillRect(x + binLines, y + packet.getPacketHeight() * -packagesSteps(bin.getBinCapacityHeight()), binWidth - 20, packet.getPacketHeight() * packagesSteps(bin.getBinCapacityHeight()));
                // pack number text
                Integer packPosition = order.indexOf(packet);
                //package number
                g.setColor(Color.BLACK);
                g.setFont(new Font(null, Font.PLAIN, 12));
                g.drawString("#" + packPosition.toString(), x + 10, y - 5);

                y -= packet.getPacketHeight() * packagesSteps(bin.getBinCapacityHeight());
                //line between packets
                g.drawLine(x + binLines, y, x + binWidth - 11, y);
            }

            x+= binWidth + binSpacing;
        }

    }


}
