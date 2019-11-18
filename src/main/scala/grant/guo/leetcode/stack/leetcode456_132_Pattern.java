package grant.guo.leetcode.stack;

import org.omg.CORBA.INTERNAL;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/132-pattern/
 *
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
 *
 * Note: n will be less than 15,000.
 *
 * https://www.cnblogs.com/grandyang/p/6081984.html
 *
 * https://www.cnblogs.com/grandyang/p/8887985.html     summuary of monotone stack
 *
 * https://blog.csdn.net/liujian20150808/article/details/50752861
 */
public class leetcode456_132_Pattern {

    public boolean find132pattern(int[] nums) {
        if(nums == null || nums.length < 3)
            return false;

        int[] min = new int[nums.length];
        min[0] = nums[0];
        for(int i = 1;i< nums.length;i++) {
            min[i] = Math.min(min[i-1], nums[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for(int j = nums.length - 1;j>=0;j--) {
            if(nums[j] > min[j]) {
                while(!stack.isEmpty() && stack.peek() <= min[j])
                    stack.pop();
                if(!stack.isEmpty() && stack.peek() < nums[j])
                    return true;
                stack.push(nums[j]);
            }
        }
        return false;
    }

    public boolean find132pattern1(int[] nums) {
        if(nums == null || nums.length < 3)
            return false;

        int three = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int i = nums.length - 1;i >=0;i--) {
            if(!stack.isEmpty() && nums[i] < three) return true;
            while(!stack.isEmpty() && nums[i] > stack.peek()) {
                three = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }



    public static void main(String[] args) {
        leetcode456_132_Pattern inst = new leetcode456_132_Pattern();

//        int[] nums = {1,2,3,4};
//        int[] nums = {3,1,4,2};
//        int[] nums = {-1,3,2,0};
        int[] nums = {1, 12, 3,4,6,11,20};
        System.out.println(inst.find132pattern1(nums));
    }
}
