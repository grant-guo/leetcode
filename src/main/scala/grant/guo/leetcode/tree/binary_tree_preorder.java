package grant.guo.leetcode.tree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class binary_tree_preorder {

    public List<Integer> recursive(TreeNode node) {
        if(node == null) {
            return new ArrayList<>();
        } else {
            List<Integer> ltree = recursive(node.left);
            List<Integer> rtree = recursive(node.right);

            List<Integer> ret = new ArrayList<>();
            ret.add(node.val);
            ret.addAll(ltree);
            ret.addAll(rtree);

            return ret;
        }
    }

    public List<Integer> iterative(TreeNode node) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = node;

        while(!stack.isEmpty() || curr != null) {
            if(curr != null) {
                ret.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop().right;
            }

        }

        return ret;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.generateTree();

        binary_tree_preorder inst = new binary_tree_preorder();
        List<Integer> ret1 = inst.recursive(root);
        ret1.forEach(n -> System.out.print(n + ", "));


        List<Integer> ret2 = inst.recursive(root);
        ret2.forEach(n -> System.out.print(n + ", "));
    }
}
