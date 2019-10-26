package grant.guo.leetcode.tree;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class leetcode102_binary_tree_level_order_traversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> sub = new ArrayList<>();
            Queue<TreeNode> curr = queue;
            queue = new LinkedList<>();
            while(!curr.isEmpty()) {
                TreeNode top = curr.poll();
                sub.add(top.val);
                if(top.left != null)
                    queue.add(top.left);
                if(top.right != null)
                    queue.add(top.right);
            }
            ret.add(sub);
        }
        return ret;
    }

    public static void main(String[] args) {
        leetcode102_binary_tree_level_order_traversal inst = new leetcode102_binary_tree_level_order_traversal();
        TreeNode root = TreeNode.generateTree();
        List<List<Integer>> ret = inst.levelOrder(root);

        Iterator<List<Integer>> iter1 = ret.iterator();
        while(iter1.hasNext()) {
            Iterator<Integer> iter2 = iter1.next().iterator();
            while(iter2.hasNext()) {
                System.out.println(iter2.next() + ", ");
            }
            System.out.println();
        }
    }
}
