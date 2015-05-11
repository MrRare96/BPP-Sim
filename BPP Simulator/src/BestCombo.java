/**
 * Created by ewart on 28-4-2015.
 */
import java.util.ArrayList;
import java.util.Stack;

public class BestCombo {

    /** Set a value for target sum */
    private  final int target;
    private int sumInStack, bestStackId;

    private Stack<Packet> stack;
    private ArrayList<Packet> bestCombination;


    public BestCombo(int arrayLength, int targetNumber) {
        this.stack =  new Stack<>();
        this.bestCombination =  new ArrayList<>();
        this.target = targetNumber;
        this.sumInStack = 0;

        // bestStackId is equal to length of data array.
        // if arrayLength is higher then 2 times the target bestStackId equals target times 2 to prevent unnecessary calculations.
        if( arrayLength > targetNumber) {
            this.bestStackId = targetNumber;
        } else {
            this.bestStackId = arrayLength;
        }
    }


    public int getBestStackId() {
        return bestStackId;
    }

    public ArrayList<Packet> calculateTarget(ArrayList<Packet> data, int fromIndex, int endIndex) {

        //if solution uses a lower index then the previous
        if (sumInStack == target && fromIndex < bestStackId) {
            this.bestStackId = fromIndex;
            this.bestCombination.clear();
            for (Packet p : stack) {
                bestCombination.add(p);
            }
        }

        for (int currentIndex = fromIndex; currentIndex < endIndex && currentIndex < bestStackId; currentIndex++) {
            if (sumInStack + data.get(currentIndex).getPacketHeight() <= target) {
                stack.push(data.get(currentIndex));
                sumInStack += data.get(currentIndex).getPacketHeight();

                /*
                * Make the currentIndex +1, and then use recursion to proceed
                * further.
                */
                calculateTarget(data, currentIndex + 1, endIndex);
                sumInStack -= stack.pop().getPacketHeight();
            }
        }
        return bestCombination;
    }

}
