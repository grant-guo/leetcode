package grant.guo.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 *
 * solution: https://www.programcreek.com/2015/01/leetcode-verify-preorder-serialization-of-a-binary-tree-java/
 *
 * keep trimming the tree until no elements left
 */
public class leetcode331_Verify_Preorder_Serialization_of_BTree {
    public boolean isValidSerialization(String preorder) {
        if(preorder.length() == 0)
            return false;
        String[] tokens = preorder.split(",");
        Stack<String> stack = new Stack<>();

        for(int i = 0;i<tokens.length;i++) {
            if(!tokens[i].equals("#")) {
                stack.push(tokens[i]);
            } else {
                while(!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if(stack.isEmpty())
                        return false;
                    else
                        stack.pop();
                }
                stack.push("#");

            }
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }
    public static void main(String[] args) {
        leetcode331_Verify_Preorder_Serialization_of_BTree inst = new leetcode331_Verify_Preorder_Serialization_of_BTree();
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        boolean ret = inst.isValidSerialization(preorder);
        System.out.println(ret);
    }
}
