package grant.guo.leetcode.tree;

public class leetcode104_max_depth_binary_tree {

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        } else {
            int l = maxDepth(root.left);
            int r = maxDepth(root.right);
            return 1+ Math.max(l, r);
        }
    }
    public static void main(String[] args) {
        leetcode104_max_depth_binary_tree inst = new leetcode104_max_depth_binary_tree();
        int ret = inst.maxDepth(TreeNode.generateTree());
        System.out.println(ret);
    }
}
