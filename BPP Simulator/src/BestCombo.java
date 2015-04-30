/**
 * Created by ewart on 28-4-2015.
 */
import java.util.ArrayList;
import java.util.Stack;

public class BestCombo {

    /** Set a value for target sum */
    public static final int TARGET_SUM = 10;

    private Stack<Packet> stack = new Stack<Packet>();
    private Stack<Packet> notStack = new Stack<Packet>();
    private ArrayList<Packet> bestCombination= new ArrayList<Packet>();


    private int sumInStack = 0;
    private int sumNotInStack = 0;

    private int bestStackId = 10000;

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
        //if stack != 10
        if (sumInStack < TARGET_SUM
                && sumInStack > calculateStackHeight(bestCombination)) {
            bestCombination.clear();
            this.bestStackId = fromIndex;
            for (Packet p : stack) {
                bestCombination.add(p);
            }
        }
        //if stack == 10 and fromindex < beste stackid
        if (sumInStack == TARGET_SUM
                && fromIndex < bestStackId) {
            System.out.println("Current : " + calculateStackHeight(bestCombination) + " | " + bestStackId + ", New: " + sumInStack + " | " + fromIndex);
            this.bestCombination.clear();
            this.bestStackId = fromIndex;
            for (Packet p : stack) {
                bestCombination.add(p);
            }
        }

        for (int currentIndex = fromIndex; currentIndex < endIndex; currentIndex++) {

            if (sumInStack + data.get(currentIndex).getPacketHeight() <= TARGET_SUM) {
                stack.push(data.get(currentIndex));
                sumInStack += data.get(currentIndex).getPacketHeight();

                /*
                * Make the currentIndex +1, and then use recursion to proceed
                * further.
                */
                populateSubset(data, currentIndex + 1, endIndex);
                sumInStack -= stack.pop().getPacketHeight();
            } else {
                if(sumNotInStack + data.get(currentIndex).getPacketHeight() > TARGET_SUM) {
                    currentIndex++;
                    sumNotInStack -= notStack.pop().getPacketHeight();
                } else {
                    sumNotInStack += data.get(currentIndex).getPacketHeight();
                    notStack.push(data.get(currentIndex));
                }
            }
        }
        return bestCombination;
    }

    /**
     * Print satisfied result. i.e. 15 = 4+6+5
     */

    private void print(Stack<Integer> stack) {
        StringBuilder sb = new StringBuilder();
        sb.append(TARGET_SUM).append(" = ");
        for (Integer i : stack) {
            sb.append(i).append("+");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
    }
}