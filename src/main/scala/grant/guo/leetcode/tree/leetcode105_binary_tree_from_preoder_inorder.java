package grant.guo.leetcode.tree;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class leetcode105_binary_tree_from_preoder_inorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // the first element in preorder is the root of the tree, define it as ROOT
        // any elements in inorder which are located before ROOT, will be the left tree of ROOT,
        // any elements which are located after ROOT, will be the right tree of ROOT
        // then recursively generate the whole tree

        if(preorder.length == 0 && inorder.length == 0)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        int i = 0;
        while(preorder[0] != inorder[i]) {
            i++;
        }
        TreeNode left = null;
        if(i > 0)
        {
            left =  buildTree(
                    copy(preorder, 1, i),
                    copy(inorder, 0, i-1)
            );
        }


        TreeNode right = null;
        if (i < inorder.length - 1) {
            right =  buildTree(
                    copy(preorder, i+1, preorder.length - 1),
                    copy(inorder, i+1, inorder.length - 1)
            );
        }

        root.right = right;
        root.left = left;
        return root;
    }


    private int[] copy(int[] target, int start, int end) {
        int[] ret = new int[end-start+1];
        for(int i = start,j = 0;i<= end;i++, j++)
            ret[j] = target[i];
        return ret;
    }

    public static void main(String[] args) {
        leetcode105_binary_tree_from_preoder_inorder inst = new leetcode105_binary_tree_from_preoder_inorder();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode ret = inst.buildTree(preorder, inorder);
        System.out.println(ret);
    }
}
