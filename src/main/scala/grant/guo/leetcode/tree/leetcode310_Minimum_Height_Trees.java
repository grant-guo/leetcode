package grant.guo.leetcode.tree;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-height-trees/
 *
 *
 */
public class leetcode310_Minimum_Height_Trees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        MHTNode[] nodes = new MHTNode[n];
        for(int i = 0;i < n;i++)
            nodes[i]= new MHTNode(i);

        for(int i = 0;i < edges.length;i++) {
            nodes[edges[i][0]].neighbours.add(nodes[edges[i][1]]);
            nodes[edges[i][1]].neighbours.add(nodes[edges[i][0]]);
        }

        int min = Integer.MAX_VALUE;
        List<Integer> ret = new ArrayList<>();
        for(int i = 0;i < nodes.length;i++) {
            MHTNode curr = nodes[i];
            boolean[] visited = generatedVisited(n);

            int height = findMinHeighTreesR(curr, visited);
            if(height < min) {
                min = height;
                ret = new ArrayList<>();
                ret.add(curr.val);
            } else if (height == min) {
                ret.add(curr.val);
            }
        }
        return ret;
    }

    private boolean[] generatedVisited(int n) {
        boolean[] ret = new boolean[n];
        for(int i = 0;i< n;i++)
            ret[i] = false;
        return ret;
    }

    public int findMinHeighTreesR(MHTNode node, boolean[] visited) {
        visited[node.val] = true;

        List<MHTNode> unhandled = new ArrayList<>();
        Iterator<MHTNode> iter = node.neighbours.iterator();
        while(iter.hasNext()) {
            MHTNode neighbour = iter.next();
            if(visited[neighbour.val] == false)
                unhandled.add(neighbour);
        }
        if(unhandled.size() == 0)
            return 1;

        int max = Integer.MIN_VALUE;
        iter = unhandled.iterator();
        while(iter.hasNext()) {
            int height = findMinHeighTreesR(iter.next(), visited);
            if (height > max)
                max = height;
        }
        return max+1;
    }

    public static void main(String[] args) {
        leetcode310_Minimum_Height_Trees inst = new leetcode310_Minimum_Height_Trees();

//        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
//        List<Integer> ret = inst.findMinHeightTrees(4, edges);

//        int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
//        List<Integer> ret = inst.findMinHeightTrees(6, edges);

        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}};
        List<Integer> ret = inst.findMinHeightTrees(6, edges);

        Iterator<Integer> iter = ret.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    public List<Integer> findMinHeightTreesNew(int n, int[][] edges) {

        // find the longest diameter
        // find all endpoints which are only one connection with other nodes
        // start to find the longest path from one endpoint to another endpoint
        // the minimum tree is (length of path)/2 + 1
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0;i<edges.length;i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            Set<Integer> set = map.get(v1);
            if(set == null) {
                set = new HashSet<>();
                map.put(v1, set);
            }
            set.add(v2);

            set = map.get(v2);
            if(set == null) {
                set = new HashSet<>();
                map.put(v2, set);
            }
            set.add(v1);
        }

        Map<Integer, Integer> endpoints = new HashMap<>();
        Iterator<Integer> iter = map.keySet().iterator();
        while(iter.hasNext()) {
            int node = iter.next();
            Set<Integer> set = map.get(node);
            if(set.size() == 1) {
                endpoints.put(node, set.iterator().next());
            }
        }



        return null;
    }

}

class MHTNode{

    public int val;
    public List<MHTNode> neighbours = new ArrayList<>();

    public MHTNode(int v)  {
        this.val = v;
    }
}
