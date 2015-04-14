import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Eldin on 4/9/2015 for Windesheim Magazijn Robot KBS
 * drawer draws all the what you put in array(contains coordinates and size of shape)
 */

public class Drawer extends JPanel {

    private ArrayList<ArrayList> rectangleShape;

    public Drawer(){
        rectangleShape = new ArrayList<ArrayList>();
    }

    public void drawingShapes(ArrayList<ArrayList> rectangleShapeDimensions){
        /**
         * Adds the dimensions from the given arraylist to another arraylist, because otherwise adding
         * multiple shapes in batch is not possible (in this case)
         */

        rectangleShape.add(rectangleShapeDimensions);
        repaint();
    }

    public Dimension getPreferredSize()
    {
        return (new Dimension(500, 450));
    }


    public void paintComponent(Graphics g){
        /**
         * In this method the shapes defined in the array retrieved from the drawingShapes method are being
         * drawn to the JPanel
         */

        super.paintComponent(g);

        //for loop in for loop gets dimension for the specific shape
        for(ArrayList<ArrayList> rectangleShapes : rectangleShape){
            for(ArrayList<Integer> rectangleShapeInfo : rectangleShapes){
                if(rectangleShapeInfo.get(5) == 0){
                    g.setColor(Color.BLUE);
                } else {
                    Random rand = new Random();

                    int  rc = rand.nextInt(255);
                    int  gc = rand.nextInt(255);
                    int  bc = rand.nextInt(255);

                    g.drawLine(rectangleShapeInfo.get(1), rectangleShapeInfo.get(2), rectangleShapeInfo.get(1), rectangleShapeInfo.get(2) + 1);
                    g.setColor(new Color(rc, gc, bc));
                }

                g.fillRect(rectangleShapeInfo.get(1), rectangleShapeInfo.get(2), rectangleShapeInfo.get(3), rectangleShapeInfo.get(4));
            }
        }

    }


}
