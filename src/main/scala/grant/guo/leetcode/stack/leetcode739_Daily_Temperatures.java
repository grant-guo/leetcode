package grant.guo.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/daily-temperatures/
 *
 * iven a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 */
public class leetcode739_Daily_Temperatures {

    private static class Item {
        public int value;
        public int index;
        public Item(int v, int i) {
            this.value = v;
            this.index = i;
        }
    }

    public int[] dailyTemperatures(int[] T) {
        Stack<Item> stack = new Stack<>();
        Item[] f = new Item[T.length];
        for(int i = T.length - 1;i >=0 ;i--) {
            while(!stack.isEmpty() && stack.peek().value < T[i]) {
                stack.pop();
            }
            if(!stack.isEmpty())
                f[i] = stack.peek();
            else
                f[i] = new Item(-1, -1);
            stack.push(new Item(T[i], i));
        }
        int[] rets = new int[T.length];
        for(int i = 0;i< rets.length;i++) {
            if(f[i].index == -1)
                rets[i] = 0;
            else {
                rets[i] = f[i].index - i;
            }
        }
        return rets;
    }
    public static void main(String[] args) {
        leetcode739_Daily_Temperatures inst = new leetcode739_Daily_Temperatures();
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] rets = inst.dailyTemperatures(T);
        for(int num: rets)
            System.out.print(num + ", ");
    }
}
