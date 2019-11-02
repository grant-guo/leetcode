package grant.guo.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class leetcode222_Count_Complete_Tree_Nodes {
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Queue<TreeNode> cur = queue;
            queue = new LinkedList<>();
            while(!cur.isEmpty()) {
                TreeNode tn = cur.poll();
                count++;
                if(tn.left != null && tn.right != null) {
                    queue.add(tn.left);
                    queue.add(tn.right);
                } else if(tn.left != null && tn.right == null) {
                    return count*2;
                } else {
                    return (count-1)*2 + 1;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        leetcode222_Count_Complete_Tree_Nodes inst = new leetcode222_Count_Complete_Tree_Nodes();
        int ret = inst.countNodes(buildTree());
        System.out.println(ret);
    }
    private static TreeNode buildTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        return root;
    }
}
