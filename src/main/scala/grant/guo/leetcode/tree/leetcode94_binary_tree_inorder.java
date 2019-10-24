package grant.guo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 */
public class leetcode94_binary_tree_inorder {

    public List<Integer> recursive(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        } else {
            List<Integer> ltree = recursive(root.left);
            List<Integer> rtree = recursive(root.right);
            List<Integer> ret = new ArrayList<>();
            ret.addAll(ltree);
            ret.add(root.val);
            ret.addAll(rtree);
            return ret;
        }
    }

    public List<Integer> iterative(TreeNode root) {

        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode peek = stack.pop();
            ret.add(peek.val);
            curr = peek.right;
        }
        return ret;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.generateTree();


        leetcode94_binary_tree_inorder inst = new leetcode94_binary_tree_inorder();
        List<Integer> ret1 = inst.recursive(root);
        ret1.forEach(n -> System.out.print(n + ", "));

        List<Integer> ret2 = inst.iterative(root);
        ret2.forEach(n -> System.out.print(n + ", "));
    }
}
