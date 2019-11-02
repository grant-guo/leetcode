package grant.guo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * hard
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 *
 * Not Done Yet
 *
 *
 */
public class leetcode124_BTree_Max_Path_Sum {

    public int maxPathSum(TreeNode root) {

        if(root == null)
            return Integer.MIN_VALUE;
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);

        List<Integer> list = new ArrayList<>();

        if(root.left == null && root.right == null) {
            list.add(root.val);
        }
        if(root.left != null && root.right != null) {
            list.add(root.val + left + right);
        }

        if (root.left != null) {
            list.add(root.val + left);
        }

        if (root.right != null) {
            list.add(root.val + right);
        }

        list.sort((a, b) -> {
            if(a > b) return -1; else if (a == b) return 0; else return 1;
        });

        return list.get(0);
    }
    public static void main(String[] args) {
        leetcode124_BTree_Max_Path_Sum inst = new leetcode124_BTree_Max_Path_Sum();
        inst.maxPathSum(leetcode124_BTree_Max_Path_Sum.buildTree());
    }

    private static TreeNode buildTree() {
//        TreeNode root = new TreeNode(-10);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.left.left.left = new TreeNode(-1);
        root.right.left = new TreeNode(-2);
        return root;
    }
}
