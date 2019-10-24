package grant.guo.leetcode.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class leetcod95_Unique_Binary_Search_Trees {

    public List<TreeNode> generateTrees(int n) {
        int[] nums = IntStream.range(1, n+1).toArray();

        return build(nums, 0, nums.length - 1);
    }

    private List<TreeNode> build(int[] nums, int start, int end) {
        if(start < 0 || start > end)
            return null;
        if (start == end) {
            List<TreeNode> ret = new ArrayList<>();
            ret.add(new TreeNode(nums[start]));
            return ret;
        }
        List<TreeNode> ret = new ArrayList<>();
        for(int i = start;i <= end;i++) {
            List<TreeNode> left = build(nums, start, i-1);
            List<TreeNode> right = build(nums, i+1, end);
            if(left != null && right != null) {
                Iterator<TreeNode> iter = left.iterator();
                while(iter.hasNext()) {
                    Iterator<TreeNode> iter1 = right.iterator();
                    while(iter1.hasNext()) {
                        TreeNode curr = new TreeNode(nums[i]);
                        curr.left = iter.next();
                        curr.right =  iter1.next();
                        ret.add(curr);
                    }
                }
            } else if (left != null) {
                Iterator<TreeNode> iter = left.iterator();
                while(iter.hasNext()) {
                    TreeNode curr = new TreeNode(nums[i]);
                    curr.left = iter.next();
                    curr.right = null;
                    ret.add(curr);
                }
            } else if (right != null) {
                Iterator<TreeNode> iter = right.iterator();
                while(iter.hasNext()) {
                    TreeNode curr = new TreeNode(nums[i]);
                    curr.left = null;
                    curr.right =  iter.next();
                    ret.add(curr);
                }
            }else {
                ret.add(new TreeNode(nums[i]));
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        leetcod95_Unique_Binary_Search_Trees inst = new leetcod95_Unique_Binary_Search_Trees();
        int n = 3;
        List<TreeNode> ret = inst.generateTrees(3);
    }
}
