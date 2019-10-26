package grant.guo.leetcode.tree;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class leetcode114_Flatten_Binary_Tree_to_Linked_List {

    public void flatten(TreeNode root) {
        if(root == null)
            return;

        TreeNode left = root.left;
        TreeNode right = root.right;
        flatten(left);
        flatten(right);

        if(left != null) {
            TreeNode cur = left;
            while(cur != null){
                if(cur.right == null)
                    break;
                cur = cur.right;
            }
            cur.right = right;
        }
        root.left = null;
        if(left != null)
            root.right = left;

    }

    public static void main(String[] args) {
        leetcode114_Flatten_Binary_Tree_to_Linked_List inst = new leetcode114_Flatten_Binary_Tree_to_Linked_List();
        TreeNode root = leetcode114_Flatten_Binary_Tree_to_Linked_List.buildTree();
        inst.flatten(root);
        System.out.println();
    }

    private static TreeNode buildTree() {
        TreeNode  root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        return root;
    }
}
