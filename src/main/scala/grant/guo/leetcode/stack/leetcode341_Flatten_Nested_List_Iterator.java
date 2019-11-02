package grant.guo.leetcode.stack;

import java.util.*;

public class leetcode341_Flatten_Nested_List_Iterator {

    
    public static class NestedIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.iterator = build(nestedList).iterator();
        }

        @Override
        public Integer next() {
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        private List<Integer> build(List<NestedInteger> nestedList) {
            List<Integer> ret = new ArrayList<>();
            Iterator<NestedInteger> iter = nestedList.iterator();
            while(iter.hasNext()) {
                NestedInteger ni = iter.next();
                if(ni.isInteger()) {
                    ret.add(ni.getInteger());
                } else {
                    ret.addAll(build(ni.getList()));
                }
            }
            return ret;
        }
    }


    public class NestedIterator1 implements Iterator<Integer> {

        private Iterator<NestedInteger> iter;

        private Stack<Iterator<NestedInteger>> stack = new Stack<>();
        private Queue<Integer> queue = new LinkedList<>();

        public NestedIterator1(List<NestedInteger> nestedList) {
            iter = nestedList.iterator();
        }

        @Override
        public Integer next() {
            return queue.poll();
        }

        @Override
        public boolean hasNext() {
            if(iter.hasNext()) {
                NestedInteger ni = iter.next();
                if(ni.isInteger()) {
                    queue.add(ni.getInteger());
                    return true;
                } else {
                    stack.push(iter);
                    iter = ni.getList().iterator();
                }
            } else {
                if(!stack.isEmpty()){
                    iter = stack.pop();
                } else {
                    return false;
                }
            }
            return hasNext();
        }

    }

    public static void main(String[] args) {

    }
}

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}