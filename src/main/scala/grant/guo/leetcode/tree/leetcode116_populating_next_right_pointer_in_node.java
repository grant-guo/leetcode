package grant.guo.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */
public class leetcode116_populating_next_right_pointer_in_node {

    public Node connect(Node root) {
        if(root == null)
            return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Queue<Node> buffer = queue;
            queue = new LinkedList<>();
            while(!buffer.isEmpty()) {
                Node top = buffer.poll();
                if(top.left != null)
                    queue.add(top.left);
                if (top.right != null)
                    queue.add(top.right);
                if (buffer.peek() != null) {
                    top.next = buffer.peek();
                } else {
                    top.next = null;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        leetcode116_populating_next_right_pointer_in_node inst = new leetcode116_populating_next_right_pointer_in_node();
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};