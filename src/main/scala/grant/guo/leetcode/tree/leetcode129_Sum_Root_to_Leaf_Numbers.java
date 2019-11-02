package grant.guo.leetcode.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * Note: A leaf is a node with no children.
 */
public class leetcode129_Sum_Root_to_Leaf_Numbers {

    public int sumNumbers(TreeNode root) {
        if(root == null)
            return 0;
        List<List<Integer>> all = getAll(root);
        return  all.stream().map(lst -> {
            int total = 0;
           int base = 1;
           Iterator<Integer> iter = lst.iterator();
           while(iter.hasNext()) {
               total += (iter.next() * base);
               base = base * 10;
           }
           return total;
        }).reduce(0, (a, b) -> a + b);
    }

    private List<List<Integer>> getAll(TreeNode node) {

        if(node.left == null && node.right == null) {
            List<List<Integer>> lst = new ArrayList<>();
            List<Integer> inner = new ArrayList<>();
            inner.add(node.val);
            lst.add(inner);
            return lst;
        }

        List<List<Integer>> ret = new ArrayList<>();

        if (node.left != null) {
            List<List<Integer>> left = getAll(node.left);
            Iterator<List<Integer>> iter1 = left.iterator();
            while(iter1.hasNext()) {
                iter1.next().add(node.val);
            }
            ret.addAll(left);
        }

        if(node.right != null) {
            List<List<Integer>> right = getAll(node.right);
            Iterator<List<Integer>> iter1 = right.iterator();
            while(iter1.hasNext()) {
                iter1.next().add(node.val);
            }
            ret.addAll(right);
        }

        return ret;
    }

    public static void main(String[] args) {
        leetcode129_Sum_Root_to_Leaf_Numbers inst = new leetcode129_Sum_Root_to_Leaf_Numbers();
        int ret = inst.sumNumbers(leetcode129_Sum_Root_to_Leaf_Numbers.buildTree());
        System.out.println(ret);
    }

    private static TreeNode buildTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);
        return root;
    }
}
