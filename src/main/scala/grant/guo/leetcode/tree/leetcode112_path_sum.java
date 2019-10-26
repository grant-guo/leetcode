package grant.guo.leetcode.tree;

/**
 * https://leetcode.com/problems/path-sum/submissions/
 */
public class leetcode112_path_sum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null)
            return false;
        if(root.left == null && root.right == null && sum == root.val)
            return true;
        int value = root.val;
        return hasPathSum(root.left, sum - value) || hasPathSum(root.right, sum - value);
    }

    public static void main(String[] args) {
        leetcode112_path_sum inst = new leetcode112_path_sum();
    }
}
