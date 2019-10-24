package grant.guo.leetcode.tree;

public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }

     /*
      *                1
      *             2     3
      *          4    5
      */

     public static TreeNode generateTree() {
          TreeNode root = new TreeNode(1);
          root.left = new TreeNode(2);
          root.right = new TreeNode(3);
          root.left.left = new TreeNode(4);
          root.left.right = new TreeNode(5);
          return root;
     }
}
