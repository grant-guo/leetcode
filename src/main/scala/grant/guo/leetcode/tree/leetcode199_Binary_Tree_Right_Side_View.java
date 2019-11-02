package grant.guo.leetcode.tree;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class leetcode199_Binary_Tree_Right_Side_View {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Queue<TreeNode> curr = queue;
            queue = new LinkedList<>();
            while(!curr.isEmpty()) {
                TreeNode pop = curr.poll();
                if(pop.left != null)
                    queue.add(pop.left);
                if(pop.right != null)
                    queue.add(pop.right);
                if(curr.isEmpty())
                    ret.add(pop.val);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        leetcode199_Binary_Tree_Right_Side_View inst = new leetcode199_Binary_Tree_Right_Side_View();
        List<Integer> ret = inst.rightSideView(buildTree());

        Iterator<Integer> iter = ret.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
    private static TreeNode buildTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        return root;
    }
}
