package grant.guo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class binary_tree_postorder {

    public List<Integer> recursive(TreeNode node) {
        if(node == null) {
            return new ArrayList<>();
        } else {
            List<Integer> ltree = recursive(node.left);
            List<Integer> rtree = recursive(node.right);
            List<Integer> ret = new ArrayList<>();
            ret.addAll(ltree);
            ret.addAll(rtree);
            ret.add(node.val);

            return ret;
        }
    }

    public List<Integer> iterative(TreeNode node) {
        List<Integer> ret = new ArrayList<>();
        return ret;
    }

    public static void main(String[] args) {
        binary_tree_postorder inst = new binary_tree_postorder();
        TreeNode root = TreeNode.generateTree();
        List<Integer> ret1 = inst.recursive(root);
        ret1.forEach(n -> System.out.print(n + ", "));
    }
}
