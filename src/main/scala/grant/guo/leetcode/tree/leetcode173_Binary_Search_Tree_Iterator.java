package grant.guo.leetcode.tree;

import java.util.Queue;
import java.util.Stack;

public class leetcode173_Binary_Search_Tree_Iterator {
    public static void main(String[] args) {
        TreeNode node = buildTree();

        BSTIterator iter = new BSTIterator(node);
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
    private static TreeNode buildTree() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        return root;
    }
}

class BSTIterator {

    private TreeNode curr = null;
    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        this.curr = root;
    }

    /** @return the next smallest number */
    public int next() {

        if (this.curr != null || !stack.isEmpty()) {
            while(this.curr != null) {
                stack.push(this.curr);
                this.curr = curr.left;
            }

        }

        TreeNode pop = this.stack.pop();
        this.curr = pop.right;
        return pop.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (this.curr != null || !stack.isEmpty());
    }
}