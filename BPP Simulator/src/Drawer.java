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
    private int binHeight, binWidth, binSpacing, binLines;
    private boolean bin;
    private String algo;
    private boolean konami;
    public Drawer(String algo, Bin left, Bin right, int binHeight, int binWidth) {
        this.bins = new ArrayList<Bin>();
        bins.add(left);
        bins.add(right);
        this.binHeight = binHeight;
        this.binWidth = binWidth;
        this.binSpacing = 50;
        this.algo = algo;
        this.binLines = 10;
        this.konami = false;
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
        //for loop in for loop gets dimension for the specific shape
            int x = 10;
        g.drawRect(0,0,binWidth*2 + binSpacing + 40, binHeight + 40);

        g.drawString(algo,10,12);
        for(Bin bin: bins) {
            int y = 20;
            boolean drawBin = false;
            for (Packet packet : bin.getPackets()) {
                if(!drawBin) {
                    drawBin = true;

                    //Draw packetheights in bin
                    g.drawLine(x, y, x, y + binHeight);
                    for(int line = 0; line <= bin.getBinCapacityHeight(); line++) {
                        g.drawLine(x,y +line *packagesSteps(bin.getBinCapacityHeight()),x +binLines, y + line *packagesSteps(bin.getBinCapacityHeight()));
                    }
                    x+=binLines;
                    //gray bin
                    g.setColor(new Color(192, 192, 192));
                    g.fillRect(x, y, binWidth, binHeight + 1);
                    y += binHeight;

                    g.setColor(Color.black);
                    g.drawString(   "" + bin.getBinCapicity() + "/" + bin.getBinCapacityHeight() +//  5/10
                                    " "+ (bin.getBinCapicity()*100)/bin.getBinCapacityHeight()+ "%",// 50%
                                    x + binWidth - 50, 430);
                }

                if(konami) {
                    Random rand = new Random();

                    int rc = rand.nextInt(255);
                    int gc = rand.nextInt(255);
                    int bc = rand.nextInt(255);

                    g.setColor(new Color(rc,gc,bc));
                } else {
                    //paint packet
                    g.setColor(packet.getColor());
                }
                g.fillRect(x + binLines, y, binWidth - 20, packet.getPacketHeight() * -packagesSteps(bin.getBinCapacityHeight()));
                y -= packet.getPacketHeight() * packagesSteps(bin.getBinCapacityHeight());
                //line between packets
                g.setColor(Color.BLACK);
                g.drawLine(x + binLines, y, x + binWidth - 11, y);

                System.out.println("Bin:" + bin.getAmountOfPackages() + " current y: " + y);
                System.out.println("-------------------------------");
                System.out.println((bin.getBinCapicity()*100)/bin.getBinCapacityHeight()+ "%");
            }

            x+= binWidth + binSpacing;
        }
        if(konami) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint(); //disco
        }
    }


}
