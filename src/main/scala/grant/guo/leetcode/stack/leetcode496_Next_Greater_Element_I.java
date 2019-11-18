package grant.guo.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * https://leetcode.com/problems/next-greater-element-i/
 *
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 *
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 */
public class leetcode496_Next_Greater_Element_I {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        int[] greater = new int[nums1.length];
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
            stack.push(num);
        }
        while (!stack.isEmpty())
            map.put(stack.pop(), -1);

        for (int i = 0; i < nums1.length; i++)
            greater[i] = map.get(nums1[i]);

        return greater;
    }
    public static void main(String[] args) {
        leetcode496_Next_Greater_Element_I inst = new leetcode496_Next_Greater_Element_I();
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};

        int[] ret = inst.nextGreaterElement(nums1, nums2);
        for(int i : ret)
            System.out.println(i);
    }
}


