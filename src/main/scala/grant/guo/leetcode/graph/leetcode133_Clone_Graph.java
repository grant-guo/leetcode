package grant.guo.leetcode.graph;

import java.util.*;

public class leetcode133_Clone_Graph {
    public Node cloneGraph(Node node) {

        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            Queue<Node> curr = queue;
            queue = new LinkedList<>();

            while(!curr.isEmpty()) {
                Node pop = curr.poll();
                Node newE = null;
                if(!map.containsKey(pop.val)) {
                    newE = new Node(pop.val, new ArrayList<>());
                    map.put(pop.val, newE);
                }
                else {
                    newE = map.get(pop.val);
                }

                Iterator<Node> iter = pop.neighbors.iterator();
                while(iter.hasNext()) {
                    Node neighbour = iter.next();
                    if(map.containsKey(neighbour.val)) {
                        newE.neighbors.add(neighbour);
                        map.get(neighbour.val).neighbors.add(newE);
                    } else {
                        if(!existsIn(neighbour.val, queue))
                            queue.add(neighbour);
                    }
                }
            }
        }
        return map.values().iterator().next();

    }

    private boolean existsIn(int value, Queue<Node> queue) {
        Iterator<Node> iter = queue.iterator();
        while(iter.hasNext()) {
            if (iter.next().val == value)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        leetcode133_Clone_Graph inst = new leetcode133_Clone_Graph();
        Node ret = inst.cloneGraph(buildGraph());
        System.out.println();
    }
    private static Node buildGraph() {
        Node node1 = new Node() {{this.val = 1;}};
        Node node2 = new Node(){{this.val = 2;}};
        Node node3 = new Node(){{this.val = 3;}};
        Node node4 = new Node(){{this.val = 4;}};

        node1.neighbors = Arrays.asList(node2, node4);
        node2.neighbors = Arrays.asList(node1, node3);
        node3.neighbors = Arrays.asList(node2, node4);
        node4.neighbors = Arrays.asList(node1, node3);

        return node1;
    }
}
