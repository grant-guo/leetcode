package grant.guo.leetcode.tree;

import java.util.*;

/**
 * https://leetcode.com/problems/path-sum-ii/
 */
public class leetcode113_path_sum_ii {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        if(root == null){
            return new ArrayList<>();
        }

        if (root.right == null && root.left == null && root.val == sum) {
            List<List<Integer>> outer = new ArrayList<>();
            List<Integer> inner = new ArrayList<>();
            inner.add(root.val);
            outer.add(inner);
            return outer;
        }

        List<List<Integer>> left = pathSum(root.left, sum -root.val);
        List<List<Integer>> right = pathSum(root.right, sum- root.val);

        if(left != null && right != null) {
            addValueIntoList(left, root.val);
            addValueIntoList(right, root.val);
            left.addAll(right);
            return left;
        } else if (left != null && right  == null){
            addValueIntoList(left, root.val);
            return left;
        } else if(left == null && right != null){
            addValueIntoList(right, root.val);
            return right;
        } else {
            return null;
        }
    }

    private List<List<Integer>> addValueIntoList(List<List<Integer>> list, int value) {
        Iterator<List<Integer>> iter1 = list.iterator();
        while(iter1.hasNext()) {
            List<Integer> l = iter1.next();
            l.add(0, value);
        }
        return list;
    }


    public static void main(String[] args) {
        leetcode113_path_sum_ii inst = new leetcode113_path_sum_ii();
        List<List<Integer>> ret = inst.pathSum(inst.buildTree(), 22);

        Iterator<List<Integer>> iter1 = ret.iterator();
        while(iter1.hasNext()) {
            Iterator<Integer> iter2 = iter1.next().iterator();
            while(iter2.hasNext()) {
                System.out.print(iter2.next() + ", ");
            }
            System.out.println();
        }
    }

    private TreeNode buildTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        return root;
    }
}
