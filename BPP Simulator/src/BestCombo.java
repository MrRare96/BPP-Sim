/**
 * Created by ewart on 28-4-2015.
 */
import java.util.ArrayList;
import java.util.Stack;

public class BestCombo {

    /** Set a value for target sum */
    public int TARGET_SUM;

    private Stack<Packet> stack = new Stack<Packet>();
    private ArrayList<Packet> bestCombination= new ArrayList<>();


    private int sumInStack = 0;

    private int bestStackId;

    public BestCombo(int bestStackId, int target) {
        this.TARGET_SUM = target;
        if( bestStackId > target * 2) {
            this.bestStackId = target * 2;
        } else {
            this.bestStackId = bestStackId;
        }
    }

    public int calculateStackHeight(ArrayList<Packet> stack){
        int stackHeight = 0;
        for( Packet p: stack) {
            stackHeight += p.getPacketHeight();
        }
        return stackHeight;
    }

    public int getBestStackId() {
        return bestStackId;
    }

    public ArrayList<Packet> populateSubset(ArrayList<Packet> data, int fromIndex, int endIndex) {

        if (sumInStack == TARGET_SUM
                && fromIndex < bestStackId) {
            this.bestCombination.clear();
            this.bestStackId = fromIndex;
            for (Packet p : stack) {
                bestCombination.add(p);
            }
        }

        for (int currentIndex = fromIndex; currentIndex < endIndex && currentIndex < bestStackId; currentIndex++) {
            if (sumInStack + data.get(currentIndex).getPacketHeight() <= TARGET_SUM && fromIndex <= bestStackId) {
                stack.push(data.get(currentIndex));
                sumInStack += data.get(currentIndex).getPacketHeight();

                /*
                * Make the currentIndex +1, and then use recursion to proceed
                * further.
                */
                populateSubset(data, currentIndex + 1, endIndex);
                sumInStack -= stack.pop().getPacketHeight();
            }
        }
        return bestCombination;
    }

}
