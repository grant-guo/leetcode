package grant.guo.leetcode.stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-ii/
 */
public class leetcode503_Next_Greater_Element_II {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            while(!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        List<Integer> list = new LinkedList<>();
        while(stack.size() > 1) {
            list.add(stack.pop());
        }
        int max = stack.pop();
        map.put(max, -1);
        for(int num: list) {
            map.put(num, max);
        }

        int[] ret = new int[nums.length];
        for(int i = 0;i < ret.length;i++){
            ret[i] = map.get(nums[i]);
        }
        return ret;
    }
    public static void main(String[] args) {
        leetcode503_Next_Greater_Element_II inst = new leetcode503_Next_Greater_Element_II();;
        int[] nums = {1,2,1};
        int[] ret = inst.nextGreaterElements(nums);
        for(int num: ret) {
            System.out.print(num + ", ");
        }
    }
}
